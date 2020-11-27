package com.integro.sjc.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.sjc.R;
import com.integro.sjc.model.Announcements;

import java.util.ArrayList;

public class AnnouncementsAdapter extends RecyclerView.Adapter<AnnouncementsAdapter.MyViewHolder> {
Context context;
ArrayList<Announcements>announcementsArrayList;

    public AnnouncementsAdapter(Context context, ArrayList<Announcements> announcementsArrayList) {
        this.context = context;
        this.announcementsArrayList = announcementsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_annoncements,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Announcements newsLetter=announcementsArrayList.get(position);
        holder.tvAnntitle.setText(newsLetter.getTitle());

        holder.tvAnnpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setData(Uri.parse(newsLetter.getPdf()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return announcementsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvAnntitle,tvAnnpdf;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAnntitle=itemView.findViewById(R.id.tvAnntitle);
            tvAnnpdf=itemView.findViewById(R.id.tvAnnpdf);
        }

    }
}
