package com.integro.sjc;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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

    int position;
    String itemId;
    ImageView ivImage;
    TextView tvTitle;
    TextView tvDescription;
    ArrayList<Department2> department2ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_department);

        itemId = getIntent().getStringExtra("itemId");
        position = getIntent().getIntExtra("position", 0) - 1;
        department2ArrayList = new ArrayList<>();

        ivImage = findViewById(R.id.ivImage);
        tvTitle = findViewById(R.id.tv_title);
        tvDescription = findViewById(R.id.tv_description);

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
                    tvTitle.setText(department2ArrayList.get(position).getTitle());
                    tvDescription.setText(department2ArrayList.get(position).getDescription());
                    Glide.with(getApplicationContext())
                            .load(department2ArrayList.get(position).getImage())
                            .into(ivImage);
                } else {
                    finish();
                    Toast.makeText(DepartmentActivity2.this, "Data not Available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Department2List> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t);
            }
        });
    }
}