package com.integro.sjc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.integro.sjc.adapters.PrincipalMessageAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.PrincipalMessage;
import com.integro.sjc.model.PrincipalMessageList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrincipalMessageActivity extends AppCompatActivity {

    private static final String TAG = "PrincipalMessageActivity";
    RecyclerView rvPrincipal;
    ArrayList<PrincipalMessage> principalMessageArrayList;
    PrincipalMessageAdapter adapter;

    TextView tvTitle;
    TextView tv_description;
    TextView tvQli;
    ImageView ivImage;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_message);

        tvTitle = findViewById(R.id.tvTitle);
        tv_description = findViewById(R.id.tv_description);
        tvQli = findViewById(R.id.tvQli);
        ivImage = findViewById(R.id.ivImage);

        
        rvPrincipal=findViewById(R.id.rvPrincipal);
        principalMessageArrayList=new ArrayList<>();



        getPrincipalMessageList();
    }

    private void getPrincipalMessageList() {
        Call<PrincipalMessageList> messageListCall= ApiClients.getClients().create(ApiServices.class).getPrincipalMessageList();
        messageListCall.enqueue(new Callback<PrincipalMessageList>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<PrincipalMessageList> call, Response<PrincipalMessageList> response) {
                if (!response.isSuccessful()){
                    Log.i(TAG, "onResponse: Response Failed");
                    return;
                }
                if (response.body().getPrincipalMessageArrayList()==null){
                    Log.i(TAG, "onResponse: Response null");
                    return;
                }
                int size = response.body().getPrincipalMessageArrayList().size();
                if (size>principalMessageArrayList.size()-1){
                    principalMessageArrayList.addAll(response.body().getPrincipalMessageArrayList());
                    rvPrincipal.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter=new PrincipalMessageAdapter(getApplicationContext(),principalMessageArrayList);
                    rvPrincipal.setAdapter(adapter);

                    tvTitle.setText(principalMessageArrayList.get(principalMessageArrayList.size()-1).getName());

                    Glide.with(getApplicationContext())
                            .load(principalMessageArrayList.get(principalMessageArrayList.size()-1).getImage())
                            .into(ivImage);

                    tvTitle.setText(principalMessageArrayList.get(principalMessageArrayList.size()-1).getName());
                    tvQli.setText(principalMessageArrayList.get(principalMessageArrayList.size()-1).getQualification());
                    tv_description.setText(principalMessageArrayList.get(principalMessageArrayList.size()-1).getDesignation());
                }
            }

            @Override
            public void onFailure(Call<PrincipalMessageList> call, Throwable t) {

            }
        });
    }
}