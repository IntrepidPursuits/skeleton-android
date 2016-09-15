package io.intrepid.skeleton.screens.gitHubUserRepos;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import io.intrepid.skeleton.SkeletonApplication;
import io.intrepid.skeleton.base.BaseEventBusPresenter;
import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.events.OnThrowableEvent;
import io.intrepid.skeleton.models.GitHubRepo;
import io.intrepid.skeleton.rest.GitHubApi;
import io.intrepid.skeleton.utils.StringUtils;
import timber.log.Timber;

public class GitHubUserReposPresenter extends BaseEventBusPresenter<GitHubUserReposContract.View> implements GitHubUserReposContract.Presenter {
    private GitHubApi gitHubApi;
    private List<GitHubRepo> repos;

    public GitHubUserReposPresenter(@NonNull GitHubUserReposContract.View view,
                                    @NonNull PresenterConfiguration configuration,
                                    @NonNull GitHubApi gitHubApi,
                                    @NonNull List<GitHubRepo> repos) {
        super(view, configuration);

        this.gitHubApi = gitHubApi;
        this.repos = repos;
    }

    @Override
    public List<GitHubRepo> getRepos() {
        return repos;
    }

    @Override
    public void showReposButtonClicked() {
        loadRepos();
    }

    @Override
    public void repoSelected(GitHubRepo gitHubRepo) {
        Timber.d("You selected repository: %s", gitHubRepo.getName());
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onEvent(OnThrowableEvent onThrowableEvent) {
        Timber.e(onThrowableEvent.getThrowable());
    }

    private void loadRepos() {
        gitHubApi.getUserRepos(view.getUsernameInput())
                .compose(subscribeOnIoObserveOnUi())
                .subscribe(this::updateRepos,
                           this::errorLoadingRepos);
    }

    private void updateRepos(List<GitHubRepo> repos) {
        this.repos.clear();
        if (repos != null) {
            this.repos.addAll(repos);
        }

        refreshUi();
    }

    private void refreshUi() {
        if (repos != null && !repos.isEmpty()) {
            String avatarUrl = repos.get(0).getOwner().getAvatarUrl();
            if (!StringUtils.isEmpty(avatarUrl)) {
                view.showUserAvatar(avatarUrl);
            } else {
                view.hideUserAvatar();
            }
        } else {
            view.hideUserAvatar();
        }
        view.updateReposDisplay();
        view.hideKeyboard();
    }

    private void errorLoadingRepos(Throwable t) {
        SkeletonApplication.bus.post(new OnThrowableEvent(t));
    }
}
