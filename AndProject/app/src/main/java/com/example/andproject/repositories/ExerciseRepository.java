package com.example.andproject.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.andproject.R;
import com.example.andproject.models.Exercise;

import java.util.ArrayList;
import java.util.List;


public class ExerciseRepository {    // faking getting data, could not find an Exercises API that matched my needs

    private static ExerciseRepository instance;
    private ArrayList<Exercise> dataSet = new ArrayList<>();


    public static ExerciseRepository getInstance() {
        if (instance == null) {
            instance = new ExerciseRepository();
        }
        return instance;
    }


    public MutableLiveData<List<Exercise>> getExercises() {
        setExercises();
        MutableLiveData<List<Exercise>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    public void setExercises() {

        dataSet.add(new Exercise("Spider crawl: for 100m", R.drawable.spidercrawl));
        dataSet.add(new Exercise("Split jump: 3 sets of 10", R.drawable.splitjump));
        dataSet.add(new Exercise("Bodyweight squat: 3 sets of 10", R.drawable.squat));
        dataSet.add(new Exercise("Toe touchers: 3 sets of 10", R.drawable.toetouchers));
        dataSet.add(new Exercise("Torso roation: 3 sets of 10", R.drawable.torsorotation));
    }
}
