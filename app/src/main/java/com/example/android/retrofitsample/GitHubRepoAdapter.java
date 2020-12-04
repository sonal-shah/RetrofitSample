package com.example.android.retrofitsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class GitHubRepoAdapter extends ArrayAdapter<GitHubRepo> {

    private Context context;
    private List<GitHubRepo> repoList;
    public GitHubRepoAdapter(@NonNull Context context, List<GitHubRepo> repoList) {
        super(context, R.layout.repo_listitem, repoList);
        this.context = context;
        this.repoList = repoList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listItemView = inflater.inflate(R.layout.repo_listitem, parent, false);
        }

        GitHubRepo repoItem = repoList.get(position);
        TextView repo_name_text = (TextView)listItemView.findViewById(R.id.repo_name_text);
        repo_name_text.setText(repoItem.getName());

        return listItemView;
    }
}
