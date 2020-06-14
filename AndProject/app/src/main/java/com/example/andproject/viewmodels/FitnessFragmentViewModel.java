package com.example.andproject.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.andproject.models.Exercise;
import com.example.andproject.repositories.ExerciseRepository;

import java.util.List;

public class FitnessFragmentViewModel extends ViewModel {

    private MutableLiveData<List<Exercise>> mExercises;
    private ExerciseRepository exerciseRepo;

    public void init() {
        if (mExercises != null) {
            return;
        }
        exerciseRepo = ExerciseRepository.getInstance();
        mExercises = exerciseRepo.getExercises();
    }

    public LiveData<List<Exercise>> getWorkout() {
        return mExercises;
    }
}
