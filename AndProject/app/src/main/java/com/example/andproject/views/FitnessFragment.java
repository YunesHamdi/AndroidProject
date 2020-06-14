package com.example.andproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andproject.R;
import com.example.andproject.adapters.ExerciseAdapter;
import com.example.andproject.models.Exercise;
import com.example.andproject.viewmodels.FitnessFragmentViewModel;

import java.util.List;

public class FitnessFragment extends Fragment {


    View v;
    TextView label;
    RecyclerView exerciseList;
    ExerciseAdapter exerciseAdapter;


    private FitnessFragmentViewModel mFitnessFragmentViewModel;

    public FitnessFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fitness_fragment, container, false);
        label = v.findViewById(R.id.workoutLabel);
        exerciseList = v.findViewById(R.id.rv);
        exerciseList.hasFixedSize();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initWorkoutRecyclerView();
    }

    public void initWorkoutRecyclerView() {
        mFitnessFragmentViewModel.init();
        mFitnessFragmentViewModel.getWorkout().observe(getViewLifecycleOwner(), new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                exerciseAdapter.notifyDataSetChanged();
            }
        });
        exerciseAdapter = new ExerciseAdapter(mFitnessFragmentViewModel.getWorkout().getValue());
        exerciseList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        exerciseList.setAdapter(exerciseAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFitnessFragmentViewModel = new ViewModelProvider(this).get(FitnessFragmentViewModel.class);

    }
}