package com.example.clashroyale.model;

import com.example.clashroyale.model.RestClashResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClashRestApi {
    @GET("clashApi.json")
    Call<RestClashResponse> getListClashAPI();
}
