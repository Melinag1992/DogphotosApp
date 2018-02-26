package com.example.melg.dogsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by melg on 2/25/18.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText password;
    private EditText username;
    private Button submit;
    private SharedPreferences sharedPrefs;
    private String sharedUsername;
    private String sharedPassword;
    private static final String SHAREDPREF = "mySharedPrefs";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.button);
        sharedPrefs = getApplicationContext().getSharedPreferences(SHAREDPREF, MODE_PRIVATE);

        if(sharedPrefs.getString("username", null) != null ){
            startActivity(new Intent(LoginActivity.this,BreedsActivity.class));
            finish();

        }
//
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().trim().equalsIgnoreCase("")) {
                    username.setError(getString(R.string.enterusername));
                } if (password.getText().toString().trim().equalsIgnoreCase("")) {
                    password.setError(getString(R.string.enterpassword));
                } else if (password.getText().toString() != null && username.getText().toString() != null) {

                    if (password.getText().toString().contains(username.getText().toString())) {
                        password.setError(getString(R.string.cannotcontainusername));
                    } else {

                        sharedUsername = username.getText().toString();
                        sharedPassword = password.getText().toString();
                        SharedPreferences.Editor editor = sharedPrefs.edit();
                        editor.putString("username", sharedUsername);
                        editor.putString("Password", sharedPassword);
                        editor.commit();

//                        Toast.makeText(LoginActivity.this, sharedUsername + " = " + "Username" + "\n " + sharedPassword, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(LoginActivity.this,BreedsActivity.class));

                    }
                }
            }


//}
////                }
////
////                Intent intent = new Intent(LoginActivity.this,ListActivity.class);
////                intent.putExtra("username",username.getText().toString());
//////                startActivity(intent);
////            }
////        });
//
//            }
//        });

        });
    }
}