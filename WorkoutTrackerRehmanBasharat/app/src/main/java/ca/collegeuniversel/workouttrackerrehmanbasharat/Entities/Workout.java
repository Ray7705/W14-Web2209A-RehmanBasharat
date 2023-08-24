package ca.collegeuniversel.workouttrackerrehmanbasharat.Entities;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Workout {
    private final long id;
    private String name;
    private int duration;
    private int quantity;


    public Workout(@NonNull String name, int duration){
        this(0, name, duration, 1);
    }
    public Workout(@NonNull String name, int duration, int quantity) {
        this(0, name, duration, quantity);
    }
    public Workout(@NonNull Workout other) {
        this(other.id, other.name, other.duration, other.quantity);
    }
    public Workout(long id, @NonNull Workout other) {
        this(id, other.name, other.duration, other.quantity);
    }

    public Workout(long id, @NonNull String name, int duration, int quantity) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.duration = validateDuration(quantity);
        this.quantity = validateQuantity(quantity);
    }





    @Override
    @NonNull
    public String toString() {
        return name;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalDuration() {
        if (duration * quantity < 1)
            throw new IllegalArgumentException("Total Duration must not be less than 1.");
        return duration * quantity;
    }



    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public void setDuration(int duration) {
        this.duration = validateDuration(duration);
    }

    public void setQuantity(int quantity) {
        this.quantity = validateQuantity(quantity);
    }



    private static int validateDuration(int duration) {
        if (duration < 1)
            throw new IllegalArgumentException("Duration must not be less than 1.");
        return duration;
    }

    private static int validateQuantity(int quantity) {
        if (quantity < 1)
            throw new IllegalArgumentException("Quantity must not be less than 1.");
        return quantity;
    }

    private static int validateTotalDuration(int duration) {
        if (duration < 1)
            throw new IllegalArgumentException("Duration must not be less than 1.");
        return duration;
    }
}
