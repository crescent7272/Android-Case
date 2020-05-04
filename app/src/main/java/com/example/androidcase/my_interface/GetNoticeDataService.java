package com.example.androidcase.my_interface;

import com.example.androidcase.model.AnttPricesRequestModel;
import com.example.androidcase.model.AnttPricesModel;
import com.example.androidcase.model.RequestModel;
import com.example.androidcase.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetNoticeDataService {



    /**
     * URL MANIPULATION
     * HTTP request body with the @Body annotation
     */
    @POST("v1/route")
    Call<ResponseModel> getRoute(@Body RequestModel notice);

    @POST("v1/antt_price/all")
    Call<AnttPricesModel> getAnttPrices(@Body AnttPricesRequestModel notice);

}