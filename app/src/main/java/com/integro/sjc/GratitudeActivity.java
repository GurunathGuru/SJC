package com.integro.sjc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.demono.AutoScrollViewPager;
import com.google.android.material.tabs.TabLayout;
import com.integro.sjc.adapters.GratitudeAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.Gratitude;
import com.integro.sjc.model.GratitudeList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class GratitudeActivity extends AppCompatActivity {

    AutoScrollViewPager vpGratitude;
    ArrayList<Gratitude> gratitudeArrayList;
    GratitudeAdapter adapter;
    String flog;
    TextView tv_description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratitude);

        tv_description=findViewById(R.id.tv_description);
        flog="fromActivity";

        vpGratitude = findViewById(R.id.vpGratitude);
        gratitudeArrayList = new ArrayList<>();

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vpGratitude, true);


        getGratitudeList();
    }
    public void getGratitudeList() {
        Call<GratitudeList> gratitudeListCall = (Call<GratitudeList>) ApiClients.getClients().create(ApiServices.class).getGratitudeList();
        gratitudeListCall.enqueue(new Callback<GratitudeList>() {
            @Override
            public void onResponse(Call<GratitudeList> call, Response<GratitudeList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body().getGratitudeArrayList() == null) {
                    return;
                }
                int size = response.body().getGratitudeArrayList().size();
                Log.i(TAG, "onResponse from home page: size " + size);

                if (size > 0) {
                    gratitudeArrayList.addAll(response.body().getGratitudeArrayList());
                    adapter=new GratitudeAdapter(getApplicationContext(),gratitudeArrayList,flog);
                    vpGratitude.setAdapter(adapter);
                    vpGratitude.startAutoScroll(3000);
                    vpGratitude.setCycle(true);
                    tv_description.setText(gratitudeArrayList.get(0).getDescription());
                }
            }

            @Override
            public void onFailure(Call<GratitudeList> call, Throwable t) {

            }
        });
    }
}