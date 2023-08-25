package ca.collegeuniversel.workouttrackerrehmanbasharat.Views.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

import ca.collegeuniversel.workouttrackerrehmanbasharat.Entities.Workout;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Edit.IEditWorkoutCommand;
import ca.collegeuniversel.workouttrackerrehmanbasharat.R;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Edit.IEditWorkoutCommand;

public class WorkoutAdapter extends ArrayAdapter<Workout> {

    private final List<Workout> workouts;
    private final IEditWorkoutCommand editWorkoutCommand;

    public WorkoutAdapter(Context context, List<Workout> workouts, IEditWorkoutCommand editWorkoutCommand) {
        super(context, R.layout.activity_workout_view, workouts);
        this.workouts = Objects.requireNonNull(workouts);
        this.editWorkoutCommand = Objects.requireNonNull(editWorkoutCommand);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        WorkoutView workoutView = (WorkoutView) convertView;
        if (workoutView == null)
            workoutView = new WorkoutView(getContext());

        Workout workout = getItem(position);

        workoutView.setName(workout.getName());
        workoutView.setDuration(workout.getDuration());
        workoutView.setQuantity(workout.getQuantity());
        workoutView.setTotalDuration(workout.getTotalDuration());

        workoutView.setOnClickListener(view -> editWorkoutCommand.editWorkout(position));
        workoutView.setOnLongClickListener(view -> {
            editWorkoutCommand.editWorkout(position);
            return true;
        });

        return workoutView;
    }
}
