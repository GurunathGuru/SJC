package com.integro.sjc.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.integro.sjc.R;
import com.integro.sjc.WebActivity;
import com.integro.sjc.model.Banner;
import com.integro.sjc.model.CoverPhotos;

import java.util.ArrayList;

public class BannerViewPagerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Banner> bannerArrayList;
    private static final String TAG = "BannerViewPagerAdapter";

    public BannerViewPagerAdapter(Context context, ArrayList<Banner> bannerArrayList) {
        this.context = context;
        this.bannerArrayList = bannerArrayList;
    }


    @Override
    public int getCount() {
        return bannerArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_cover_vp_photos, container, false);

        ImageView ivImage = view.findViewById(R.id.ivImage);
        TextView tvTitle = view.findViewById(R.id.tvTitle);

        Log.d(TAG, "instantiateItem: title " + bannerArrayList.get(position).getTitle());
        Log.d(TAG, "instantiateItem: image " + bannerArrayList.get(position).getImage());

        if (bannerArrayList.get(position).getImage().isEmpty()) {
            ivImage.setVisibility(View.GONE);
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(bannerArrayList.get(position).getTitle());
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("TAG", bannerArrayList.get(position).getWeblink());
                    context.startActivity(intent);
                }
            });
        } else {

            tvTitle.setVisibility(View.GONE);
            ivImage.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(bannerArrayList.get(position).getImage())
                    .into(ivImage);
            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("TAG", bannerArrayList.get(position).getWeblink());
                    context.startActivity(intent);
                }
            });

        }
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
