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
<<<<<<< HEAD


    private List<String> trainees;
    private String FirstName;
    private String LastName;
    private String Email;
    private String ImgUri;
    private String PhoneNumber;

    public Coach(String firstName, String lastName, String email) {
        this.trainees = new ArrayList<String>();
        FirstName = firstName;
        LastName = lastName;
        Email = email;

=======


    private List<String> trainees;
    private String FirstName;
    private String LastName;
    private String Email;

    public Coach(String firstName, String lastName, String email) {
        this.trainees = new ArrayList<String>();
        FirstName = firstName;
        LastName = lastName;
        Email = email;
>>>>>>> bef046bccefc588df21098259ab5ed2029ec6ac2
    }

    public Coach() {
    }

<<<<<<< HEAD

=======
>>>>>>> bef046bccefc588df21098259ab5ed2029ec6ac2
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

    public String getImgUri() {
        return ImgUri;
    }

    public void setImgUri(String imgUri) {
        ImgUri = imgUri;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
