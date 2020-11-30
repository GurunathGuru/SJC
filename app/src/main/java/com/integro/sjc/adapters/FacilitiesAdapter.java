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
import com.integro.sjc.model.Facilities;

import java.util.ArrayList;

public class FacilitiesAdapter  extends RecyclerView.Adapter<FacilitiesAdapter.MyViewHolder> {
    Context context;
    ArrayList<Facilities>facilitiesArrayList;

    public FacilitiesAdapter(Context context, ArrayList<Facilities> facilitiesArrayList) {
        this.context = context;
        this.facilitiesArrayList = facilitiesArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_facilities,parent,false);
        return new FacilitiesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Facilities facilities =facilitiesArrayList.get(position);

        Glide.with(context)
                .load(facilities.getImage())
                .into(holder.ivFImage);

        holder.tvFTitle.setText(facilities.getTitle());
        holder.tvFDescription.setText(facilities.getDescription());
    }



    @Override
    public int getItemCount() {
        return facilitiesArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewFacilities;
        ImageView ivFImage;
        TextView tvFTitle,tvFDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewFacilities=itemView.findViewById(R.id.cr_Facilities);
            ivFImage=itemView.findViewById(R.id.iv_Fimage);
            tvFTitle=itemView.findViewById(R.id.tv_Ftitle);
            tvFDescription=itemView.findViewById(R.id.tv_Fdescription);
        }
    }

}
