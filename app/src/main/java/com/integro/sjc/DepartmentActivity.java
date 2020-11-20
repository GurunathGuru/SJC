package com.integro.sjc;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.integro.sjc.adapters.DepartmentAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.Department;
import com.integro.sjc.model.DepartmentList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DepartmentActivity extends AppCompatActivity {

    private RecyclerView rvDepartment;
    private DepartmentAdapter departmentAdapter;
    private ArrayList<Department> departmentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        rvDepartment = findViewById(R.id.rv_dept);
        departmentArrayList = new ArrayList<>();
        getDepartmentList();
    }

    public void getDepartmentList() {
        Call<DepartmentList> departmentListCall = ApiClients.getClients().create(ApiServices.class).getDepartmentList();
        departmentListCall.enqueue(new Callback<DepartmentList>() {
            @Override
            public void onResponse(Call<DepartmentList> call, Response<DepartmentList> response) {
                if (response.isSuccessful()) {
                    int size = response.body().getDepartmentArrayList().size();
                    Log.i(TAG, "onResponse: size " + size);
                    if (size > 0) {
                        departmentArrayList.addAll(response.body().getDepartmentArrayList());
                        rvDepartment.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
                        departmentAdapter = new DepartmentAdapter(getApplicationContext(), departmentArrayList);
                        rvDepartment.setAdapter(departmentAdapter);
                        rvDepartment.setHasFixedSize(true);
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DepartmentList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure:" + t.getMessage());
            }
        });
    }
}