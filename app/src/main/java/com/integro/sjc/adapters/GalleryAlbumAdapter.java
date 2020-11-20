package com.integro.sjc.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.sjc.GalleryImagesActivity;
import com.integro.sjc.R;
import com.integro.sjc.model.GalleryAlbum;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GalleryAlbumAdapter extends RecyclerView.Adapter<GalleryAlbumAdapter.MyViewHolder> {

    Context context;
    ArrayList<GalleryAlbum> galleryAlbumArrayList;

    public GalleryAlbumAdapter(Context context, ArrayList<GalleryAlbum> galleryAlbumArrayList) {
        this.context = context;
        this.galleryAlbumArrayList = galleryAlbumArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_gallery,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GalleryAlbum album=galleryAlbumArrayList.get(position);

        holder.tvTitle.setText(album.getTitle());
        Glide.with(context).load(album.getImage()).into(holder.ivImage);

        holder.cvAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, GalleryImagesActivity.class);
                intent.putExtra("itemId",album.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryAlbumArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cvAlbum;
        ImageView ivImage;
        TextView tvTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cvAlbum=itemView.findViewById(R.id.cvAlbum);
            ivImage=itemView.findViewById(R.id.ivImage);
            tvTitle=itemView.findViewById(R.id.tvTitle);
        }
    }
}
