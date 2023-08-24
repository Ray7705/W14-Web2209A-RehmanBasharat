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

public class WorkoutHeaderView extends LinearLayoutCompat {

    private final TextView textViewTotalDuration;

    public WorkoutHeaderView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_workout_header_view, this);
        textViewTotalDuration = findViewById(R.id.text_view_total_duration);
    }

    public WorkoutHeaderView(@NonNull Context context, @Nullable AttributeSet attributes) {
        super(context, attributes);
        inflate(context, R.layout.activity_workout_header_view, this);
        textViewTotalDuration = findViewById(R.id.text_view_total_duration);
    }

    public WorkoutHeaderView(@NonNull Context context, @Nullable AttributeSet attributes, int defaultStyleAttribute) {
        super(context, attributes, defaultStyleAttribute);
        inflate(context, R.layout.activity_workout_header_view, this);
        textViewTotalDuration = findViewById(R.id.text_view_total_duration);
    }

    public void setTotalDuration(int totalDuration) {
        if (totalDuration >= 0) {
            textViewTotalDuration.setText(getResources().getString(R.string.total_amount, totalDuration));
        } else {
            textViewTotalDuration.setText(R.string.total);
        }
    }

}