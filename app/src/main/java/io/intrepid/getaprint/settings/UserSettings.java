package io.intrepid.getaprint.settings;

import android.support.annotation.Nullable;

public interface UserSettings {

    void setLastIp(String ip);

    @Nullable
    String getLastIp();
}
