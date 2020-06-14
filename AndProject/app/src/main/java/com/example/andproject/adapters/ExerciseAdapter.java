package com.example.andproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andproject.R;
import com.example.andproject.models.Exercise;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {


    private List<Exercise> mExercises;

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exercise_list_item, parent, false);
        return new ViewHolder(view);
    }

    public ExerciseAdapter(List<Exercise> exercises) {
        mExercises = exercises;
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.icon.setImageResource(mExercises.get(position).getIconId());
        viewHolder.name.setText(mExercises.get(position).getName());
    }


    @Override
    public int getItemCount() {
        if (mExercises == null)
            return 0;
        else return mExercises.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_icon);
            name = itemView.findViewById(R.id.tv_name);
        }
    }
}
