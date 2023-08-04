package ca.collegeuniversel.introduction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextFullName = findViewById(R.id.editText);
        Switch lightSwitch = findViewById(R.id.switch1);
        Button buttonSubmit = findViewById(R.id.button);

        buttonSubmit.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View view) {
                String userInput = editTextFullName.getText().toString();
                boolean switchState = lightSwitch.isChecked();

                Log.i(MainActivity.class.getSimpleName(),  "Input: " + userInput + ", Switch: " + switchState);

                Snackbar snackbar = Snackbar.make(view, "Form data logged", Snackbar.LENGTH_LONG);
                snackbar.setAction("Action", new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Log.i(MainActivity.class.getSimpleName(), "Snackbar action clicked");
                    }
                });
                snackbar.show();
            }
        });
    }
}