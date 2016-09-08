package io.intrepid.skeleton.models;

import android.content.Context;

import io.intrepid.skeleton.R;

public enum Demo {
    GITHUB(R.string.demo_github);

    private int titleResId;

    Demo(int titleResId) {
        this.titleResId = titleResId;
    }

    public String getTitle(Context context) {
        return context.getString(titleResId);
    }
}
