package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView Datname, Datemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Datname = findViewById(R.id.name_et);
        Datemail = findViewById(R.id.email_et);

        SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        String name = sp.getString("name","");
        String email = sp.getString("email", "");

        Datname.setText(name);
        Datemail.setText(email);
    }
}