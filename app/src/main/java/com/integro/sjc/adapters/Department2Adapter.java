package com.integro.sjc.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.sjc.R;
import com.integro.sjc.WebActivity;
import com.integro.sjc.model.Department2;

import java.util.ArrayList;

public class Department2Adapter extends RecyclerView.Adapter<Department2Adapter.MyViewHolder> {
    Context context;
    ArrayList<Department2> department2ArrayList;


    public Department2Adapter(Context context, ArrayList<Department2> departmentArrayList) {
        this.context = context;
        this.department2ArrayList = departmentArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_departent, parent, false);
        return new Department2Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Department2 department2 = department2ArrayList.get(position);
        holder.tvDeptTitle.setText(department2.getTitle());

        holder.cvDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = department2ArrayList.get(position).getWeblink();
                Intent intent4 = new Intent(context, WebActivity.class);
                intent4.putExtra("TAG", url);
                intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent4);
            }
        });

    }

    @Override
    public int getItemCount() {
        return department2ArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDeptTitle;
        CardView cvDepart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDeptTitle = itemView.findViewById(R.id.tv_deptTitle);
            cvDepart = itemView.findViewById(R.id.cvDepart);
        }
    }
}
