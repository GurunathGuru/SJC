package com.integro.sjc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.github.demono.AutoScrollViewPager;
import com.integro.sjc.EventsActivity;
import com.integro.sjc.R;
import com.integro.sjc.WebActivity;
import com.integro.sjc.adapters.AlumniAdapter;
import com.integro.sjc.adapters.GratitudeAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.DistinguishAlumni;
import com.integro.sjc.model.DistinguishAlumniList;
import com.integro.sjc.model.Gratitude;
import com.integro.sjc.model.GratitudeList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class NewsFragment extends Fragment {

    private AutoScrollViewPager vpGratitude;
    private ArrayList<Gratitude> gratitudeArrayList;
    private GratitudeAdapter adapter;

    private AutoScrollViewPager vpAlumni;
    private ArrayList<DistinguishAlumni> distinguishAlumniArrayList;
    private AlumniAdapter alumniAdapter;
    private String flog;
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        flog = "fromFragment";
        vpGratitude = view.findViewById(R.id.vpGratitude);
        gratitudeArrayList = new ArrayList<>();

        vpAlumni = view.findViewById(R.id.vpAlumni);
        distinguishAlumniArrayList = new ArrayList<>();

        TextView tvEvents = view.findViewById(R.id.tvEvents);
        TextView tvOutReach = view.findViewById(R.id.tvOutReach);
        TextView tvResearch = view.findViewById(R.id.tvResearch);
        TextView tvAssociations = view.findViewById(R.id.tvAssociations);
        TextView tvStudentUnion = view.findViewById(R.id.tvStudentUnion);
        TextView tvAchievements = view.findViewById(R.id.tvAchievements);
        LinearLayout llSupport = view.findViewById(R.id.llSupport);

        tvEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), EventsActivity.class);
                startActivity(intent);
            }
        });

        tvOutReach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("TAG", "https://www.sjc.ac.in/outreach.php");
                startActivity(intent);
            }
        });

        tvResearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("TAG", "https://www.sjc.ac.in/research.php");
                startActivity(intent);
            }
        });

        tvStudentUnion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("TAG", "https://www.sjc.ac.in/studentunion.php");
                startActivity(intent);
            }
        });

        tvAssociations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("TAG", "https://www.sjc.ac.in/association.php");
                startActivity(intent);
            }
        });

        tvAchievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        llSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        getGratitudeList();
        getDistinguishAlumniListList();
        return view;
    }

    public void getGratitudeList() {
        Call<GratitudeList> gratitudeListCall = (Call<GratitudeList>) ApiClients.getClients().create(ApiServices.class).getGratitudeList();
        gratitudeListCall.enqueue(new Callback<GratitudeList>() {
            @Override
            public void onResponse(Call<GratitudeList> call, Response<GratitudeList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body().getGratitudeArrayList() == null) {
                    return;
                }
                int size = response.body().getGratitudeArrayList().size();
                Log.i(TAG, "onResponse from home page: size " + size);

                if (size > 0) {
                    gratitudeArrayList.addAll(response.body().getGratitudeArrayList());
                    adapter = new GratitudeAdapter(getContext(), gratitudeArrayList, flog);
                    vpGratitude.setAdapter(adapter);
                    vpGratitude.startAutoScroll(3000);
                    vpGratitude.setCycle(true);
                }
            }

            @Override
            public void onFailure(Call<GratitudeList> call, Throwable t) {

            }
        });
    }

    public void getDistinguishAlumniListList() {
        Call<DistinguishAlumniList> gratitudeListCall = (Call<DistinguishAlumniList>) ApiClients.getClients().create(ApiServices.class).getDistinguishAlumniList();
        gratitudeListCall.enqueue(new Callback<DistinguishAlumniList>() {
            @Override
            public void onResponse(Call<DistinguishAlumniList> call, Response<DistinguishAlumniList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body().getDistinguishAlumniArrayList() == null) {
                    return;
                }
                int size = response.body().getDistinguishAlumniArrayList().size();
                Log.i(TAG, "onResponse from home page: size " + size);

                if (size > 0) {
                    distinguishAlumniArrayList.addAll(response.body().getDistinguishAlumniArrayList());
                    alumniAdapter = new AlumniAdapter(getContext(), distinguishAlumniArrayList);
                    vpAlumni.setAdapter(alumniAdapter);
                    vpAlumni.startAutoScroll(3000);
                    vpAlumni.setCycle(true);
                }
            }

            @Override
            public void onFailure(Call<DistinguishAlumniList> call, Throwable t) {

            }
        });
    }
}