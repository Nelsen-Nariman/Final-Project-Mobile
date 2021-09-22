package com.example.finalproject;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Data implements Parcelable {
    private String name, email, password;



    public Data(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Data(){}


    protected Data(Parcel in) {
        name = in.readString();
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);
    }
}
