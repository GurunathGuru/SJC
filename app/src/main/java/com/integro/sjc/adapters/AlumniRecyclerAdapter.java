package com.integro.sjc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.sjc.R;
import com.integro.sjc.model.DistinguishAlumni;

import java.util.ArrayList;

public class AlumniRecyclerAdapter extends RecyclerView.Adapter<AlumniRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<DistinguishAlumni> distinguishAlumniArrayList;

    public AlumniRecyclerAdapter(Context context, ArrayList<DistinguishAlumni> distinguishAlumniArrayList) {
        this.context = context;
        this.distinguishAlumniArrayList = distinguishAlumniArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_alumni_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context)
                .load(distinguishAlumniArrayList.get(position).getImage())
                .into(holder.ivImage);

        holder.tvTitle.setText(distinguishAlumniArrayList.get(position).getName());
        holder.tv_description.setText(distinguishAlumniArrayList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return distinguishAlumniArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tv_description;
        ImageView ivImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tv_description = itemView.findViewById(R.id.tv_description);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
