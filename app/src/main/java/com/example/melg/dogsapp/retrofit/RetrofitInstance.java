package com.example.melg.dogsapp.retrofit;

import com.example.melg.dogsapp.Dogservice.DogService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by melg on 2/25/18.
 */

public class RetrofitInstance {

    private String HOST = "https://dog.ceo/";

    public static RetrofitInstance instance;


    public RetrofitInstance() {

    }


    public static RetrofitInstance getInstance() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }



    Retrofit getRetrofit() { // getting the retofit builder to use in other methods
        return new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    public DogService getApi() {
        return getRetrofit().create(DogService.class);
    }


}

