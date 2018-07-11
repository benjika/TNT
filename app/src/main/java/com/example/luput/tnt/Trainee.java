package com.example.luput.tnt;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Trainee extends Person implements Comparable {

    private int TRAINEE_ID = 2;
    private List<String> Programs;

    public Trainee(String firstName, String lastName, String emailAddress) {
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


   /* public int compareTo(Trainee comparesto) {
        if (this.getLastName().compareTo(((Trainee) comparesto).getLastName()) != 0)
            return this.getFirstName().compareTo(((Trainee) comparesto).getLastName());
        else if (this.getFirstName().compareTo(((Trainee) comparesto).getFirstName()) != 0) {
            return this.getFirstName().compareTo(((Trainee) comparesto).getFirstName());
        }
        return this.getEmailAddress().compareTo(((Trainee) comparesto).getEmailAddress());
        // For Descending order do like this
        //return compareage-this.studentage;
    }*/

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
//
          //  //ascending order
          //  return StudentName1.compareTo(StudentName2);
//
          //  //descending order
          //  //return StudentName2.compareTo(StudentName1);
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