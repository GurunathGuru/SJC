package com.integro.sjc.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.integro.sjc.R;
import com.integro.sjc.adapters.NotificationAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.Notification;
import com.integro.sjc.model.NotificationList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class NotificationFragment extends Fragment {

    RecyclerView rvNotification;
    NotificationAdapter adapter;
    ArrayList<Notification> notificationArrayList;
    private boolean flag = false;

    public NotificationFragment() {
        FirebaseUser vipUser = FirebaseAuth.getInstance().getCurrentUser();
        if (vipUser != null) {
            flag = true;
        } else {
            flag = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        rvNotification = view.findViewById(R.id.rv_notification);
        notificationArrayList = new ArrayList<>();
        rvNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        getNotificationList();
        return view;
    }

    public void getNotificationList() {
        Call<NotificationList> notificationListCall = ApiClients.getClients().create(ApiServices.class).getNotificationList();
        notificationListCall.enqueue(new Callback<NotificationList>() {
            @Override
            public void onResponse(Call<NotificationList> call, Response<NotificationList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getNotificationArrayList()!=null) {
                        int size = response.body().getNotificationArrayList().size();
                        for (int i = 0; i < size; i++) {
                            if (flag == true) {
                                notificationArrayList.add(response.body().getNotificationArrayList().get(i));
                            } else {
                                if (response.body().getNotificationArrayList().get(i).getNtype().contentEquals("Student")) {
                                    notificationArrayList.add(response.body().getNotificationArrayList().get(i));
                                }
                            }
                        }
                        adapter = new NotificationAdapter(getContext(), notificationArrayList);
                        rvNotification.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    //failure api response
                    Toast.makeText(getContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotificationList> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure:" + t.getMessage());
            }
        });
    }
}
