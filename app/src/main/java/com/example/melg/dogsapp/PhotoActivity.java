package com.example.melg.dogsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by melg on 2/25/18.
 */

public class PhotoActivity extends AppCompatActivity {

    private ImageView dogImage;
    private String dogImageURL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        dogImage = findViewById(R.id.dog_photo);

        Intent intent = getIntent();
        dogImageURL = intent.getStringExtra("URLString");


        Picasso.with(getApplicationContext())
                .load(dogImageURL)
                .resize(500, 500)
                .into(dogImage);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}



