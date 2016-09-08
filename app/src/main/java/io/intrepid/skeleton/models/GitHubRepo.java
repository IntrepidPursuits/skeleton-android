package io.intrepid.skeleton.models;

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
