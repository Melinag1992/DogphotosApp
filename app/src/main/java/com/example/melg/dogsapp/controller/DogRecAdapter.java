package com.example.melg.dogsapp.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.melg.dogsapp.PhotoActivity;
import com.example.melg.dogsapp.R;
import com.example.melg.dogsapp.model.DogListPojo;
import com.example.melg.dogsapp.model.DogPojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melg on 2/25/18.
 */


public class DogRecAdapter extends RecyclerView.Adapter<DogViewHolder> {

    Context context;
    List<String> dogsImages = new ArrayList<>();
    View view;

    public DogRecAdapter(Context context, List<String>dogList){
        this.dogsImages = dogList;
        this.context = context;

    }
    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dogs_itemview, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, int position) {
        final String dogListPojo = dogsImages.get(position);
        holder.onBind(dogListPojo);
        holder.dogImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotoActivity.class);
                intent.putExtra("URLString", dogListPojo);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return dogsImages.size();
    }
}
