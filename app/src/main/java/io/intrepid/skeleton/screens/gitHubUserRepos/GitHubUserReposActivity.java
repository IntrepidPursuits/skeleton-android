package io.intrepid.skeleton.screens.gitHubUserRepos;

import android.content.Intent;
import android.support.v4.app.Fragment;

import io.intrepid.skeleton.base.BaseFragmentActivity;

public class GitHubUserReposActivity extends BaseFragmentActivity {
    @Override
    protected Fragment createFragment(Intent intent) {
        return GitHubUserReposFragment.newInstance();
    }
}
