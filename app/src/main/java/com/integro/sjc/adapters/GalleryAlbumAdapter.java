package com.integro.sjc.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.sjc.GalleryFullImageActivity;
import com.integro.sjc.R;
import com.integro.sjc.model.Gallery;

import java.util.ArrayList;

public class GalleryAlbumAdapter extends RecyclerView.Adapter<GalleryAlbumAdapter.MyViewHolder> {

    Context context;
    ArrayList<Gallery> galleryArrayList;

    public GalleryAlbumAdapter(Context context, ArrayList<Gallery> galleryArrayList) {
        this.context = context;
        this.galleryArrayList = galleryArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_gallery, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Gallery album = galleryArrayList.get(position);

        Glide.with(context).load(album.getImage()).into(holder.ivImage);

        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GalleryFullImageActivity.class);
                intent.putExtra("itemId", album.getId());
                intent.putExtra("position", position);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
