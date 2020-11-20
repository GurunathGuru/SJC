package com.integro.sjc;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.sjc.adapters.OurCoursesAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.OurCourses;
import com.integro.sjc.model.OurCoursesList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class OurCoursesActivity extends AppCompatActivity {

    private RecyclerView rvOurCourses;
    private OurCoursesAdapter ourCoursesAdapter;
    private ArrayList<OurCourses> ourCoursesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_courses);

        rvOurCourses = findViewById(R.id.rv_OurCourses);
        ourCoursesArrayList = new ArrayList<>();
        getOurCoursesList();
    }

    public void getOurCoursesList() {
        Call<OurCoursesList> ourCoursesListCall = ApiClients.getClients().create(ApiServices.class).getOurCoursesList();
        ourCoursesListCall.enqueue(new Callback<OurCoursesList>() {
            @Override
            public void onResponse(Call<OurCoursesList> call, Response<OurCoursesList> response) {
                Log.i("RESONSE", "" + response.isSuccessful());
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        int size = response.body().getOurCoursesArrayList().size();
                        if (size > 0) {
                            ourCoursesArrayList.addAll(response.body().getOurCoursesArrayList());
                            rvOurCourses.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rvOurCourses.setHasFixedSize(true);
                            ourCoursesAdapter = new OurCoursesAdapter(getApplicationContext(), ourCoursesArrayList);
                            rvOurCourses.setAdapter(ourCoursesAdapter);
                        } else {
                            Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<OurCoursesList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());
            }
        });
    }
}
