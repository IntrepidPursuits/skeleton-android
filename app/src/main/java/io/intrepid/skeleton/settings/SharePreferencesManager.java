package io.intrepid.skeleton.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharePreferencesManager implements UserSettings {

    private static SharePreferencesManager instance;

    private final SharedPreferences preferences;

    public static UserSettings getInstance(Context context) {
        if (instance == null) {
            instance = new SharePreferencesManager(context);
        }
        return instance;
    }

    private SharePreferencesManager(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
}
