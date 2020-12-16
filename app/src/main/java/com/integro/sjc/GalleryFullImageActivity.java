package com.integro.sjc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.integro.sjc.adapters.GalleryViewPagerAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.Gallery;
import com.integro.sjc.model.GalleryList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFullImageActivity extends AppCompatActivity {

    private static final String TAG = "GalleryFullImageActivity";
    private ApiServices apiServices;
    private ArrayList<Gallery> galleryArrayList;
    private GalleryViewPagerAdapter galleryViewPagerAdapter;
    private ViewPager vpFullImage;
    private int itemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_full_image);
        itemPosition = (int) getIntent().getSerializableExtra("position");

        apiServices = ApiClients.getClients().create(ApiServices.class);
        galleryArrayList = new ArrayList<>();
        vpFullImage = findViewById(R.id.vpFullGallery);
        getPmoPhotosList();

    }

    public void getPmoPhotosList() {
        Call<GalleryList> photosImagesListCall = apiServices.getGalleryAlbumList();
        photosImagesListCall.enqueue(new Callback<GalleryList>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<GalleryList> call, Response<GalleryList> response) {
                if (response.isSuccessful()) {
                    int size = response.body().getGalleryArrayList().size();
                    Log.i(TAG, "onResponse: size "+size);
                    Log.i(TAG, "onResponse: position "+ itemPosition);
                    for (int i = 0; i < size; i++) {
                        galleryArrayList.add(response.body().getGalleryArrayList().get(i));
                        galleryViewPagerAdapter = new GalleryViewPagerAdapter(getApplicationContext(), galleryArrayList);
                        vpFullImage.setAdapter(galleryViewPagerAdapter);
                        vpFullImage.setCurrentItem(itemPosition);
                    }
                }
            }

            @Override
            public void onFailure(Call<GalleryList> call, Throwable t) {

            }
        });
    }
}