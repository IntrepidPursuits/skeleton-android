package io.intrepid.getaprint.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

public class SharePreferencesManager implements UserSettings {

    private static final String LAST_IP = "last_ip";

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

    @Override
    public void setLastIp(String ip) {
        preferences.edit().putString(LAST_IP, ip).apply();
    }

    @Override
    @Nullable
    public String getLastIp() {
        return preferences.getString(LAST_IP, null);
    }
}
