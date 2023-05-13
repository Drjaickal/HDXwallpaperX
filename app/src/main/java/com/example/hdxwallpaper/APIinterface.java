package com.example.hdxwallpaper;

import static com.example.hdxwallpaper.APIutilities.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIinterface {

    String BASE_URL="https://api.pexels.com/v1/";

    @Headers("Authorization: "+API)
    @GET("curated")
    Call<SearchModel> getimage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );


    @Headers("Authorization: "+API)
    @GET("search")
    Call<SearchModel> getSearchimage(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );


}
