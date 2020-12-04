package com.example.android.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.github.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView repoListView  = (ListView)findViewById(R.id.repo_list);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        //create actual retrofit object..
        Retrofit retrofit = builder.build();

        //create rest client instance
        GitHubClient restClient = retrofit.create(GitHubClient.class);

        //Call rest client apis using rest client instance
        Call<List<GitHubRepo>> call = restClient.repositoryForUser("futurestudio");

        //Calling enqueue over call object to run network call asynchronously and declare call backs to handle response on UI thread.
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repoList = response.body();
                //updating the ListView with the repository received from web service..
                repoListView.setAdapter(new GitHubRepoAdapter(MainActivity.this,repoList));

            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"error in getting repository list",Toast.LENGTH_LONG).show();
            }
        });
    }
}