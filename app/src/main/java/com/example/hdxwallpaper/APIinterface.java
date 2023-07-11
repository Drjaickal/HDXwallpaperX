package com.example.hdxwallpaper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIinterface {
    String BASE_URL = "https://api.pexels.com/v1/";
    String HEADER_AUTHORIZATION = "Authorization";

    @Headers({HEADER_AUTHORIZATION + ": {apiKey}"})
    @GET("curated")
    Call<SearchModel> getImages(
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("apiKey") String apiKey
    );

    @Headers({HEADER_AUTHORIZATION + ": {apiKey}"})
    @GET("search")
    Call<SearchModel> getSearchImages(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("apiKey") String apiKey
    );

    Call<SearchModel> getSearchimage(String query, int i, int i1);
}
