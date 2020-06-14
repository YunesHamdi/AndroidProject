package com.example.andproject.views;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andproject.R;
import com.example.andproject.adapters.ExerciseAdapter;
import com.example.andproject.models.Workouts;

public class GetOtherWorkout extends AppCompatActivity implements View.OnClickListener {

    Workouts workouts;
    TextView label;
    RecyclerView exerciseList;
    ExerciseAdapter exerciseAdapter;
    Button button1;
    Button button2;
    Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_other_workout);
        label = findViewById(R.id.newWorkoutLabel);
        exerciseList = findViewById(R.id.rv3);
        exerciseList.hasFixedSize();

        button1 = findViewById(R.id.getworkout1);
        button1.setOnClickListener(this);

        button2 = findViewById(R.id.getworkout2);
        button2.setOnClickListener(this);

        button3 = findViewById(R.id.getworkout3);
        button3.setOnClickListener(this);

        workouts = new Workouts();


        Toolbar toolbar = findViewById(R.id.toolbar_getOtherWorkout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("New workouts");
        initWorkoutRecyclerView();

    }

    public void initWorkoutRecyclerView() {
        exerciseList.setLayoutManager(new LinearLayoutManager(this));
        exerciseAdapter = new ExerciseAdapter(workouts.getWorkout(3));
        exerciseList.setAdapter(exerciseAdapter);
    }


    @Override
    public void onClick(View v) {
        initWorkoutRecyclerView();
        switch (v.getId()) {
            case R.id.getworkout1:
                exerciseAdapter = new ExerciseAdapter(workouts.getWorkout(0));
                exerciseList.setAdapter(exerciseAdapter);
                break;
            case R.id.getworkout2:
                exerciseAdapter = new ExerciseAdapter(workouts.getWorkout(1));
                exerciseList.setAdapter(exerciseAdapter);
                break;
            case R.id.getworkout3:
                exerciseAdapter = new ExerciseAdapter(workouts.getWorkout(2));
                exerciseList.setAdapter(exerciseAdapter);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
