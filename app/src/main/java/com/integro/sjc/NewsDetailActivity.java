package com.integro.sjc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.demono.AutoScrollViewPager;
import com.integro.sjc.adapters.NewsImagesAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.News;
import com.integro.sjc.model.NewsImages;
import com.integro.sjc.model.NewsImagesList;
import com.integro.sjc.model.NewsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailActivity extends AppCompatActivity {

    private static final String TAG = "NewsDetailActivity";
    private News news;
    private AutoScrollViewPager vp_news;
    private ArrayList<NewsImages> newsImagesArrayList;
    private NewsImagesAdapter newsImagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        news = (News) getIntent().getSerializableExtra("data");
        vp_news = findViewById(R.id.vp_news);
        newsImagesArrayList = new ArrayList<>();

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvDate = findViewById(R.id.tv_date);
        TextView tvDescription = findViewById(R.id.tv_description);
        TextView tvNewsShare = findViewById(R.id.tvNewsShare);

        tvDate.setText(news.getDate());
        tvTitle.setText(news.getTitle());
        tvDescription.setText(news.getDescription());

        tvNewsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "https://sjc.ac.in/sjc_app/newsshare.php?id="+news.getId());
                startActivity(intent);
            }
        });

        getNewsImagesList();
    }

    public void getNewsImagesList() {
        Call<NewsImagesList> coverPhotosListCall = ApiClients.getClients().create(ApiServices.class).getNewsImagesList(news.getId());
        coverPhotosListCall.enqueue(new Callback<NewsImagesList>() {
            @Override
            public void onResponse(Call<NewsImagesList> call, Response<NewsImagesList> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + "fail");
                    return;
                }
                if (response.body().getNewsImagesArrayList() == null) {
                    Log.i(TAG, "onResponse: " + "null");
                    return;
                }
                int size = response.body().getNewsImagesArrayList().size();
                Log.i(TAG, "onResponse: " + size);
                for (int i = 0; i < size; i++) {
                    newsImagesArrayList.add(response.body().getNewsImagesArrayList().get(i));
                    newsImagesAdapter = new NewsImagesAdapter(getApplicationContext(), newsImagesArrayList);
                    vp_news.setAdapter(newsImagesAdapter);
                    vp_news.startAutoScroll();
                }
            }
            @Override
            public void onFailure(Call<NewsImagesList> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t);
            }
        });
    }
}