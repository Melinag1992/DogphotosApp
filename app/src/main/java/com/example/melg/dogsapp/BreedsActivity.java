package com.example.melg.dogsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.melg.dogsapp.model.DogPojo;
import com.example.melg.dogsapp.retrofit.RetrofitInstance;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by melg on 2/25/18.
 */


public class BreedsActivity extends AppCompatActivity {
    private ImageView terrierImage;
    private ImageView poodleImage;
    private ImageView retrieverImage;
    private ImageView spanielImage;
    private TextView welcomeMessage;
    private String username;
    private CardView terrierCardView;
    private CardView spanielCardView;
    private CardView retrieverCardView;
    private CardView poodleCardView;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);
         sharedPreferences = this.getSharedPreferences("mySharedPrefs", MODE_PRIVATE);

        setviews();
        setCardViewImage(terrierCardView, getString(R.string.terrier), terrierImage);
        setCardViewImage(spanielCardView, getString(R.string.spaniel), spanielImage);
        setCardViewImage(retrieverCardView, getString(R.string.retriever), retrieverImage);
        setCardViewImage(poodleCardView, getString(R.string.poodle), poodleImage);
        setCardonClick();


        username = sharedPreferences.getString("username","");


        welcomeMessage.setText(getString(R.string.welcome) + " " + username + "?");


    }

    public void setviews() {
        terrierImage = findViewById(R.id.terrier_imagev);
        poodleImage = findViewById(R.id.poodle_imagev);
        retrieverImage = findViewById(R.id.retriever_imagev);
        spanielImage = findViewById(R.id.spaniel_imagev);

        poodleCardView = findViewById(R.id.poodle_card);
        terrierCardView = findViewById(R.id.terrier_card);
        spanielCardView = findViewById(R.id.spaniel_card);
        retrieverCardView = findViewById(R.id.retriever_card);

        welcomeMessage = findViewById(R.id.welcome_textview);

    }


    public void setCardViewImage(View v, String breed, final ImageView imageView) {
        Call<DogPojo> getResults = RetrofitInstance.getInstance()
                .getApi()
                .getDogResults(breed);
        getResults.enqueue(new Callback<DogPojo>() {
            public static final String TAG = "terrier ";

            @Override
            public void onResponse(Call<DogPojo> call, Response<DogPojo> response) {


                Picasso.with(getApplicationContext())
                        .load(response.body().getMessage())
                        .resize(500, 500)
                        .into(imageView);

                Log.d(TAG, "onResponse: " + response.body());

            }

            @Override
            public void onFailure(Call<DogPojo> call, Throwable t) {
                t.getMessage();

            }
        });

    }

    public void setCardonClick() {
        poodleCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntents("poodle");
            }
        });
        spanielCardView = findViewById(R.id.spaniel_card);
        spanielCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntents("spaniel");
            }
        });
        retrieverCardView = findViewById(R.id.retriever_card);
        retrieverCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntents("retriever");
            }
        });
        terrierCardView = findViewById(R.id.terrier_card);
        terrierCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntents("terrier");
            }
        });
    }

    public void startIntents(String breed) {
        Intent intent = new Intent(this, DogsActivity.class);
        intent.putExtra("breed", breed);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }


}
