package com.integro.sjc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.integro.sjc.adapters.GalleryImagesAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.GalleryImages;
import com.integro.sjc.model.GalleryImagesList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class GalleryImagesActivity extends AppCompatActivity {
    private static final String TAG = "GalleryImagesActivity";
    String itemId;
    RecyclerView rvGalleryImages;
    ArrayList<GalleryImages> galleryImagesArrayList;
    GalleryImagesAdapter imagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_images);

        itemId=getIntent().getStringExtra("itemId");
        rvGalleryImages=findViewById(R.id.rvGalleryImages);
        galleryImagesArrayList=new ArrayList<>();

        getGalleryImagesList();
    }

    private void getGalleryImagesList() {
        Call<GalleryImagesList> galleryImagesListCall= ApiClients.getClients().create(ApiServices.class).getGalleryImagesList(itemId);
        galleryImagesListCall.enqueue(new Callback<GalleryImagesList>() {
            @Override
            public void onResponse(Call<GalleryImagesList> call, Response<GalleryImagesList> response) {
                if (!response.isSuccessful()){
                    Log.i(TAG, "onResponse: response fail");
                    return;
                }
                if (response.body().getGalleryImagesArrayList()==null){
                    Log.i(TAG, "onResponse: response null");
                    return;
                }
                int size=response.body().getGalleryImagesArrayList().size();
                if (size>0){
                    galleryImagesArrayList.addAll(response.body().getGalleryImagesArrayList());
                    imagesAdapter=new GalleryImagesAdapter(getApplicationContext(),galleryImagesArrayList);
                    rvGalleryImages.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
                    rvGalleryImages.setAdapter(imagesAdapter);
                }
            }

            @Override
            public void onFailure(Call<GalleryImagesList> call, Throwable t) {

            }
        });
    }
}