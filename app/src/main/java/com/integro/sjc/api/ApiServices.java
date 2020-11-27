package com.integro.sjc.api;

import com.integro.sjc.model.AboutSjcList;
import com.integro.sjc.model.AnnouncementsList;
import com.integro.sjc.model.Department2List;
import com.integro.sjc.model.DepartmentList;
import com.integro.sjc.model.FacilitiesList;
import com.integro.sjc.model.GalleryAlbumList;
import com.integro.sjc.model.GalleryImagesList;
import com.integro.sjc.model.NewsImagesList;
import com.integro.sjc.model.NewsList;
import com.integro.sjc.model.NotificationList;
import com.integro.sjc.model.OurCoursesList;
import com.integro.sjc.model.PlacementList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @GET("sjc_news.php")
    Call<NewsList> getNewsList();

    @FormUrlEncoded
    @POST("sjc_newsimages.php")
    Call<NewsImagesList> getNewsImagesList(@Field("news_id")String news_id);

    @GET("sjc_notification.php")
    Call<NotificationList> getNotificationList();

    @GET("sjc_about.php")
    Call<AboutSjcList> getAboutSjcList();

    @GET("sjc_courses.php")
    Call<OurCoursesList> getOurCoursesList();

    @GET("sjc_placement.php")
    Call<PlacementList> getPlacementList();

    @GET("sjc_dept.php")
    Call<DepartmentList> getDepartmentList();

    @FormUrlEncoded
    @POST("sjc_dept1.php")
    Call<Department2List> getDepartment2List(@Field("d_id")String d_id);

    @GET("sjc_gallery.php")
    Call<GalleryAlbumList>getGalleryAlbumList();

    @FormUrlEncoded
    @POST("sjc_galleryimages.php")
    Call<GalleryImagesList> getGalleryImagesList(@Field("g_id")String g_id);

    @FormUrlEncoded
    @POST("sjc_announcement.php")
    Call<AnnouncementsList> getAnnouncementsList(@Field("updated_at")String updated_at);


    @FormUrlEncoded
    @POST("sjc_facilities.php")
    Call<FacilitiesList> getFacilitiesList(@Field("updated_at")String updated_at);









}
