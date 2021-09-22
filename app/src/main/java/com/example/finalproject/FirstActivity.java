package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    Button sigin;

    View.OnClickListener toSignIn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent toSignIn = new Intent(FirstActivity.this, AuthActivity.class);
            startActivity(toSignIn);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        sigin = findViewById(R.id.signin_et);


        sigin.setOnClickListener(toSignIn);

    }
}