package io.intrepid.skeleton.models;

import com.google.gson.annotations.SerializedName;

public class GitHubOwner {
    @SerializedName("login")
    private String loginName;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public GitHubOwner() {
    }

    public GitHubOwner(String loginName, String avatarUrl) {
        this.loginName = loginName;
        this.avatarUrl = avatarUrl;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
