package ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Add;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.EditText;

import ca.collegeuniversel.workouttrackerrehmanbasharat.R;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Input.IDurationInput;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Input.INameInput;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Input.IQuantityInput;

public class AddWorkoutForm extends ConstraintLayout implements INameInput, IDurationInput {


    private final EditText editTextName;
    private final EditText editTextDuration;

    public AddWorkoutForm(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_add_workout_view, this);
        editTextName = findViewById(R.id.edit_text_name);
        editTextDuration = findViewById(R.id.edit_text_duration);
    }

    public AddWorkoutForm(@NonNull Context context, @Nullable AttributeSet attributes) {
        super(context, attributes);
        inflate(context, R.layout.activity_add_workout_view, this);
        editTextName = findViewById(R.id.edit_text_name);
        editTextDuration = findViewById(R.id.edit_text_duration);
    }

    public AddWorkoutForm(@NonNull Context context, @Nullable AttributeSet attributes, int defaultStyleAttribute) {
        super(context, attributes, defaultStyleAttribute);
        inflate(context, R.layout.activity_add_workout_view, this);
        editTextName = findViewById(R.id.edit_text_name);
        editTextDuration = findViewById(R.id.edit_text_duration);
    }

    public AddWorkoutForm(@NonNull Context context, @Nullable AttributeSet attributes, int defaultStyleAttribute, int defaultStyleResource) {
        super(context, attributes, defaultStyleAttribute, defaultStyleResource);
        inflate(context, R.layout.activity_add_workout_view, this);
        editTextName = findViewById(R.id.edit_text_name);
        editTextDuration = findViewById(R.id.edit_text_duration);
    }


    @Override
    public String getName() {
        return editTextName.getText().toString().trim();
    }

    @Override
    public void setName(String name) {
        editTextName.setText(name);
    }

    @Override
    public void setNameError(String error) {
        editTextName.setError(error);
    }

    @Override
    public String getDuration() {
        return editTextDuration.getText().toString().trim();
    }

    @Override
    public int parseDuration() {
        return Integer.parseInt(getDuration());
    }

    @Override
    public int tryParseDuration() {
        try {
            return Integer.parseInt(getDuration());
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }

    @Override
    public void setDuration(int duration) {
        editTextDuration.setText(String.valueOf(duration));
    }

    @Override
    public void setDurationError(String error) {
        editTextDuration.setError(error);
    }
}