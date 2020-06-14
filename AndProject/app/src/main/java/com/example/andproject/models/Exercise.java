package com.example.andproject.models;

public class Exercise {
    private String mName;
    private int mIconId;

    public Exercise(String name, int iconId) {
        mName = name;
        mIconId = iconId;
    }

    public String getName() {
        return mName;
    }

    public int getIconId() {
        return mIconId;
    }
}
