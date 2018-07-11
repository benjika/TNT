package com.example.luput.tnt;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Trainee {


    private List<String> Programs;
    private String FirstName;
    private String LastName;
    private String Email;

    public Trainee(String firstName, String lastName,String emailAddress) {
        FirstName = firstName;
        LastName = lastName;
        Email = emailAddress;
        Programs = new ArrayList<String>();
    }

    public Trainee() {
    }


    public void setPrograms(List<String> programs) {
        Programs = programs;
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

    public List<String> getPrograms() {
        return Programs;
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
}
