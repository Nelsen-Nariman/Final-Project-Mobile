package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    // Initializing the ImageView
    ImageView rImage;
    Button profile;

    View.OnClickListener toProfile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent toProfile = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(toProfile);
        }
    };

    View.OnClickListener toDescription = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent toDescription = new Intent(MainActivity.this, ImageGoActivity.class);
            startActivity(toDescription);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting ImageView by its id
        rImage = findViewById(R.id.rImage);
        profile = findViewById(R.id.profil_et);

        profile.setOnClickListener(toProfile);
        rImage.setOnClickListener(toDescription);

        // we will get the default FirebaseDatabase instance
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        // we will get a DatabaseReference for the database root node
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        // Here "image" is the child node value we are getting
        // child node data in the getImage variable
        DatabaseReference getImage = databaseReference.child("image");

        // Adding listener for a single change
        // in the data at this location.
        // this listener will triggered once
        // with the value of the data at the location
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // getting a DataSnapshot for the location at the specified
                // relative path and getting in the link variable
                String link = dataSnapshot.getValue(String.class);

                // loading that data into rImage
                // variable which is ImageView
                Picasso.get().load(link).into(rImage);
            }

            // this will called when any problem
            // occurs in getting data
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // we are showing that error message in toast
                Toast.makeText(MainActivity.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
