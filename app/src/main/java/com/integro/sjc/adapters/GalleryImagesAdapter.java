package com.integro.sjc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.sjc.R;
import com.integro.sjc.model.GalleryImages;

import java.util.ArrayList;

public class GalleryImagesAdapter extends RecyclerView.Adapter<GalleryImagesAdapter.MyViewHolder> {

    Context context;
    ArrayList<GalleryImages>galleryImagesArrayList;

    public GalleryImagesAdapter(Context context, ArrayList<GalleryImages> galleryImagesArrayList) {
        this.context = context;
        this.galleryImagesArrayList = galleryImagesArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_gallery_image,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GalleryImages images=galleryImagesArrayList.get(position);

        Glide.with(context)
                .load(images.getImage())
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return galleryImagesArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage=itemView.findViewById(R.id.ivImage);
        }
    }
}
