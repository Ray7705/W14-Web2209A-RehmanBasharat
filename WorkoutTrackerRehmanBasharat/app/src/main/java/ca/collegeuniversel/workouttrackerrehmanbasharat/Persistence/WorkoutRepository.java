package ca.collegeuniversel.workouttrackerrehmanbasharat.Persistence;

import static java.util.Objects.requireNonNull;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ca.collegeuniversel.workouttrackerrehmanbasharat.Entities.Workout;

public class WorkoutRepository {
    private final DatabaseHelper helper;

    public WorkoutRepository(DatabaseHelper helper) {
        this.helper = requireNonNull(helper);
    }


    public List<Workout> getAll() {
        String query = "SELECT id, name, duration, quantity FROM workouts;";

        SQLiteDatabase database = helper.getReadableDatabase();
        try (Cursor cursor = database.rawQuery(query, null)) {
            ArrayList<Workout> workouts = new ArrayList<>();
            while (cursor.moveToNext())
                workouts.add(readNextWorkout(cursor));
            return workouts;
        }
    }

    public Workout get(long id) {
        String query = "SELECT id, name, duration, quantity FROM workouts WHERE id = ?;";

        String[] parameters = new String[] { String.valueOf(id) };

        SQLiteDatabase database = helper.getReadableDatabase();
        try (Cursor cursor = database.rawQuery(query, parameters)) {
            if (cursor.moveToNext())
                return readNextWorkout(cursor);
            return null;
        }
    }

    private static Workout readNextWorkout(Cursor cursor) {
        long id = cursor.getLong(0);
        String name = cursor.getString(1);
        int duration = cursor.getInt(2);
        int quantity = cursor.getInt(3);

        return new Workout(id, name, duration, quantity);
    }

    public Workout add(Workout workout) {
        Objects.requireNonNull(workout);

        ContentValues values = new ContentValues();
        values.put("name", workout.getName());
        values.put("duration", workout.getDuration());
        values.put("quantity", workout.getQuantity());

        SQLiteDatabase database = helper.getWritableDatabase();
        long id = database.insert("workouts", null, values);
        if (id != -1)
            return new Workout(id, workout);
        return null;
    }

    public boolean update(Workout workout) {
        Objects.requireNonNull(workout);

        ContentValues values = new ContentValues();
        values.put("name", workout.getName());
        values.put("duration", workout.getDuration());
        values.put("quantity", workout.getQuantity());

        String[] parameters = new String[] { String.valueOf(workout.getId()) };

        SQLiteDatabase database = helper.getWritableDatabase();
        int rowsUpdated = database.update("workouts", values, "id = ?", parameters);
        return rowsUpdated > 0;
    }

    public boolean updateName(Workout workout) {
        Objects.requireNonNull(workout);

        ContentValues values = new ContentValues();
        values.put("name", workout.getName());

        String[] parameters = new String[] { String.valueOf(workout.getId()) };

        SQLiteDatabase database = helper.getWritableDatabase();
        int rowsUpdated = database.update("workouts", values, "id = ?", parameters);
        return rowsUpdated > 0;
    }

    public boolean updateDuration(Workout workout) {
        Objects.requireNonNull(workout);

        ContentValues values = new ContentValues();
        values.put("duration", workout.getDuration());

        String[] parameters = new String[] { String.valueOf(workout.getId()) };

        SQLiteDatabase database = helper.getWritableDatabase();
        int rowsUpdated = database.update("workouts", values, "id = ?", parameters);
        return rowsUpdated > 0;
    }

    public boolean updateQuantity(Workout workout) {
        Objects.requireNonNull(workout);

        ContentValues values = new ContentValues();
        values.put("quantity", workout.getQuantity());

        String[] parameters = new String[] { String.valueOf(workout.getId()) };

        SQLiteDatabase database = helper.getWritableDatabase();
        int rowsUpdated = database.update("workouts", values, "id = ?", parameters);
        return rowsUpdated > 0;
    }

    public boolean delete(Workout workout) {
        Objects.requireNonNull(workout);
        return delete(workout.getId());
    }

    public boolean delete(long id) {
        String[] parameters = new String[] { String.valueOf(id) };

        SQLiteDatabase database = helper.getWritableDatabase();
        int rowsDeleted = database.delete("workouts", "id = ?", parameters);
        return rowsDeleted > 0;
    }

}
