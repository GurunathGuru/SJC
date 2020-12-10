package com.integro.sjc;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.sjc.adapters.Department2Adapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.Department2;
import com.integro.sjc.model.Department2List;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentActivity2 extends AppCompatActivity {

    private static final String TAG = "DepartmentActivity2";

    RecyclerView rvDep2;
    int position;
    String itemId;
    Department2Adapter departmentAdapter;
    ArrayList<Department2> department2ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_department);

        department2ArrayList = new ArrayList<>();
        rvDep2 = findViewById(R.id.rv_dept2);

        itemId = (String) getIntent().getSerializableExtra("itemId");
        position = (int) getIntent().getSerializableExtra("position");

        getDepart2();
    }

    public void getDepart2() {
        Call<Department2List> department2ListCall = ApiClients.getClients().create(ApiServices.class).getDepartment2List(itemId);
        Log.i(TAG, "onResponse: itemId " + itemId);
        department2ListCall.enqueue(new Callback<Department2List>() {
            @Override
            public void onResponse(Call<Department2List> call, Response<Department2List> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + "fail");
                    return;
                }
                if (response.body().getDepartment2ArrayList() == null) {
                    Log.i(TAG, "onResponse: " + "null");
                    return;
                }
                int size = response.body().getDepartment2ArrayList().size();
                Log.i(TAG, "onResponse: size " + size);
                if (size > 0) {
                    department2ArrayList.addAll(response.body().getDepartment2ArrayList());
                    rvDep2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    departmentAdapter = new Department2Adapter(getApplicationContext(), department2ArrayList);
                    rvDep2.setAdapter(departmentAdapter);
                    rvDep2.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<Department2List> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t);
            }
        });
    }
}