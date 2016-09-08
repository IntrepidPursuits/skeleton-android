package io.intrepid.skeleton.rest;

import java.util.List;

import io.intrepid.skeleton.models.GitHubRepo;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubApi {
    @GET("/users/{username}/repos")
    Observable<List<GitHubRepo>> getUserRepos(@Path("username") String username);
}
