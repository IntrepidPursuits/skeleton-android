package io.intrepid.skeleton.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.intrepid.skeleton.R;

/**
 * Base class for activities whose sole purpose to to host a fragment. Child classes simply need
 * to override {@link #createFragment(Intent)} and create the appropriate Fragment. If the activity
 * contains any additional logic, use {@link BaseMvpActivity} instead
 */
public abstract class BaseFragmentActivity extends BaseActivity {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_fragment_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
