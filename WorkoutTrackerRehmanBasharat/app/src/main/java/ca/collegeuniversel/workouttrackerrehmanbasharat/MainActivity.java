package ca.collegeuniversel.workouttrackerrehmanbasharat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import ca.collegeuniversel.workouttrackerrehmanbasharat.Entities.Workout;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Persistence.DatabaseHelper;
import ca.collegeuniversel.workouttrackerrehmanbasharat.Persistence.WorkoutRepository;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}