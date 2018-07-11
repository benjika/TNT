package com.example.luput.tnt;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Coach extends Person {

    int COACH_ID = 1;
    private List<String> trainees;

    public Coach(String firstName, String lastName, String emailAddress) {
        super(firstName, lastName, emailAddress);
        this.COACH_ID = COACH_ID;
        trainees = new ArrayList<String>();
    }

    public Coach() {
    }

    public int getCOACH_ID() {
        return COACH_ID;
    }

    public List<String> getTrainees() {
        return trainees;
    }
}
