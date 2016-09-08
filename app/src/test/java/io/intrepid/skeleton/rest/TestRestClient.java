package io.intrepid.skeleton.rest;

import io.intrepid.skeleton.rules.MockServerRule;

public class TestRestClient {
    public static GitHubApi getGitHubApi(MockServerRule mockServer) {
        return GitHubRetrofitClient.getTestApi(mockServer.getServerUrl());
    }
}
