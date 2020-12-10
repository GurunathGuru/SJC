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

import com.integro.sjc.DepartmentActivity2;
import com.integro.sjc.R;
import com.integro.sjc.model.Department;
import com.integro.sjc.model.Department2;

import java.util.ArrayList;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.MyViewHolder> {
    Context context;
    ArrayList<Department> departmentArrayList;

    public DepartmentAdapter(Context context, ArrayList<Department> departmentArrayList) {
        this.context = context;
        this.departmentArrayList = departmentArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_departent, parent, false);
        return new DepartmentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Department department = departmentArrayList.get(position);
        holder.tvDeptTitle.setText(department.getTitle());

        holder.cvDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DepartmentActivity2.class);
                intent.putExtra("position", position);
                intent.putExtra("itemId",department.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return departmentArrayList.size();
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
