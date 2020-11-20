package com.integro.sjc;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.integro.sjc.adapters.NewsAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.News;
import com.integro.sjc.model.NewsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.LinearLayout.VERTICAL;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class NewsActivity extends AppCompatActivity {

    NewsAdapter newsAdapter;
    ArrayList<News> newsArrayList;
    private RecyclerView rvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        rvNews = findViewById(R.id.rv_news);
        newsArrayList = new ArrayList<>();

        getNewsList();
    }

    public void getNewsList() {
        Call<NewsList> newsListCall = ApiClients.getClients().create(ApiServices.class).getNewsList();
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "onResponse: fail");
                    return;
                }
                if (response.body().getNewsArrayList() == null) {
                    Log.i(TAG, "onResponse: null");
                    return;
                }
                int size = response.body().getNewsArrayList().size();
                if (size > 0) {
                    newsArrayList.addAll(response.body().getNewsArrayList());
                    rvNews.setLayoutManager(new StaggeredGridLayoutManager(2,VERTICAL));
                    rvNews.setHasFixedSize(true);
                    newsAdapter = new NewsAdapter(getApplicationContext(), newsArrayList);
                    rvNews.setAdapter(newsAdapter);
                } else {
                    finish();
                    Toast.makeText(getApplicationContext(), "Data not Available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());
            }
        });
    }
}