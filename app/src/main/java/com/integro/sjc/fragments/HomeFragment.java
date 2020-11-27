package com.integro.sjc.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

import com.github.demono.AutoScrollViewPager;
import com.integro.sjc.AboutSjcActivity;
import com.integro.sjc.AnnouncementsActivity;
import com.integro.sjc.OurCoursesActivity;
import com.integro.sjc.PlacementActivity;
import com.integro.sjc.R;
import com.integro.sjc.adapters.NewsViewPagerAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.News;
import com.integro.sjc.model.NewsList;
import com.potyvideo.library.AndExoPlayerView;
import com.potyvideo.library.globalInterfaces.ExoPlayerCallBack;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {

    private AutoScrollViewPager vpNews;
    private ArrayList<News> newsArrayList;
    private NewsViewPagerAdapter newsViewPagerAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        vpNews = v.findViewById(R.id.vpNews);
        newsArrayList = new ArrayList<>();
       /* String videoId = "https://stmarys.onlinefeast.in/SJC1.mp4";
        String videoId2 = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4";
        String videoId3 = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
*/        getNewsList();

        TextView tvAnnouncements=v.findViewById(R.id.tv_Announcements);
        TextView tvAboutSjc = v.findViewById(R.id.tv_AboutSjc);
        TextView tvOurCourses = v.findViewById(R.id.tv_OurCourses);
        TextView tvOurCourses1 = v.findViewById(R.id.tv_OurCourses1);
        TextView tvPlacement = v.findViewById(R.id.tvPlacement);

       /* AndExoPlayerView videoView = v.findViewById(R.id.videoview);
        videoView.setSource(videoId);
        videoView.requestFocus();
*/
        tvAboutSjc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AboutSjcActivity.class);
                startActivity(intent);
            }
        });

        tvOurCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOurCourses = new Intent(getContext(), OurCoursesActivity.class);
                startActivity(intentOurCourses);
            }
        });

        tvOurCourses1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOurCourses1 = new Intent(getContext(), OurCoursesActivity.class);
                startActivity(intentOurCourses1);
            }
        });
        tvAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAnnouncements = new Intent(getContext(), AnnouncementsActivity.class);
                startActivity(intentAnnouncements);

            }
        });

        tvPlacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PlacementActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    public void getNewsList() {
        Call<NewsList> newsCall = ApiClients.getClients().create(ApiServices.class).getNewsList();
        newsCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                Log.i("onResponse", "" + response.isSuccessful());
                if (!response.isSuccessful()) {
                    Log.i(TAG, "onResponse: fail");
                    return;
                }
                if (response.body().getNewsArrayList() == null) {
                    Log.i(TAG, "onResponse: null");
                    return;
                }
                int size = response.body().getNewsArrayList().size();
                Log.i(TAG, "onResponse from home page: size " + size);

                if (size > 0) {
                    newsArrayList.addAll(response.body().getNewsArrayList());
                    newsViewPagerAdapter = new NewsViewPagerAdapter(getContext(), newsArrayList);
                    vpNews.setAdapter(newsViewPagerAdapter);
                    vpNews.startAutoScroll();
                }
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());
            }
        });
    }
}


