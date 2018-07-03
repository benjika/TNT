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
    transient private Bitmap photo;
    private String gender;
    private String phone;
    private String dayOfBirth;
    private String homeAddress;
    private String emailAddress;

    public Person(String firstName, String lastName, Bitmap photo, String gender, String phone, String dayOfBirth, String homeAddress, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.gender = gender;
        this.phone = phone;
        this.dayOfBirth = dayOfBirth;
        this.homeAddress = homeAddress;
        this.emailAddress = emailAddress;
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

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

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
}
