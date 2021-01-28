package com.integro.sjc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.sjc.adapters.AboutSjcAdapter;
import com.integro.sjc.adapters.NewsLetterAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.AboutSjc;
import com.integro.sjc.model.AboutSjcList;
import com.integro.sjc.model.NewsLetter;
import com.integro.sjc.model.NewsLetterList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class NewsLetterActivity extends AppCompatActivity {

    private RecyclerView rvNewsLetter;
    private NewsLetterAdapter newsLetterAdapter;
    private ArrayList<NewsLetter> newsLetterArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_letter);

        rvNewsLetter = findViewById(R.id.rvNewsLetter);
        newsLetterArrayList = new ArrayList<>();
        getNewsLetterList();
    }

    public void getNewsLetterList() {
        Call<NewsLetterList> newsLetterListCall = ApiClients.getClients().create(ApiServices.class).getNewsLetterList();
        newsLetterListCall.enqueue(new Callback<NewsLetterList>() {
            @Override
            public void onResponse(Call<NewsLetterList> call, Response<NewsLetterList> response) {
                Log.i("RESONSE", "" + response.isSuccessful());
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body().getNewsLetterArrayList() == null) {
                    return;
                }
                int size = response.body().getNewsLetterArrayList().size();
                if (size > 0) {
                    newsLetterArrayList.addAll(response.body().getNewsLetterArrayList());
                    rvNewsLetter.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    newsLetterAdapter = new NewsLetterAdapter(getApplicationContext(), newsLetterArrayList);
                    rvNewsLetter.setAdapter(newsLetterAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsLetterList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());
            }
        });
    }
}