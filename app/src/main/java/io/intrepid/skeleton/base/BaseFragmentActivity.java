package io.intrepid.skeleton.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.intrepid.skeleton.R;

public abstract class BaseFragmentActivity extends BaseActivity {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_fragment_container;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Fragment fragment = createFragment(intent);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    protected abstract Fragment createFragment(Intent intent);
}
