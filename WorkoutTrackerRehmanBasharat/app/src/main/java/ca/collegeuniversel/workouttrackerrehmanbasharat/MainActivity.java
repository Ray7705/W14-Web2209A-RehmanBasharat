package ca.collegeuniversel.workouttrackerrehmanbasharat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import ca.collegeuniversel.workouttrackerrehmanbasharat.Entities.Workout;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Persistence.DatabaseHelper;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Persistence.WorkoutRepository;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Add.AddWorkoutForm;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Edit.EditWorkoutForm;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.List.WorkoutAdapter;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.List.WorkoutHeaderView;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Views.Validation.WorkoutFormValidator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseHelper helper;
    private WorkoutRepository repository;

    private List<Workout> workouts;
    private WorkoutAdapter adapter;
    private WorkoutFormValidator validator;

    private WorkoutHeaderView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        helper = new DatabaseHelper(this);
        repository = new WorkoutRepository(helper);
        workouts = repository.getAll();
        adapter = new WorkoutAdapter(this, workouts, this::displayEditWorkoutDialog);
        validator = new WorkoutFormValidator(this);

        header = findViewById(R.id.workout_header_view);
        header.setTotalDuration(getTotalDuration());

        ListView listViewWorkouts = findViewById(R.id.list_view_workouts);
        listViewWorkouts.setAdapter(adapter);

        FloatingActionButton fabAddWorkout = findViewById(R.id.fab_add_workout);
        fabAddWorkout.setOnClickListener(fab -> displayAddWorkoutDialog());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        DatabaseHelper helper = this.helper;
        if (helper != null)
            helper.close();
    }

    private void displayAddWorkoutDialog() {
        AddWorkoutForm form = new AddWorkoutForm(this);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.add_workout)
                .setView(form)
                .setPositiveButton(R.string.add, null)
                .setNegativeButton(R.string.cancel, null)
                .show();

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener(button -> tryAddWorkout(alertDialog, form));
    }

    private void tryAddWorkout(DialogInterface dialog, AddWorkoutForm form) {
        try {
            boolean valid = validator.validateAddWorkoutForm(form);
            if (valid) {
                String name = form.getName();
                int duration = form.tryParseDuration();
                Workout workout = new Workout(name, duration);
                addWorkout(workout);
                dialog.dismiss();
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to add workout.", e);
            Snackbar.make(findViewById(android.R.id.content), R.string.failed_to_add_workout, Snackbar.LENGTH_LONG).show();
            dialog.dismiss();
        }
    }

    private void addWorkout(Workout workout) {
        Workout addedWorkout = repository.add(workout);

        if (addedWorkout != null) {
            workouts.add(addedWorkout);
            adapter.notifyDataSetChanged();
            header.setTotalDuration(getTotalDuration());
            Log.i(TAG, String.format("Added Duration %d: %s (%d duration x %d)", addedWorkout.getId(), addedWorkout.getName(), addedWorkout.getDuration(), addedWorkout.getQuantity()));
        } else {
            Log.e(TAG, String.format("Failed to add Duration: %s (%d duration x %d)", workout.getName(), workout.getDuration(), workout.getQuantity()));
            Snackbar.make(findViewById(android.R.id.content), R.string.failed_to_add_workout, Snackbar.LENGTH_LONG).show();
        }
    }

    private void displayEditWorkoutDialog(int index) {
        if (index >= 0 && index < workouts.size()) {
            Workout workout = workouts.get(index);

            EditWorkoutForm form = new EditWorkoutForm(this);
            form.setName(workout.getName());
            form.setDuration(workout.getDuration());
            form.setQuantity(workout.getQuantity());

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.edit_workout)
                    .setView(form)
                    .setPositiveButton(R.string.save, null)
                    .setNegativeButton(R.string.cancel, null)
                    .setNeutralButton(R.string.delete, (dialog, which) -> tryDeleteWorkout(index))
                    .show();

            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                    .setOnClickListener(button -> tryEditWorkout(alertDialog, form, index));
        }
    }


    private void tryEditWorkout(DialogInterface dialog, EditWorkoutForm form, int index) {
        try {
            boolean valid = validator.validateEditWorkoutForm(form);
            if (valid) {
                String name = form.getName();
                int duration = form.tryParseDuration();
                int quantity = form.tryParseQuantity();

                if (quantity >= 1) {
                    Workout workout = new Workout(workouts.get(index));
                    workout.setName(name);
                    workout.setDuration(duration);
                    workout.setQuantity(quantity);
                    updateWorkout(index, workout);
                } else {
                    deleteWorkout(index);
                }

                dialog.dismiss();
            }
        } catch (Exception e) {
            Log.e(TAG, String.format("Failed to edit Workout at index %d.", index), e);
            Snackbar.make(findViewById(android.R.id.content), R.string.failed_to_edit_workout, Snackbar.LENGTH_LONG).show();
            dialog.dismiss();
        }
    }

    private void updateWorkout(int index, Workout workout) {
        boolean updated = repository.update(workout);

        if (updated) {
            workouts.set(index, workout);
            adapter.notifyDataSetChanged();
            header.setTotalDuration(getTotalDuration());
            Log.i(TAG, String.format("Updated Workout %d: %s (%d Duration x %d)", workout.getId(), workout.getName(), workout.getDuration(), workout.getQuantity()));
        } else {
            Log.e(TAG, String.format("Failed to update workout %d: %s (%d workout x %d)", workout.getId(), workout.getName(), workout.getDuration(), workout.getQuantity()));
            Snackbar.make(findViewById(android.R.id.content), R.string.failed_to_save_changes_to_workout, Snackbar.LENGTH_LONG).show();
        }
    }

    private void tryDeleteWorkout(int index) {
        try {
            deleteWorkout(index);
        } catch (Exception e) {
            adapter.notifyDataSetChanged();
            Log.e(TAG, String.format("Failed to delete workout at index %d.", index), e);
            Snackbar.make(findViewById(android.R.id.content), R.string.failed_to_delete_workout, Snackbar.LENGTH_LONG).show();
        }
    }

    private void deleteWorkout(int index) {
        Workout workout = workouts.get(index);
        boolean deleted = repository.delete(workout);

        if (deleted) {
            workouts.remove(index);
            adapter.notifyDataSetChanged();
            header.setTotalDuration(getTotalDuration());
            Log.i(TAG, String.format("Deleted Workout %d: %s (%d Workout x %d)", workout.getId(), workout.getName(), workout.getDuration(), workout.getQuantity()));
            Snackbar.make(findViewById(android.R.id.content), getString(R.string.deleted_workout, workout.getName()), Snackbar.LENGTH_LONG).show();
        } else {
            adapter.notifyDataSetChanged();
            Log.e(TAG, String.format("Failed to delete workout %d: %s (%d calories x %d)", workout.getId(), workout.getName(), workout.getDuration(), workout.getQuantity()));
            Snackbar.make(findViewById(android.R.id.content), R.string.failed_to_delete_workout, Snackbar.LENGTH_LONG).show();
        }
    }

    private int getTotalDuration() {
        int totalDuration = 0;
        for (Workout workout : workouts)
            totalDuration += workout.getTotalDuration();
        return totalDuration;
    }
}