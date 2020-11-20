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
import com.integro.sjc.model.OurCourses;

import java.util.ArrayList;

public class OurCoursesAdapter extends RecyclerView.Adapter<OurCoursesAdapter.MyViewHolder>{
    Context context;
    ArrayList<OurCourses> ourCoursesArrayList;

    public OurCoursesAdapter(Context context, ArrayList<OurCourses> ourCoursesArrayList) {
        this.context = context;
        this.ourCoursesArrayList = ourCoursesArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_our_courses,parent,false);
        return new OurCoursesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OurCourses ourCourses=ourCoursesArrayList.get(position);
        Glide.with(context)
                .load(ourCourses.getImage())
                .into(holder.ivImage);
        holder.tvTitle.setText(ourCourses.getTitle());

    }

    @Override
    public int getItemCount() {
        return ourCoursesArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewAboutSjc;
        ImageView ivImage;
        TextView tvTitle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewAboutSjc=itemView.findViewById(R.id.cr_aboutSjc);
            ivImage=itemView.findViewById(R.id.iv_ivOurCoursesImage);
            tvTitle=itemView.findViewById(R.id.tv_OurCoursesTitle);
        }
    }
}
