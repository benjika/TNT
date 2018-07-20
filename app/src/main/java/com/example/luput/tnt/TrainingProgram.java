package com.example.luput.tnt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrainingProgram implements Serializable {

    private String NameOfTheProgram;
    private ArrayList<ExerciseDrill> ListOfDrills;
    //private String Type;
    private DaysOfTrainning DaysOfWorkOut;
    private boolean CurrentProgram;

    public TrainingProgram(String nameOfTheProgram,
                           ArrayList<ExerciseDrill> listOfDrills, /*String type,*/
                           DaysOfTrainning daysOfWorkOut, boolean currentProgram) {
        NameOfTheProgram = nameOfTheProgram;
        this.ListOfDrills = listOfDrills;
        // Type = type;
        DaysOfWorkOut = daysOfWorkOut;
        CurrentProgram = currentProgram;
    }

    public String getNameOfTheProgram() {
        return NameOfTheProgram;
    }

    public void setNameOfTheProgram(String nameOfTheProgram) {
        NameOfTheProgram = nameOfTheProgram;
    }

    public ArrayList<ExerciseDrill> getListOfDrills() {
        return ListOfDrills;
    }

    public void setListOfDrills(ArrayList<ExerciseDrill> listOfDrillsForA) {
        this.ListOfDrills = listOfDrillsForA;
    }

    /*
        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }
    */
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
