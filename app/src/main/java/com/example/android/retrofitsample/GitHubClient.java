package com.example.android.retrofitsample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubClient {

    @GET("/users/{name}/repos")
    Call<List<GitHubRepo>> repositoryForUser(@Path("name") String userName);
}
