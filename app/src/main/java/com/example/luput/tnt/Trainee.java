package com.example.luput.tnt;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Trainee extends Person {

    private int TRAINEE_ID = 2;
    private List<String> Programs;

    public Trainee(String firstName, String lastName,String emailAddress) {
        super(firstName, lastName, emailAddress);
        Programs = new ArrayList<String>();
    }

    public Trainee() {
    }


    public List<String> getPrograms() {
        return Programs;
    }

    public int getTRAINEE_ID() {
        return TRAINEE_ID;
    }


}
