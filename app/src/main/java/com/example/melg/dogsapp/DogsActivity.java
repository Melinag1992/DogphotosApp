package com.example.melg.dogsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);

        sharedPrefs = getApplicationContext().getSharedPreferences("mySharedPrefs", MODE_PRIVATE);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();


        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}
