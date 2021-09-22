package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ImageGoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_go);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_container2, new DescriptionFragment())
                .commit();
    }
}