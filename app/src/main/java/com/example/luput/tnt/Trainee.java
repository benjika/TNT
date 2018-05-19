package com.example.luput.tnt;

import android.graphics.Bitmap;

public class Trainee extends Person {

    int TRAINEE_ID = 2;

    public Trainee(String firstName, String lastName, Bitmap photo, String gender, String phone, String dayOfBirth, String homeAddress, String emailAddress) {
        super(firstName, lastName, photo, gender, phone, dayOfBirth, homeAddress, emailAddress);
    }

    public int getTRAINEE_ID() {
        return TRAINEE_ID;
    }


}
