package com.example.luput.tnt;

import java.io.Serializable;
import java.util.List;

public class TrainingProgram implements Serializable {

    private String NameOfTheProgram;
    private String[] ListOfDrillsForA;
    private String[] ListOfDrillsForB;
    private String Type;
    private DaysOfTrainning DaysOfWorkOut;
    private boolean CurrentProgram;

    public TrainingProgram(String nameOfTheProgram, String[] listOfDrillsForA, String[] listOfDrillsForB, String type, DaysOfTrainning daysOfWorkOut, boolean currentProgram) {
        NameOfTheProgram = nameOfTheProgram;
        Type = type;
        DaysOfWorkOut = daysOfWorkOut;
        CurrentProgram = currentProgram;
        if(type.equals("AB")){
            ListOfDrillsForA = listOfDrillsForA;
            ListOfDrillsForB = listOfDrillsForB;
        }
        else{
            ListOfDrillsForA = listOfDrillsForA;
            ListOfDrillsForB = null;
        }
    }

    public TrainingProgram() {/*defult ctor*/}

//region Gettrers


    public boolean isCurrentProgram() {
        return CurrentProgram;
    }

    public String getNameOfTheProgram() {
        return NameOfTheProgram;
    }

    public String[] getListOfDrillsForA() {
        return ListOfDrillsForA;
    }

    public String[] getListOfDrillsForB() {
        return ListOfDrillsForB;
    }

    public String getType() {
        return Type;
    }

    public DaysOfTrainning getDaysOfWorkOut() {
        return DaysOfWorkOut;
    }

    //endregion
}
