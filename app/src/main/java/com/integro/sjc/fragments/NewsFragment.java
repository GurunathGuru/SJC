package com.integro.sjc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.integro.sjc.EventsActivity;
import com.integro.sjc.R;
import com.integro.sjc.WebActivity;

public class NewsFragment extends Fragment {

    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        TextView tvEvents = view.findViewById(R.id.tvEvents);
        TextView tvOutReach = view.findViewById(R.id.tvOutReach);
        TextView tvResearch = view.findViewById(R.id.tvResearch);
        TextView tvAssociations = view.findViewById(R.id.tvAssociations);
        TextView tvStudentUnion = view.findViewById(R.id.tvStudentUnion);
        TextView tvAchievements = view.findViewById(R.id.tvAchievements);
        TextView tvSupport = view.findViewById(R.id.tvSupport);

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
                intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("TAG", "https://www.example.com");
                startActivity(intent);
            }
        });

        tvSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("TAG", "https://www.example.com");
                startActivity(intent);
            }
        });
        return view;
    }
}