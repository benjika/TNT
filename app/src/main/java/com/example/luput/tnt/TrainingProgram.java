package com.example.luput.tnt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrainingProgram implements Serializable {

    private String NameOfTheProgram;
    private ArrayList<ExerciseDrill> ListOfDrillsForA;
    private ArrayList<ExerciseDrill> ListOfDrillsForB;
    private String Type;
    private DaysOfTrainning DaysOfWorkOut;
    private boolean CurrentProgram;

    public TrainingProgram(String nameOfTheProgram, ArrayList<ExerciseDrill> listOfDrillsForA,
                           ArrayList<ExerciseDrill> listOfDrillsForB, String type,
                           DaysOfTrainning daysOfWorkOut, boolean currentProgram) {
        NameOfTheProgram = nameOfTheProgram;
        ListOfDrillsForA = listOfDrillsForA;
        ListOfDrillsForB = listOfDrillsForB;
        Type = type;
        DaysOfWorkOut = daysOfWorkOut;
        CurrentProgram = currentProgram;
    }

    public String getNameOfTheProgram() {
        return NameOfTheProgram;
    }

    public void setNameOfTheProgram(String nameOfTheProgram) {
        NameOfTheProgram = nameOfTheProgram;
    }

    public ArrayList<ExerciseDrill> getListOfDrillsForA() {
        return ListOfDrillsForA;
    }

    public void setListOfDrillsForA(ArrayList<ExerciseDrill> listOfDrillsForA) {
        ListOfDrillsForA = listOfDrillsForA;
    }

    public ArrayList<ExerciseDrill> getListOfDrillsForB() {
        return ListOfDrillsForB;
    }

    public void setListOfDrillsForB(ArrayList<ExerciseDrill> listOfDrillsForB) {
        ListOfDrillsForB = listOfDrillsForB;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public DaysOfTrainning getDaysOfWorkOut() {
        return DaysOfWorkOut;
    }

    public void setDaysOfWorkOut(DaysOfTrainning daysOfWorkOut) {
        DaysOfWorkOut = daysOfWorkOut;
    }

    public boolean isCurrentProgram() {
        return CurrentProgram;
    }

    public void setCurrentProgram(boolean currentProgram) {
        CurrentProgram = currentProgram;
    }

    public TrainingProgram() {/*defult ctor*/}


}
