package com.example.andproject;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.andproject.R;
import com.github.appintro.AppIntro2;
import com.github.appintro.AppIntroFragment;


public class MyAppIntro extends AppIntro2 {


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance(
                "Journaling",
                "A journal is meant to collect your ideas, a jorunal connects you to your values, emotions and goals",
                R.drawable.journalslide,
                getColor(R.color.colorPrimary)
        ));
        addSlide(AppIntroFragment.newInstance(
                "Fitness",
                "Move your body and yourself towards a happier, stronger and faster YOU!",
                R.drawable.fitnessslide,
                getColor(R.color.colorPrimary)
        ));
        addSlide(AppIntroFragment.newInstance(
                "Wisdom & Quotes",
                "Get a daily quote, enrich your mind and soul",
                R.drawable.wisdomslide,
                getColor(R.color.colorPrimary)
        ));
        setIndicatorColor(getResources().getColor(R.color.grey), getResources().getColor(R.color.grey));
    }

    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
