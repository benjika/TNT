package com.example.luput.tnt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrainingProgram implements Serializable {

    private String NameOfTheProgram;
    private ArrayList<ExerciseDrill> ListOfDrillsChest;
    private ArrayList<ExerciseDrill> ListOfDrillsBack;
    private ArrayList<ExerciseDrill> ListOfDrillsBiceps;
    private ArrayList<ExerciseDrill> ListOfDrillsTriceps;
    private ArrayList<ExerciseDrill> ListOfDrillsLegs;
    private ArrayList<ExerciseDrill> ListOfDrillsShoulders;
    private ArrayList<ExerciseDrill> ListOfDrillsABs;
    //private String Type;
    private DaysOfTrainning DaysOfWorkOut;
    private boolean CurrentProgram;

    public TrainingProgram(String nameOfTheProgram, ArrayList<ExerciseDrill> listOfDrillsChest_n,
                           ArrayList<ExerciseDrill> listOfDrillsBack_n, ArrayList<ExerciseDrill> listOfDrillsBiceps_n,
                           ArrayList<ExerciseDrill> listOfDrillsTriceps_n, ArrayList<ExerciseDrill> listOfDrillsLegs_n,
                           ArrayList<ExerciseDrill> listOfDrillsShoulders_n, ArrayList<ExerciseDrill> listOfDrillsABs_n,
                           DaysOfTrainning daysOfWorkOut, boolean currentProgram) {
        NameOfTheProgram = nameOfTheProgram;
        this.ListOfDrillsChest = listOfDrillsChest_n;
        this.ListOfDrillsBack = listOfDrillsBack_n;
        this.ListOfDrillsBiceps = listOfDrillsBiceps_n;
        this.ListOfDrillsTriceps = listOfDrillsTriceps_n;
        this.ListOfDrillsLegs = listOfDrillsLegs_n;
        this.ListOfDrillsShoulders = listOfDrillsShoulders_n;
        this.ListOfDrillsABs = listOfDrillsABs_n;
        DaysOfWorkOut = daysOfWorkOut;
        CurrentProgram = currentProgram;
    }

    public ArrayList<ExerciseDrill> getListOfDrillsChest() {
        if (ListOfDrillsChest == null) {
            this.ListOfDrillsChest = new ArrayList<>();
        }
        return ListOfDrillsChest;
    }

    public ArrayList<ExerciseDrill> getListOfDrillsBack() {
        if (this.ListOfDrillsBack == null) this.ListOfDrillsBack = new ArrayList<>();
        return ListOfDrillsBack;
    }

    public void setListOfDrillsBack(ArrayList<ExerciseDrill> listOfDrillsBack) {
        this.ListOfDrillsBack = listOfDrillsBack;
    }

    public ArrayList<ExerciseDrill> getListOfDrillsBiceps() {
        if (this.ListOfDrillsBiceps == null) this.ListOfDrillsBiceps = new ArrayList<>();
        return ListOfDrillsBiceps;
    }

    public void setListOfDrillsBiceps(ArrayList<ExerciseDrill> listOfDrillsBiceps) {
        ListOfDrillsBiceps = listOfDrillsBiceps;
    }

    public ArrayList<ExerciseDrill> getListOfDrillsTriceps() {
        if (ListOfDrillsTriceps == null) this.ListOfDrillsTriceps = new ArrayList<>();
        return ListOfDrillsTriceps;
    }

    public void setListOfDrillsTriceps(ArrayList<ExerciseDrill> listOfDrillsTriceps) {
        ListOfDrillsTriceps = listOfDrillsTriceps;
    }

    public ArrayList<ExerciseDrill> getListOfDrillsLegs() {
        if (this.ListOfDrillsLegs == null) this.ListOfDrillsLegs = new ArrayList<>();
        return ListOfDrillsLegs;
    }

    public void setListOfDrillsLegs(ArrayList<ExerciseDrill> listOfDrillsLegs) {
        ListOfDrillsLegs = listOfDrillsLegs;
    }

    public ArrayList<ExerciseDrill> getListOfDrillsShoulders() {
        if (this.ListOfDrillsShoulders == null) this.ListOfDrillsShoulders = new ArrayList<>();
        return ListOfDrillsShoulders;
    }

    public void setListOfDrillsShoulders(ArrayList<ExerciseDrill> listOfDrillsShoulders) {
        ListOfDrillsShoulders = listOfDrillsShoulders;
    }

    public ArrayList<ExerciseDrill> getListOfDrillsABs() {
        if (this.ListOfDrillsABs == null) this.ListOfDrillsABs = new ArrayList<>();
        return ListOfDrillsABs;
    }

    public void setListOfDrillsABs(ArrayList<ExerciseDrill> listOfDrillsABs) {
        ListOfDrillsABs = listOfDrillsABs;
    }

    public String getNameOfTheProgram() {
        return NameOfTheProgram;
    }

    public void setNameOfTheProgram(String nameOfTheProgram) {
        NameOfTheProgram = nameOfTheProgram;
    }

    public void setListOfDrillsChest(ArrayList<ExerciseDrill> listOfDrills) {
        this.ListOfDrillsChest = listOfDrills;
    }

    public int getSizeOfDrillsChest() {
        if (ListOfDrillsChest == null) {
            this.ListOfDrillsChest = new ArrayList<>();
            return 0;
        }
        return ListOfDrillsChest.size();
    }

    public void addNewDrillChest(ExerciseDrill exerciseDrill) {
        if (this.ListOfDrillsChest == null) this.ListOfDrillsChest = new ArrayList<>();
        ListOfDrillsChest.add(exerciseDrill);
    }

    public void addNewDrillBack(ExerciseDrill exerciseDrill) {
        if (this.ListOfDrillsBack == null) this.ListOfDrillsBack = new ArrayList<>();
        ListOfDrillsBack.add(exerciseDrill);
    }

    public int getSizeOfDrillsBack() {
        if (this.ListOfDrillsBack == null) {
            this.ListOfDrillsBack = new ArrayList<>();
            return 0;
        }
        return ListOfDrillsBack.size();
    }

    public void addNewDrillBiceps(ExerciseDrill exerciseDrill) {
        if (this.ListOfDrillsBiceps == null) this.ListOfDrillsBiceps = new ArrayList<>();
        ListOfDrillsBiceps.add(exerciseDrill);
    }

    public int getSizeOfDrillsBiceps() {
        if (this.ListOfDrillsBiceps == null) {
            this.ListOfDrillsBiceps = new ArrayList<>();
            return 0;
        }
        return ListOfDrillsBiceps.size();
    }

    public void addNewDrillTriceps(ExerciseDrill exerciseDrill) {
        if (this.ListOfDrillsTriceps == null) this.ListOfDrillsTriceps = new ArrayList<>();
        ListOfDrillsTriceps.add(exerciseDrill);
    }

    public int getSizeOfDrillsTriceps() {
        if (this.ListOfDrillsTriceps == null) {
            this.ListOfDrillsTriceps = new ArrayList<>();
            return 0;
        }
        return ListOfDrillsTriceps.size();
    }

    public void addNewDrillShoulders(ExerciseDrill exerciseDrill) {
        if (this.ListOfDrillsShoulders == null) this.ListOfDrillsShoulders = new ArrayList<>();
        ListOfDrillsShoulders.add(exerciseDrill);
    }

    public int getSizeOfDrillsShoulders() {
        if (this.ListOfDrillsShoulders == null) {
            this.ListOfDrillsShoulders = new ArrayList<>();
            return 0;
        }
        return ListOfDrillsShoulders.size();
    }

    public void addNewDrillLegs(ExerciseDrill exerciseDrill) {
        if (this.ListOfDrillsLegs == null) this.ListOfDrillsLegs = new ArrayList<>();
        ListOfDrillsLegs.add(exerciseDrill);
    }

    public int getSizeOfDrillsLegs() {
        if (this.ListOfDrillsLegs == null) {
            this.ListOfDrillsLegs = new ArrayList<>();
            return 0;
        }
        return ListOfDrillsLegs.size();
    }

    public void addNewDrillABs(ExerciseDrill exerciseDrill) {
        if (this.ListOfDrillsABs == null) this.ListOfDrillsABs = new ArrayList<>();
        ListOfDrillsABs.add(exerciseDrill);
    }

    public int getSizeOfDrillsABs() {
        if (this.ListOfDrillsABs == null) {
            this.ListOfDrillsABs = new ArrayList<>();
            return 0;
        }
        return ListOfDrillsABs.size();
    }

    public int getSizeOfTotalPrograms() {
        return getSizeOfDrillsChest() + getSizeOfDrillsBack() + getSizeOfDrillsBiceps() +
                getSizeOfDrillsTriceps() + getSizeOfDrillsShoulders() + getSizeOfDrillsABs() +
                getSizeOfDrillsLegs();
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
