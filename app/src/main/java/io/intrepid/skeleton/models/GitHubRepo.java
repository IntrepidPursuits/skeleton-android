package io.intrepid.skeleton.models;

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GitHubRepo {
    @SerializedName("name")
    private String name;
    @SerializedName("owner")
    private GitHubOwner owner;
    @SerializedName("created_at")
    private Date createdAt;

    public GitHubRepo() {
    }

    @VisibleForTesting
    public GitHubRepo(String name, GitHubOwner owner, Date createdAt) {
        this.name = name;
        this.owner = owner;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public GitHubOwner getOwner() {
        return owner;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
