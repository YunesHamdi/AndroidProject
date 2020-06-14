package com.example.andproject.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.andproject.views.AdviceFragment;
import com.example.andproject.views.FitnessFragment;
import com.example.andproject.views.JournalFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numberOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.numberOfTabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new JournalFragment();
            case 1:
                return new FitnessFragment();
            case 2:
                return new AdviceFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
