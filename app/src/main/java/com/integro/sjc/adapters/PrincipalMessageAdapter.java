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
import com.integro.sjc.model.PrincipalMessage;

import java.util.ArrayList;

public class PrincipalMessageAdapter extends RecyclerView.Adapter<PrincipalMessageAdapter.MyViewHolder> {

    Context context;
    ArrayList<PrincipalMessage> principalMessageArrayList;

    public PrincipalMessageAdapter(Context context, ArrayList<PrincipalMessage> principalMessageArrayList) {
        this.context = context;
        this.principalMessageArrayList = principalMessageArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_principal_message, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (position == principalMessageArrayList.size() - 1) {
            holder.principalCard.setVisibility(View.GONE);
        }
        Glide.with(context)
                .load(principalMessageArrayList.get(position).getImage())
                .into(holder.ivImage);

        holder.tvTitle.setText(principalMessageArrayList.get(position).getName());
        holder.tvQli.setText(principalMessageArrayList.get(position).getQualification());
        holder.tv_description.setText(principalMessageArrayList.get(position).getDesignation());
    }

    @Override
    public int getItemCount() {
        return principalMessageArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tv_description;
        TextView tvQli;
        ImageView ivImage;
        CardView principalCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            principalCard = itemView.findViewById(R.id.principalCard);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tv_description = itemView.findViewById(R.id.tv_description);
            tvQli = itemView.findViewById(R.id.tvQli);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
