package com.example.melg.dogsapp.Dogservice;

import com.example.melg.dogsapp.model.DogListPojo;
import com.example.melg.dogsapp.model.DogPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by melg on 2/25/18.
 */

public interface DogService {
    @GET("/api/breed/{breed-name}/images/random")
    Call<DogPojo> getDogResults(@Path("breed-name")String breed);


    @GET("/api/breed/{breed-name}/images")
    Call<DogListPojo> getListOfImages(@Path("breed-name")String breed);

}
