package io.intrepid.skeleton.screens.demoSelection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import io.intrepid.skeleton.R;
import io.intrepid.skeleton.base.BaseMvpActivity;
import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.screens.gitHubUserRepos.GitHubUserReposActivity;

public class DemoSelectionActivity extends BaseMvpActivity<DemoSelectionContract.Presenter> implements DemoSelectionContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView libDemosRecyclerView;

    @NonNull
    @Override
    public DemoSelectionContract.Presenter createPresenter(PresenterConfiguration configuration) {
        return new DemoSelectionPresenter(this, configuration);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_demo_selection;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);

        libDemosRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        libDemosRecyclerView.setLayoutManager(linearLayoutManager);

        DemoSelectionAdapter adapter = new DemoSelectionAdapter(this, presenter);
        libDemosRecyclerView.setAdapter(adapter);
    }

    @Override
    public void gotoGitHubUserRepos() {
        Intent intent = new Intent(this, GitHubUserReposActivity.class);
        startActivity(intent);
    }
}
