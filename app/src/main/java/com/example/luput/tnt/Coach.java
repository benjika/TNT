package com.example.luput.tnt;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Coach implements Serializable {

    private List<String> trainees;


    private List<TrainingProgram> TrainingProgramBank;
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
        if (this.trainees == null) this.trainees = new ArrayList<>();
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

    public List<TrainingProgram> getTrainingProgramBank() {
        if (this.TrainingProgramBank == null) this.TrainingProgramBank = new ArrayList<>();
        return TrainingProgramBank;
    }

    public void setTrainingProgramBank(List<TrainingProgram> trainingProgramBank) {
        TrainingProgramBank = trainingProgramBank;
    }

    public int getSizeOfBank() {
        if (this.TrainingProgramBank == null) this.TrainingProgramBank = new ArrayList<>();
        return this.TrainingProgramBank.size();
    }

    public void addToBank(TrainingProgram trainingProgram) {
        if (this.TrainingProgramBank == null) this.TrainingProgramBank = new ArrayList<>();
        this.TrainingProgramBank.add(trainingProgram);
    }
}
