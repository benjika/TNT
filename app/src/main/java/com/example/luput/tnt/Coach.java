package com.example.luput.tnt;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class Coach {


    private List<String> trainees;
    private String FirstName;
    private String LastName;
    private String Email;

    public Coach(String firstName, String lastName, String email) {
        this.trainees = new ArrayList<String>();
        FirstName = firstName;
        LastName = lastName;
        Email = email;
    }

    public Coach() {
    }

    public void setTrainees(List<String> trainees) {
        this.trainees = trainees;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public List<String> getTrainees() {
        return trainees;
    }
}
