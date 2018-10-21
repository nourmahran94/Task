package com.nour.myapplication.network;

import com.nour.myapplication.models.BigModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("all")
    Call<BigModel>getCategories();
}
