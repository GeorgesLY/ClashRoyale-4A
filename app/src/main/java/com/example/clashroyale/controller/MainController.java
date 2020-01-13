package com.example.clashroyale.controller;

import android.util.Log;

import com.example.clashroyale.model.ClashRestApi;
import com.example.clashroyale.model.Items;
import com.example.clashroyale.model.RestClashResponse;
import com.example.clashroyale.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {
    public MainActivity activity;
    public MainController(MainActivity mainActivity) {
        this.activity = mainActivity;
    }
    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/GeorgesLY/Programmation-Mobile/master/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ClashRestApi clashRestApi = retrofit.create(ClashRestApi.class);
        Call<RestClashResponse> call = clashRestApi.getListClashAPI();
        call.enqueue(new Callback<RestClashResponse>() {
            public void onResponse(Call<RestClashResponse> call, Response<RestClashResponse> response) {
                RestClashResponse restClashResponse = response.body();
                List<Items> listClash = restClashResponse.getItems();
                activity.showList(listClash);
            }
            public void onFailure(Call<RestClashResponse> call, Throwable t) {
                Log.d("Erreur", "API KO");
            }
        });
    }
}
