package io.intrepid.skeleton.screens.gitHubUserRepos;

import java.util.List;

import io.intrepid.skeleton.base.BaseContract;
import io.intrepid.skeleton.models.GitHubRepo;

public class GitHubUserReposContract {
    public interface View extends BaseContract.View {
        String getUsernameInput();

        void updateReposDisplay();

        void hideKeyboard();

        void showUserAvatar(String avatarUrl);

        void hideUserAvatar();
    }

    public interface Presenter extends BaseContract.Presenter<View>, GitHubUserReposAdapter.OnSelectListener {
        List<GitHubRepo> getRepos();

        void showReposButtonClicked();
    }
}
