package com.integro.sjc.adapters;

import android.content.Context;
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
import com.integro.sjc.model.DistinguishAlumni;

import java.util.ArrayList;

public class AlumniAdapter extends PagerAdapter {
    Context context;
    ArrayList<DistinguishAlumni> distinguishAlumniArrayList;

    public AlumniAdapter(Context context, ArrayList<DistinguishAlumni> distinguishAlumniArrayList) {
        this.context = context;
        this.distinguishAlumniArrayList = distinguishAlumniArrayList;
    }

    @Override
    public int getCount() {
        return distinguishAlumniArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_alumni, container, false);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tv_description = view.findViewById(R.id.tv_description);
        ImageView ivImage = view.findViewById(R.id.ivImage);

        Glide.with(context)
                .load(distinguishAlumniArrayList.get(position).getImage())
                .into(ivImage);

        tvTitle.setText(distinguishAlumniArrayList.get(position).getName());
        tv_description.setText(distinguishAlumniArrayList.get(position).getDescription());

        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
