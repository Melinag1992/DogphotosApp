package com.example.melg.dogsapp.controller;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import com.example.melg.dogsapp.PhotoActivity;
import com.example.melg.dogsapp.R;
import com.squareup.picasso.Picasso;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by melg on 2/25/18.
 */

class DogViewHolder extends RecyclerView.ViewHolder {
     ImageView dogImage;
    private String dogUrl;
    public DogViewHolder(final View itemView) {
        super(itemView);

        dogImage = itemView.findViewById(R.id.dog_iv_imageview);


    }public void onBind(String dogUrl){


        Picasso.with(itemView.getContext())
                .load(dogUrl)
                .resize(500,500)
                .into(dogImage);


    }
}
