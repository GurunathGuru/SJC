package com.integro.sjc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.integro.sjc.adapters.AlumniAdapter;
import com.integro.sjc.adapters.AlumniRecyclerAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.DistinguishAlumni;
import com.integro.sjc.model.DistinguishAlumniList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlumniActivity extends AppCompatActivity {

    RecyclerView rvAlumni;
    AlumniRecyclerAdapter adapter;
    ArrayList<DistinguishAlumni> distinguishAlumniArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni);

        rvAlumni=findViewById(R.id.rvAlumni);
        distinguishAlumniArrayList=new ArrayList<>();

        getAlumniList();
    }

    private void getAlumniList() {
        Call<DistinguishAlumniList> distinguishAlumniCall= ApiClients.getClients().create(ApiServices.class).getDistinguishAlumniList();
        distinguishAlumniCall.enqueue(new Callback<DistinguishAlumniList>() {
            @Override
            public void onResponse(Call<DistinguishAlumniList> call, Response<DistinguishAlumniList> response) {
                if (!response.isSuccessful()){
                    return;
                }
                if (response.body().getDistinguishAlumniArrayList()==null){
                    return;
                }
                int size=response.body().getDistinguishAlumniArrayList().size();

                if (size>0){

                    distinguishAlumniArrayList.addAll(response.body().getDistinguishAlumniArrayList());
                    adapter=new AlumniRecyclerAdapter(getApplicationContext(),distinguishAlumniArrayList);
                    rvAlumni.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvAlumni.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<DistinguishAlumniList> call, Throwable t) {

            }
        });
    }
}