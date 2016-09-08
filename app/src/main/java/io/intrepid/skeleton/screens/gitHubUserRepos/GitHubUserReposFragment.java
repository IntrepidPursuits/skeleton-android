package io.intrepid.skeleton.screens.gitHubUserRepos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import io.intrepid.skeleton.R;
import io.intrepid.skeleton.base.BaseFragment;
import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.rest.GitHubRetrofitClient;

public class GitHubUserReposFragment extends BaseFragment<GitHubUserReposContract.Presenter> implements GitHubUserReposContract.View {
    @BindView(R.id.username_input)
    EditText usernameInputView;
    @BindView(R.id.avatar)
    ImageView avatarView;
    @BindView(R.id.recyclerView)
    RecyclerView reposRecyclerView;

    private GitHubUserReposAdapter reposAdapter;

    public static GitHubUserReposFragment newInstance() {
        return new GitHubUserReposFragment();
    }

    public GitHubUserReposFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reposRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reposRecyclerView.setLayoutManager(linearLayoutManager);

        reposAdapter = new GitHubUserReposAdapter(getContext(), presenter.getRepos(), presenter);
        reposRecyclerView.setAdapter(reposAdapter);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_git_hub_user_repos;
    }

    @NonNull
    @Override
    public GitHubUserReposContract.Presenter createPresenter(PresenterConfiguration configuration) {
        return new GitHubUserReposPresenter(this, configuration, GitHubRetrofitClient.getApi());
    }

    @OnClick(R.id.show_repos_button)
    public void showReposButtonClicked() {
        presenter.showReposButtonClicked();
    }

    @Override
    public String getUsernameInput() {
        return usernameInputView.getText().toString();
    }

    @Override
    public void updateReposDisplay() {
        reposAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showUserAvatar(String avatarUrl) {
        int avatarSideDimen = getContext().getResources().getDimensionPixelSize(R.dimen.git_hub_avatar_side);
        Picasso.with(getContext())
                .load(avatarUrl)
                .resize(avatarSideDimen, avatarSideDimen)
                .centerCrop()
                .into(avatarView);
        avatarView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUserAvatar() {
        avatarView.setVisibility(View.GONE);
    }
}
