package com.example.luput.tnt;

import android.graphics.Bitmap;

public class Coach extends Person {

    int COACH_ID = 1;

    public Coach(String firstName, String lastName, Bitmap photo, String gender, String phone, String dayOfBirth, String homeAddress, String emailAddress) {
        super(firstName, lastName, photo, gender, phone, dayOfBirth, homeAddress, emailAddress);
        this.COACH_ID = COACH_ID;
    }

    public int getCOACH_ID() {
        return COACH_ID;
    }

}
