package com.integro.sjc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.sjc.adapters.AnnouncementsAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.Announcements;
import com.integro.sjc.model.AnnouncementsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AnnouncementsActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvAnnouncements;
    LinearLayoutManager manager;
    AnnouncementsAdapter announcementsAdapter;
    ArrayList<Announcements> announcementsArrayList;
    Call<AnnouncementsList> announcementsListCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvAnnouncements=findViewById(R.id.rv_Announcements);
        manager = new LinearLayoutManager(this);
        rvAnnouncements.setLayoutManager(manager);
        announcementsArrayList = new ArrayList<>();
        getAnnouncementsList();
    }
    public void getAnnouncementsList(){

        String date = "2020-07-06 01:59:02";
        announcementsListCall = apiServices.getAnnouncementsList(date);
        announcementsListCall.enqueue(new Callback<AnnouncementsList>() {
            @Override
            public void onResponse(Call<AnnouncementsList> call, Response<AnnouncementsList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getAnnouncementsArrayList() != null) {
                        int size = response.body().getAnnouncementsArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);
                        announcementsArrayList.addAll(response.body().getAnnouncementsArrayList());
                        if (announcementsArrayList.size() > 0) {
                            announcementsAdapter = new AnnouncementsAdapter(getApplicationContext(),announcementsArrayList);
                            rvAnnouncements.setAdapter(announcementsAdapter);
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<AnnouncementsList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });

    }
}

