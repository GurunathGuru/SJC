package com.integro.sjc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.sjc.adapters.PlacementAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.Placement;
import com.integro.sjc.model.PlacementList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacementActivity extends AppCompatActivity {

    private RecyclerView rvPlacement;
    private ArrayList<Placement> placementArrayList;
    private PlacementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);

        rvPlacement=findViewById(R.id.rvPlacement);
        placementArrayList=new ArrayList<>();
        getPlacementList();
    }

    public void getPlacementList(){
        Call<PlacementList> call= ApiClients.getClients().create(ApiServices.class).getPlacementList();
        call.enqueue(new Callback<PlacementList>() {
            @Override
            public void onResponse(Call<PlacementList> call, Response<PlacementList> response) {
                if (!response.isSuccessful()){
                    return;
                }
                if (response.body().getPlacementArrayList()==null){
                    return;
                }
                int size=response.body().getPlacementArrayList().size();
                if (size>0){
                    placementArrayList.addAll(response.body().getPlacementArrayList());
                    rvPlacement.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter=new PlacementAdapter(getApplicationContext(),placementArrayList);
                    rvPlacement.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<PlacementList> call, Throwable t) {

            }
        });
    }
}