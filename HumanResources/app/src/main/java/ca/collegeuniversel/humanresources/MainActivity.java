package ca.collegeuniversel.humanresources;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private EditText editTextManagerName;
    private Button buttonHireEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultLauncher<Intent> hireEmployeeActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onActivityResult
        );

        editTextManagerName = findViewById(R.id.edit_text_manager_name);
        buttonHireEmployee = findViewById(R.id.button_hire_employee);

        buttonHireEmployee.setOnClickListener(button -> {
            boolean valid = validateForm();

            if (valid) {
                String managerName = editTextManagerName.getText().toString().trim();

                Intent intent = new Intent(this, HireEmployeeActivity.class);
                intent.putExtra("manager_name", managerName);

                hireEmployeeActivityLauncher.launch(intent);
            }
        });
    }

    private boolean validateForm() {
        return validateManagerName();
    }

    private boolean validateManagerName() {
        String managerName = editTextManagerName.getText().toString().trim();
        if (managerName.isEmpty()) {
            editTextManagerName.setError(getResources().getString(R.string.enter_manager_name));
            return false;
        }

        return true;
    }

    private void onActivityResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent intent = result.getData();
            String managerName = intent.getStringExtra("manager_name");
            Employee employee = (Employee) intent.getSerializableExtra("employee");
            String message = getResources().getString(R.string.manager_successfully_hired_employee, managerName, employee.getFullName());
            Snackbar.make(buttonHireEmployee, message, Snackbar.LENGTH_LONG)
                    .show();
        } else if (result.getResultCode() == RESULT_CANCELED) {
            Snackbar.make(buttonHireEmployee, getResources().getString(R.string.hiring_canceled), Snackbar.LENGTH_LONG)
                    .show();
        }
    }
}