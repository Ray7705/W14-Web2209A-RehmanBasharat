package ca.collegeuniversel.workouttrackerrehmanbasharat.Views.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.TextView;

import ca.collegeuniversel.workouttrackerrehmanbasharat.R;

public class WorkoutView extends LinearLayoutCompat {

    private final TextView textViewName;
    private final TextView textViewDuration;
    private final TextView textViewQuantity;
    private final TextView textViewTotalDuration;

    public WorkoutView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_workout_view, this);
        textViewName = findViewById(R.id.text_view_name);
        textViewDuration = findViewById(R.id.text_view_duration);
        textViewQuantity = findViewById(R.id.text_view_quantity);
        textViewTotalDuration = findViewById(R.id.text_view_total_duration);
    }

    public WorkoutView(@NonNull Context context, @Nullable AttributeSet attributes) {
        super(context, attributes);
        inflate(context, R.layout.activity_workout_view, this);
        textViewName = findViewById(R.id.text_view_name);
        textViewDuration = findViewById(R.id.text_view_duration);
        textViewQuantity = findViewById(R.id.text_view_quantity);
        textViewTotalDuration = findViewById(R.id.text_view_total_duration);
    }

    public WorkoutView(@NonNull Context context, @Nullable AttributeSet attributes, int defaultStyleAttribute) {
        super(context, attributes, defaultStyleAttribute);
        inflate(context, R.layout.activity_workout_view, this);
        textViewName = findViewById(R.id.text_view_name);
        textViewDuration = findViewById(R.id.text_view_duration);
        textViewQuantity = findViewById(R.id.text_view_quantity);
        textViewTotalDuration = findViewById(R.id.text_view_total_duration);
    }

    public void setName(String name) {
        textViewName.setText(name);
    }

    public void setDuration(int duration) {
        textViewDuration.setText(String.valueOf(duration));
    }

    public void setQuantity(int quantity) {
        textViewQuantity.setText(String.valueOf(quantity));
    }

    public void setTotalDuration(int totalDuration) {
        textViewTotalDuration.setText(String.valueOf(totalDuration));
    }



}