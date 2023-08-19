package ca.collegeuniversel.simplepersistence;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonSave = findViewById(R.id.saveButton);
        Button buttonLoad = findViewById(R.id.loadButton);
        Button buttonReset = findViewById(R.id.resetButton);
        Button buttonClear = findViewById(R.id.clearButton);

        String name = "info.txt";
        String contents = "First line.\nSecond line.\nThird line.";

        buttonSave.setOnClickListener(button -> saveFile());


    }


    private void saveFile(Context context, String name, String firstName, String lastName, Double height, Integer Age){
        try (FileOutputStream writer = context.openFileOutput(name, Context.MODE_PRIVATE)) {
            Log.d(TAG, "Writing contents to file: " + name);
            String data = firstName + "\n" + lastName + "\n" + height.toString() + "\n" + Age.toString() + "\n";
            writer.write(data.getBytes());
            Snackbar.make(findViewById(R.id.saveButton), "File saved successfully.", Snackbar.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e(TAG, "Failed to write contents to file. " + e.getMessage());
            Snackbar.make(findViewById(android.R.id.content), "Failed to write contents to file.", Snackbar.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }
    }

    private void loadFile(Context context, String name){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream inputStream = context.openFileInput(name);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader)) {
            Log.d(TAG, "Reading contents from file: " + name);
            Snackbar.make(findViewById(android.R.id.content), "Reading contents from file: " + name, Snackbar.LENGTH_LONG).show();
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to read contents from file. " + e.getMessage());
            Snackbar.make(findViewById(android.R.id.content), "Failed to write contents from file.", Snackbar.LENGTH_LONG).show();
            throw new RuntimeException(e);
        } finally {
            String contents = stringBuilder.toString();
            Log.d(TAG, "Successfully read file: " + name);
            Log.d(TAG, contents);
            Snackbar.make(findViewById(android.R.id.content), "Successfully read file: " + name, Snackbar.LENGTH_LONG).show();
        }
    }




}