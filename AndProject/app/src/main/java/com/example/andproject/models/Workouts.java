package com.example.andproject.models;

import com.example.andproject.R;

import java.util.ArrayList;

public class Workouts {

    public ArrayList<ArrayList> workouts = new ArrayList<>();

    public Workouts() {
        ArrayList<Exercise> workout1 = new ArrayList<>();
        workout1.add(new Exercise("Air bike: for 1min", R.drawable.airbike));
        workout1.add(new Exercise("Burpee: 3 sets of 10", R.drawable.burpee));
        workout1.add(new Exercise("Arm circles: 30 rotations per arm", R.drawable.armcircles));
        workout1.add(new Exercise("Flutter kick: for 1min", R.drawable.flutterkick));
        workout1.add(new Exercise("Hanging leg rises: 3 sets of 10", R.drawable.hanginglegrises));


        ArrayList<Exercise> workout2 = new ArrayList<>();
        workout2.add(new Exercise("Hip circle: 30 in each direction", R.drawable.hipcircle));
        workout2.add(new Exercise("Kneeling hip flexor: 3 sets of 10", R.drawable.kneelinghipflexor));
        workout2.add(new Exercise("Knee tuck jump: 3 sets of 10", R.drawable.armcircles));
        workout2.add(new Exercise("Plank: 3 times max duration", R.drawable.plank));
        workout2.add(new Exercise("Pullup: 3 sets of 10", R.drawable.pullup));

        ArrayList<Exercise> workout3 = new ArrayList<>();
        workout3.add(new Exercise("Punches: shadow box for 1min", R.drawable.punches));
        workout3.add(new Exercise("Pushups: 3 sets of 10", R.drawable.pushup));
        workout3.add(new Exercise("Rope jumping: for 1min", R.drawable.ropejumping));
        workout3.add(new Exercise("Russian twist: 3 sets of 10", R.drawable.russiantwist));
        workout3.add(new Exercise("Side lying leg raises: 3 sets of 10 per side", R.drawable.sidelyingleglift));

        ArrayList<Exercise> workout4 = new ArrayList<>();
        workout4.add(new Exercise("Spider crawl: for 100m", R.drawable.spidercrawl));
        workout4.add(new Exercise("Split jump: 3 sets of 10", R.drawable.splitjump));
        workout4.add(new Exercise("Bodyweight squat: 3 sets of 10", R.drawable.squat));
        workout4.add(new Exercise("Toe touchers: 3 sets of 10", R.drawable.toetouchers));
        workout4.add(new Exercise("Torso roation: 3 sets of 10", R.drawable.torsorotation));


        workouts.add(workout1);
        workouts.add(workout2);
        workouts.add(workout3);
        workouts.add(workout4);

    }

    public ArrayList<Exercise> getWorkout(int number) {
        return workouts.get(number);

    }
}
