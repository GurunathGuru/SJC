package com.integro.sjc.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.integro.sjc.R;
import com.integro.sjc.model.Gallery;

import java.util.ArrayList;

public class GalleryViewPagerAdapter extends PagerAdapter {

    boolean flag;
    Context context;
    ArrayList<Gallery> galleryArrayList;

    public GalleryViewPagerAdapter(Context context, ArrayList<Gallery> galleryArrayList) {
        this.context = context;
        this.galleryArrayList = galleryArrayList;
    }

    @Override
    public int getCount() {
        return galleryArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_gallery_view_pager, container, false);

        PhotoView ivImage = view.findViewById(R.id.ivImage);
        TextView ivShare = view.findViewById(R.id.ivShare);

        Glide.with(context)
                .load(galleryArrayList.get(position).getImage())
                .into(ivImage);

        ivImage.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                ivShare.setVisibility(View.VISIBLE);
            }
        });

        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,
                        "https://sjc.ac.in/sjc_app/galleryshare.php?id=" + galleryArrayList.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
