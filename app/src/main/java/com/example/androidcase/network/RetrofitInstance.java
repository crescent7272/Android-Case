package com.example.androidcase.network;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    //private static final String BASE_URL = "https://api.myjson.com/";
    private static final String BASE_URL = "https://geo.api.truckpad.io/";
    private static final String BASE_URL_2 = "https://tictac.api.truckpad.io/";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getBaseUrl2() {
        return BASE_URL_2;
    }

    /**
     * Create an instance of Retrofit object
     */

    public static Retrofit getRetrofitInstance(String url) {
        //if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


       // }

        return retrofit;

    }


}