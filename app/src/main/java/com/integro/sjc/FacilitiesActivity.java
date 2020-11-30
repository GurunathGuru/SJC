package com.integro.sjc;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.sjc.adapters.FacilitiesAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.Facilities;
import com.integro.sjc.model.FacilitiesList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FacilitiesActivity extends AppCompatActivity {
    ApiServices apiServices;
    RecyclerView rvFacilities;
    LinearLayoutManager manager;
    FacilitiesAdapter facilitiesAdapter;
    ArrayList<Facilities> facilitiesArrayList;
    Call<FacilitiesList> facilitiesListCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvFacilities = findViewById(R.id.rv_Facilities);
        manager = new LinearLayoutManager(this);
        rvFacilities.setLayoutManager(manager);
        facilitiesArrayList = new ArrayList<>();
        getFacilitiesList();

    }

    public void getFacilitiesList() {

        facilitiesListCall = apiServices.getFacilitiesList();
        facilitiesListCall.enqueue(new Callback<FacilitiesList>() {
            @Override
            public void onResponse(Call<FacilitiesList> call, Response<FacilitiesList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getFacilitiesArrayList() != null) {
                        int size = response.body().getFacilitiesArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);
                        facilitiesArrayList.addAll(response.body().getFacilitiesArrayList());
                        if (facilitiesArrayList.size() > 0) {
                            facilitiesAdapter = new FacilitiesAdapter(getApplicationContext(), facilitiesArrayList);
                            rvFacilities.setAdapter(facilitiesAdapter);
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
            public void onFailure(Call<FacilitiesList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });

    }
}
