package com.example.luput.tnt;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Trainee implements Comparable,Serializable{


    private List<TrainingProgram> Programs;
    private String FirstName;
    private String LastName;
    private String Email;

    public Trainee() {
    }

    public Trainee(String firstName, String lastName, String emailAddress) {
        FirstName = firstName;
        LastName = lastName;
        Email = emailAddress;
        Programs = new ArrayList<TrainingProgram>();
    }

    public List<TrainingProgram> getPrograms() {
        if (Programs == null) Programs = new ArrayList<>();
        return Programs;
    }

    public void setPrograms(List<TrainingProgram> programs){
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

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getEmailAddress(){
        return Email;
    }

    public static Comparator<Trainee> StuNameComparator = new Comparator<Trainee>() {
        public int compare(Trainee s1, Trainee s2) {
           // String StudentName1 = s1.getStudentname().toUpperCase();
           // String StudentName2 = s2.getStudentname().toUpperCase();.

            if (s1.getLastName().compareTo(((Trainee) s2).getLastName()) != 0)
                s1.getFirstName().compareTo(((Trainee) s2).getLastName());
            else if (s1.getFirstName().compareTo(((Trainee) s2).getFirstName()) != 0) {
                return s1.getFirstName().compareTo(((Trainee) s2).getFirstName());
            }
           return s1.getEmailAddress().compareTo(((Trainee) s2).getEmailAddress());
        }};

    @Override
    public int compareTo(@NonNull Object o) {
        if (this.getLastName().compareTo(((Trainee) o).getLastName()) != 0)
            return this.getFirstName().compareTo(((Trainee) o).getLastName());
        else if (this.getFirstName().compareTo(((Trainee) o).getFirstName()) != 0) {
            return this.getFirstName().compareTo(((Trainee) o).getFirstName());
        }
        return this.getEmailAddress().compareTo(((Trainee) o).getEmailAddress());
    }
}

