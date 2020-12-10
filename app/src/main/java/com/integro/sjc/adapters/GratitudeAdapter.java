package com.integro.sjc.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.integro.sjc.R;
import com.integro.sjc.model.Gratitude;

import java.util.ArrayList;

public class GratitudeAdapter extends PagerAdapter {

    private static final String TAG = "GratitudeAdapter";
    Context context;
    ArrayList<Gratitude> gratitudeArrayList;
    String flog;

    public GratitudeAdapter(Context context, ArrayList<Gratitude> gratitudeArrayList, String flog) {
        this.context = context;
        this.gratitudeArrayList = gratitudeArrayList;
        this.flog = flog;
    }

    @Override
    public int getCount() {
        return gratitudeArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;
        ImageView ivImage = null;

        if (flog.equals("fromFragment")) {
            Log.i(TAG, "instantiateItem: fromFragment "+flog);
            view = LayoutInflater.from(context).inflate(R.layout.card_cover_photos, container, false);
            ivImage = view.findViewById(R.id.ivImage);
            Glide.with(context)
                    .load(gratitudeArrayList.get(position).getImage())
                    .into(ivImage);
        } else {
            Log.i(TAG, "instantiateItem: fromActivity "+flog);
            view = LayoutInflater.from(context).inflate(R.layout.card_cover_vp_photos, container, false);
            ivImage = view.findViewById(R.id.ivImage);
            Glide.with(context)
                    .load(gratitudeArrayList.get(position).getImage())
                    .into(ivImage);
        }
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
