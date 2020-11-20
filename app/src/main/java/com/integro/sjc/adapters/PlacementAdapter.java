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
import com.integro.sjc.model.Placement;

import java.util.ArrayList;

public class PlacementAdapter extends RecyclerView.Adapter<PlacementAdapter.MyViewHolder> {

    Context context;
    ArrayList<Placement> placementArrayList;

    public PlacementAdapter(Context context, ArrayList<Placement> placementArrayList) {
        this.context = context;
        this.placementArrayList = placementArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_placement, parent, false);
        return new PlacementAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Placement placement=placementArrayList.get(position);
        holder.tvTitle.setText(placement.getTitle());
        holder.tvDescription.setText(placement.getDescription());

        Glide.with(context)
                .load(placement.getImage())
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return placementArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvTitle;
        TextView tvDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle =itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivImage=itemView.findViewById(R.id.ivImage);

        }
    }
}
