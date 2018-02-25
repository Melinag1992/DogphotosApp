package com.example.melg.dogsapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.TextView;

import com.example.melg.dogsapp.controller.DogRecAdapter;
import com.example.melg.dogsapp.model.DogListPojo;
import com.example.melg.dogsapp.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by melg on 2/25/18.
 */

public class DogsActivity extends AppCompatActivity {

    private TextView breedTextView;
    private String breedName;
    private RecyclerView recyclerView;
    private List<String> dogImages = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);

        breedTextView = findViewById(R.id.breed_textview);

        Intent intent = getIntent();
        breedName = intent.getStringExtra("breed");
        breedTextView.setText(breedName.toUpperCase());

        recyclerView = findViewById(R.id.dog_recyclerview);
        setRetrofit(breedName);


        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else {

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
            recyclerView.setLayoutManager(gridLayoutManager);

        }
   }


    public void setRetrofit(String breed) {


        Call<DogListPojo> getResultList = RetrofitInstance.getInstance()
                .getApi()
                .getListOfImages(breed);
        getResultList.enqueue(new Callback<DogListPojo>() {
            @Override
            public void onResponse(Call<DogListPojo> call, Response<DogListPojo> response) {
                dogImages.addAll(response.body().getMessage());

                DogRecAdapter dogRecAdapter = new DogRecAdapter(getApplicationContext(), dogImages);
                recyclerView.setAdapter(dogRecAdapter);

            }
            @Override
            public void onFailure(Call<DogListPojo> call, Throwable t) {

            }
        });
    }
}