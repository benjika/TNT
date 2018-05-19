package com.example.luput.tnt;

import java.util.List;

public class TrainingProgram {

    private String NameOfTheProgram;
    private List<ExerciseDrill> ListOfDrillsForA;
    private List<ExerciseDrill> ListOfDrillsForB;
    private Enums.Type_Of_Training_Program Type;
    private List<Enums.Days_Of_The_Week> DaysOfWorkOut;
    private boolean CurrentProgram;

    public TrainingProgram(String nameOfTheProgram, List<ExerciseDrill> listOfDrillsForA, List<ExerciseDrill> listOfDrillsForB, Enums.Type_Of_Training_Program type, List<Enums.Days_Of_The_Week> daysOfWorkOut) {
        if(type == Enums.Type_Of_Training_Program.AB) { // for AB program need 2 lists
            NameOfTheProgram = nameOfTheProgram;
            ListOfDrillsForA = listOfDrillsForA;
            ListOfDrillsForB = listOfDrillsForB;
            Type = type;
            DaysOfWorkOut = daysOfWorkOut;
        }
        else{ // if not AB no need 2 list
            NameOfTheProgram = nameOfTheProgram;
            ListOfDrillsForA = listOfDrillsForA;
            ListOfDrillsForB = null;
            Type = type;
            DaysOfWorkOut = daysOfWorkOut;
        }
        CurrentProgram = false;
    }

    //region Gettrers


    public boolean isCurrentProgram() {
        return CurrentProgram;
    }

    public String getNameOfTheProgram() {
        return NameOfTheProgram;
    }

    public List<ExerciseDrill> getListOfDrillsForA() {
        return ListOfDrillsForA;
    }

    public List<ExerciseDrill> getListOfDrillsForB() {
        return ListOfDrillsForB;
    }

    public Enums.Type_Of_Training_Program getType() {
        return Type;
    }

    public List<Enums.Days_Of_The_Week> getDaysOfWorkOut() {
        return DaysOfWorkOut;
    }

    //endregion
}
