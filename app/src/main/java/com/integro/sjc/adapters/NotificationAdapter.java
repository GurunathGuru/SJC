package com.integro.sjc.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.sjc.R;
import com.integro.sjc.model.Notification;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private static final String TAG = "NotificationAdapter";
    Context context;
    ArrayList<Notification> notificationArrayList;

    public NotificationAdapter(Context context, ArrayList<Notification> notificationArrayList) {
        this.context = context;
        this.notificationArrayList = notificationArrayList;
    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_notification, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.MyViewHolder holder, int position) {
        final Notification notification = notificationArrayList.get(position);

        int itemId = Integer.parseInt(notificationArrayList.get(position).getId());
        Log.i(TAG, "onBindViewHolder: itemId " + itemId);
        if (itemId / 2 == 1) {
            holder.cardViewNotification.setCardBackgroundColor(context.getResources().getColor(R.color.colorYellow));
            holder.tvTitle.setTextColor(context.getResources().getColor(R.color.white));
        }
        holder.tvDate.setText(notification.getDate());
        holder.tvTitle.setText(notification.getTitle());
        holder.tvDescription.setText(notification.getDescription());
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewNotification;
        TextView tvDate, tvTitle, tvDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewNotification = itemView.findViewById(R.id.cr_notification);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}