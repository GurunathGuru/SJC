package com.integro.sjc;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.integro.sjc.adapters.GalleryAlbumAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.Gallery;
import com.integro.sjc.model.GalleryList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.LinearLayout.VERTICAL;


public class GalleryAlbumActivity extends AppCompatActivity {

    private static final String TAG = "GalleryAlbumActivity";
    RecyclerView rvGalleryAlbum;
    ArrayList<Gallery> galleryArrayList;
    GalleryAlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_album);

        rvGalleryAlbum=findViewById(R.id.rvGalleryAlbum);
        galleryArrayList =new ArrayList<>();

        getGalleryAlbumList();
    }

    private void getGalleryAlbumList() {
        Call<GalleryList> albumListCall= ApiClients.getClients().create(ApiServices.class).getGalleryAlbumList();
        albumListCall.enqueue(new Callback<GalleryList>() {
            @Override
            public void onResponse(Call<GalleryList> call, Response<GalleryList> response) {
                if (!response.isSuccessful()){
                    Log.i(TAG, "onResponse: response fail");
                    return;
                }
                if (response.body().getGalleryArrayList()==null){
                    Log.i(TAG, "onResponse: response null");
                    return;
                }
                int size=response.body().getGalleryArrayList().size();

                if (size>0){
                    galleryArrayList.addAll(response.body().getGalleryArrayList());
                    rvGalleryAlbum.setLayoutManager(new StaggeredGridLayoutManager(2,VERTICAL));
                    albumAdapter=new GalleryAlbumAdapter(getApplicationContext(), galleryArrayList);
                    rvGalleryAlbum.setAdapter(albumAdapter);
                }else {
                    Toast.makeText(GalleryAlbumActivity.this, "Data not Available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GalleryList> call, Throwable t) {

                Toast.makeText(GalleryAlbumActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}