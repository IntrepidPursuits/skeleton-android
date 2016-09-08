package io.intrepid.skeleton.screens.gitHubUserRepos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.intrepid.skeleton.R;
import io.intrepid.skeleton.models.GitHubRepo;

public class GitHubUserReposAdapter extends RecyclerView.Adapter<GitHubUserReposAdapter.ViewHolder> {
    private static final String DATE_FORMAT_REPO_CREATED_AT = "MMM d, yyyy";

    private Context context;
    private List<GitHubRepo> repos;
    private OnSelectListener listener;

    public GitHubUserReposAdapter(Context context, List<GitHubRepo> repos, OnSelectListener listener) {
        this.context = context;
        this.repos = repos;
        this.listener = listener;
    }

    @Override
    public GitHubUserReposAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.git_hub_repos_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GitHubUserReposAdapter.ViewHolder holder, int position) {
        holder.configureViews(repos.get(position));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public interface OnSelectListener {
        void repoSelected(GitHubRepo gitHubRepo);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.repo_name)
        TextView repoNameView;
        @BindView(R.id.created_at)
        TextView dateCreatedView;

        private GitHubRepo repo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> listener.repoSelected(ViewHolder.this.repo));
        }

        public void configureViews(GitHubRepo repo) {
            this.repo = repo;

            repoNameView.setText(repo.getName());
            dateCreatedView.setText(getCreatedAtText(repo));
        }

        public String getCreatedAtText(GitHubRepo repo) {
            SimpleDateFormat createdAtDateFormat = new SimpleDateFormat(DATE_FORMAT_REPO_CREATED_AT,
                                                                        context.getResources().getConfiguration().locale);
            return context.getString(R.string.git_hub_repo_created_at, createdAtDateFormat.format(repo.getCreatedAt()));
        }
    }
}
