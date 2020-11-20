package com.integro.sjc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.sjc.R;
import com.integro.sjc.model.AboutSjc;
import com.integro.sjc.model.News;

import java.util.ArrayList;

public class AboutSjcAdapter extends RecyclerView.Adapter<AboutSjcAdapter.MyViewHolder> {
    Context context;
    ArrayList<AboutSjc>aboutSjcArrayList;

    public AboutSjcAdapter(Context context, ArrayList<AboutSjc> aboutSjcArrayList) {
        this.context = context;
        this.aboutSjcArrayList = aboutSjcArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_about_sjc,parent,false);
        return new AboutSjcAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      AboutSjc aboutSjc=aboutSjcArrayList.get(position);

      Glide.with(context)
                .load(aboutSjc.getImage())
                .into(holder.ivImage);

        holder.tvTitle.setText(aboutSjc.getTitle());
        holder.tvDescription.setText(aboutSjc.getDescription());
    }

    @Override
    public int getItemCount() {
        return aboutSjcArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewAboutSjc;
        ImageView ivImage;
        TextView tvTitle,tvDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewAboutSjc=itemView.findViewById(R.id.cr_aboutSjc);
            ivImage=itemView.findViewById(R.id.iv_aboutSjc);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvDescription=itemView.findViewById(R.id.tv_description);
        }
    }

}
