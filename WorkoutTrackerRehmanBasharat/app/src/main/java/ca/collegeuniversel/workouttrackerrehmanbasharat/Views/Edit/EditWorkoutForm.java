package ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Edit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;

import ca.collegeuniversel.workouttrackerrehmanbasharat.R;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Input.IDurationInput;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Input.INameInput;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Input.IQuantityInput;

public class EditWorkoutForm extends ConstraintLayout implements INameInput, IDurationInput, IQuantityInput {


    private final EditText editTextName;
    private final EditText editTextDuration;
    private final EditText editTextQuantity;


    public EditWorkoutForm(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_edit_workout_view, this);
        editTextName = findViewById(R.id.edit_text_name);
        editTextDuration = findViewById(R.id.edit_text_duration);
        editTextQuantity = findViewById(R.id.edit_text_quantity);
    }

    public EditWorkoutForm(@NonNull Context context, @Nullable AttributeSet attributes) {
        super(context, attributes);
        inflate(context, R.layout.activity_edit_workout_view, this);
        editTextName = findViewById(R.id.edit_text_name);
        editTextDuration = findViewById(R.id.edit_text_duration);
        editTextQuantity = findViewById(R.id.edit_text_quantity);
    }

    public EditWorkoutForm(@NonNull Context context, @Nullable AttributeSet attributes, int defaultStyleAttribute) {
        super(context, attributes, defaultStyleAttribute);
        inflate(context, R.layout.activity_edit_workout_view, this);
        editTextName = findViewById(R.id.edit_text_name);
        editTextDuration = findViewById(R.id.edit_text_duration);
        editTextQuantity = findViewById(R.id.edit_text_quantity);
    }

    public EditWorkoutForm(@NonNull Context context, @Nullable AttributeSet attributes, int defaultStyleAttribute, int defaultStyleResource) {
        super(context, attributes, defaultStyleAttribute, defaultStyleResource);
        inflate(context, R.layout.activity_edit_workout_view, this);
        editTextName = findViewById(R.id.edit_text_name);
        editTextDuration = findViewById(R.id.edit_text_duration);
        editTextQuantity = findViewById(R.id.edit_text_quantity);
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

    @Override
    public String getQuantity() {
        return editTextQuantity.getText().toString().trim();
    }

    @Override
    public int parseQuantity() {
        return Integer.parseInt(getQuantity());
    }

    @Override
    public int tryParseQuantity() {
        try {
            return Integer.parseInt(getQuantity());
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }

    @Override
    public void setQuantity(int quantity) {
        editTextQuantity.setText(String.valueOf(quantity));
    }

    @Override
    public void setQuantityError(String error) {
        editTextQuantity.setError(error);
    }



}