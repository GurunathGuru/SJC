package com.integro.sjc;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.sjc.adapters.AboutSjcAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.AboutSjc;
import com.integro.sjc.model.AboutSjcList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AboutSjcActivity extends AppCompatActivity {

    private RecyclerView rvAboutSjc;
    private AboutSjcAdapter aboutSjcAdapter;
    private ArrayList<AboutSjc> aboutSjcArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_sjc);

        rvAboutSjc = findViewById(R.id.rv_aboutSjc);
        aboutSjcArrayList = new ArrayList<>();
        getAboutSjcList();
    }

    public void getAboutSjcList() {
        Call<AboutSjcList> aboutSjcListCall = ApiClients.getClients().create(ApiServices.class).getAboutSjcList();
        aboutSjcListCall.enqueue(new Callback<AboutSjcList>() {
            @Override
            public void onResponse(Call<AboutSjcList> call, Response<AboutSjcList> response) {
                Log.i("RESONSE", "" + response.isSuccessful());
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        int size = response.body().getAboutSjcArrayList().size();
                        if (size > 0) {
                            aboutSjcArrayList.addAll(response.body().getAboutSjcArrayList());
                            rvAboutSjc.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rvAboutSjc.setHasFixedSize(true);
                            aboutSjcAdapter = new AboutSjcAdapter(getApplicationContext(), aboutSjcArrayList);
                            rvAboutSjc.setAdapter(aboutSjcAdapter);
                        } else {
                            Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<AboutSjcList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());
            }
        });
    }
}
