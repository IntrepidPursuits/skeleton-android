package io.intrepid.getaprint.screens.example2;

import android.content.Intent;
import android.support.v4.app.Fragment;

import io.intrepid.getaprint.base.BaseFragmentActivity;

public class Example2Activity extends BaseFragmentActivity {

    @Override
    protected Fragment createFragment(Intent intent) {
        return new Example2Fragment();
    }
}
