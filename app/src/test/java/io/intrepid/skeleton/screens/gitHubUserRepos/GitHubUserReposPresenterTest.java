package io.intrepid.skeleton.screens.gitHubUserRepos;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.intrepid.skeleton.models.GitHubOwner;
import io.intrepid.skeleton.models.GitHubRepo;
import io.intrepid.skeleton.rest.GitHubApi;
import io.intrepid.skeleton.testutils.TestPresenterUtils;
import rx.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GitHubUserReposPresenterTest {
    static final String GITHUB_REPO_NAME = "cms-sandbox";
    static final String GITHUB_USERNAME = "weathersboyle";
    static final String GITHUB_USER_AVATAR_URL = "https://avatars.githubusercontent.com/u/7977903?v=3";
    static final Date GITHUB_REPO_CREATED_AT = new Date();

    @Mock
    GitHubUserReposContract.View mockView;
    @Mock
    GitHubApi gitHubApi;

    GitHubUserReposPresenter presenter;
    List<GitHubRepo> repos = new ArrayList<>();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new GitHubUserReposPresenter(mockView,
                                                 TestPresenterUtils.createTestConfiguration(),
                                                 gitHubApi,
                                                 repos);
        when(mockView.getUsernameInput()).thenReturn(GITHUB_USERNAME);
    }

    @Test
    public void testShowReposButtonClicked_noReposAvailable() {
        List<GitHubRepo> repos = new ArrayList<>();
        when(gitHubApi.getUserRepos(anyString())).thenReturn(Observable.just(repos));

        presenter.showReposButtonClicked();
        verify(gitHubApi).getUserRepos(GITHUB_USERNAME);
        verify(mockView, never()).showUserAvatar(anyString());
        verify(mockView).hideUserAvatar();
        verify(mockView).hideKeyboard();
        verify(mockView).updateReposDisplay();
    }

    @Test
    public void testShowReposButtonClicked_reposAvailable() {
        List<GitHubRepo> repos = new ArrayList<>();
        repos.add(newGitHubRepo());
        when(gitHubApi.getUserRepos(anyString())).thenReturn(Observable.just(repos));

        presenter.showReposButtonClicked();
        verify(gitHubApi).getUserRepos(GITHUB_USERNAME);
        verify(mockView).showUserAvatar(GITHUB_USER_AVATAR_URL);
        verify(mockView, never()).hideUserAvatar();
        verify(mockView).hideKeyboard();
        verify(mockView).updateReposDisplay();
    }

    private GitHubRepo newGitHubRepo() {
        return new GitHubRepo(GITHUB_REPO_NAME, newGitHubOwner(), GITHUB_REPO_CREATED_AT);
    }

    private GitHubOwner newGitHubOwner() {
        return new GitHubOwner(GITHUB_USERNAME, GITHUB_USER_AVATAR_URL);
    }
}
