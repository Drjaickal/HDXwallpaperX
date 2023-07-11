package com.example.hdxwallpaper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIutilities {

    private static Retrofit retrofit=null;
    public static final String API="Xr3QxbxGApQCj84QL5cw7tfmnAETr8vcx3surQbPTbI8H8f3pRjnGdkE";



    public static APIinterface getAPIinterface()
    {
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(APIinterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            return retrofit.create(APIinterface.class);
        }
        return null;
    }
}
