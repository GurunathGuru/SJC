package com.integro.sjc;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.sjc.adapters.EventsAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.Events;
import com.integro.sjc.model.EventsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class EventsActivity extends AppCompatActivity {

     RecyclerView rvEvents;
     ArrayList<Events> eventsArrayList;
     EventsAdapter eventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        rvEvents = findViewById(R.id.rvEvents);
        eventsArrayList = new ArrayList<>();
        getEventsSjcList();
    }

    public void getEventsSjcList() {
        Call<EventsList> eventsListCall = ApiClients.getClients().create(ApiServices.class).getEventsList();
        eventsListCall.enqueue(new Callback<EventsList>() {
            @Override
            public void onResponse(Call<EventsList> call, Response<EventsList> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: success");
                    if (response.body().getEventsArrayList()!= null) {
                        Log.i(TAG, "onResponse: guru");
                        Log.i(TAG, "onResponse: size "+response.body().getEventsArrayList().size());
                        int size = response.body().getEventsArrayList().size();
                        if (size > 0) {
                            eventsArrayList.addAll(response.body().getEventsArrayList());
                            rvEvents.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            eventsAdapter = new EventsAdapter(getApplicationContext(), eventsArrayList);
                            rvEvents.setAdapter(eventsAdapter);
                        } else {
                            Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<EventsList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());
            }
        });
    }
}