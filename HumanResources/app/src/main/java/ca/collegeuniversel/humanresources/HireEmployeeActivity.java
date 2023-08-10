package ca.collegeuniversel.humanresources;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class HireEmployeeActivity extends AppCompatActivity {
    private String managerName;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private EditText editTextDateOfBirth;
    private EditText editTextJobTitle;
    private EditText editTextCompensation;
    private Spinner spinnerCompensationUnits;
    private RadioGroup radioGroupEmployeeType;
    private TextView textViewEmployeeTypeLabel;
    private TextView textViewEmployeeTypeError;
    private SwitchCompat switchNewHire;
    private SwitchCompat switchManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_employee);

        managerName = getIntent().getStringExtra("manager_name");
        editTextFirstName = findViewById(R.id.edit_text_first_name);
        editTextLastName = findViewById(R.id.edit_text_last_name);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextDateOfBirth = findViewById(R.id.edit_text_date_of_birth);
        editTextJobTitle = findViewById(R.id.edit_text_job_title);
        editTextCompensation = findViewById(R.id.edit_text_compensation);
        spinnerCompensationUnits = findViewById(R.id.spinner_compensation);
        radioGroupEmployeeType = findViewById(R.id.radio_group_employee_type);
        textViewEmployeeTypeLabel = findViewById(R.id.text_view_employee_type_label);
        textViewEmployeeTypeError = findViewById(R.id.text_view_employee_type_error);
        switchNewHire = findViewById(R.id.switch_new_hire);
        switchManager = findViewById(R.id.switch_manager);

        radioGroupEmployeeType.setOnCheckedChangeListener((group, id) -> displayErrorTextViewIfPropertyIsInvalid(null, textViewEmployeeTypeError));

        Button buttonCancel = findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(button -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        Button buttonHire = findViewById(R.id.button_hire);
        buttonHire.setOnClickListener(this::hireEmployee);
    }

    private void hireEmployee(View button) {
        boolean valid = validateForm();

        if (valid) {
            Employee employee = new Employee(
                    UUID.randomUUID().toString(),
                    editTextFirstName.getText().toString().trim(),
                    editTextLastName.getText().toString().trim(),
                    editTextEmail.getText().toString().trim(),
                    LocalDate.parse(editTextDateOfBirth.getText().toString().trim()),
                    editTextJobTitle.getText().toString().trim(),
                    new BigDecimal(editTextCompensation.getText().toString().trim()),
                    getCompensationUnits(),
                    getEmployeeType(),
                    switchManager.isChecked());

            Intent intent = new Intent();
            intent.putExtra("manager_name", managerName);
            intent.putExtra("employee", employee);

            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private boolean validateForm() {
        EmployeeValidator validator = new EmployeeValidator(getResources(),
                editTextFirstName.getText().toString().trim(),
                editTextLastName.getText().toString().trim(),
                editTextPassword.getText().toString(),
                editTextEmail.getText().toString().trim(),
                editTextDateOfBirth.getText().toString().trim(),
                editTextJobTitle.getText().toString().trim(),
                editTextCompensation.getText().toString().trim(),
                getCompensationUnits(),
                getEmployeeType(),
                switchManager.isChecked());

        // The bitwise-and operator is used here to prevent short-circuiting,
        // ensuring that all validation methods are always called.

        return displayErrorIfPropertyIsInvalid(validator.validateFirstName(), editTextFirstName)
                & displayErrorIfPropertyIsInvalid(validator.validateLastName(), editTextLastName)
                & displayErrorIfPropertyIsInvalid(validator.validatePassword(), editTextPassword)
                & displayErrorIfPropertyIsInvalid(validator.validateEmail(), editTextEmail)
                & displayErrorIfPropertyIsInvalid(validator.validateDateOfBirth(), editTextDateOfBirth)
                & displayErrorIfPropertyIsInvalid(validator.validateJobTitle(), editTextJobTitle)
                & displayErrorIfPropertyIsInvalid(validator.validateCompensation(), editTextCompensation)
                & displayErrorTextViewIfPropertyIsInvalid(validator.validateType(), textViewEmployeeTypeError);
    }

    private boolean displayErrorIfPropertyIsInvalid(String error, TextView textView) {
        textView.setError(error);
        return error == null;
    }

    private boolean displayErrorTextViewIfPropertyIsInvalid(String error, TextView errorTextView) {
        errorTextView.setText(error);
        errorTextView.setError(error);
        errorTextView.setVisibility(error != null ? View.VISIBLE : View.GONE);
        return error == null;
    }

    private CompensationUnits getCompensationUnits() {
        // Workaround for mapping from selected localized spinner item to enumeration value
        // given that an adapter is not used here the spinner.

        String compensationUnitsText = (String) spinnerCompensationUnits.getSelectedItem();
        if (compensationUnitsText == null) {
            return null;
        }

        Resources resources = getResources();
        if (compensationUnitsText.equals(resources.getString(R.string.compensation_units_yearly))) {
            return CompensationUnits.Yearly;
        }

        if (compensationUnitsText.equals(resources.getString(R.string.compensation_units_hourly))) {
            return CompensationUnits.Hourly;
        }

        return null;
    }

    private EmployeeType getEmployeeType() {
        int typeId = radioGroupEmployeeType.getCheckedRadioButtonId();
        if (typeId == R.id.radio_button_permanent) {
            return EmployeeType.Permanent;
        }

        if (typeId == R.id.radio_button_contractor) {
            return EmployeeType.Contractor;
        }

        if (typeId == R.id.radio_button_intern) {
            return EmployeeType.Intern;
        }

        return null;
    }

    private LocalDate tryParseLocalDate(String text) {
        try {
            return LocalDate.parse(text);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private BigDecimal tryParseBigDecimal(String text) {
        try {
            return new BigDecimal(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}