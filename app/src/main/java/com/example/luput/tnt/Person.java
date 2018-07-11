package com.example.luput.tnt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable {

    private String firstName;
    private String lastName;
    private String emailAddress;

    public Person(String firstName, String lastName,String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
/*
    private void writeObject(ObjectOutputStream oos) throws IOException {
        if (photo != null) {
            photo.compress(Bitmap.CompressFormat.JPEG, 70, oos);
        }
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        photo = BitmapFactory.decodeStream(ois);
        ois.defaultReadObject();
    }

    */
}
