package com.example.luput.tnt;

public class ExerciseDrill {

    private String NameOfExercise;
    private String LinkToVideo;
    private float WeightInKg;
    private int NumberOfSets;
    private int NumberOfRepeat;
    private int NumberOfRestInSeconds;
    private int DurationOfRunInMin;
    private float LengthOfRunInKM;
    private String Description;


    //region Ctors

    public ExerciseDrill(String nameOfExercise, String linkToVideo, float weightInKg, int numberOfSets, int numberOfRepeat, int numberOfRestInSeconds, String description) {
        NameOfExercise = nameOfExercise;
        LinkToVideo = linkToVideo;
        WeightInKg = weightInKg;
        NumberOfSets = numberOfSets;
        NumberOfRepeat = numberOfRepeat;
        NumberOfRestInSeconds = numberOfRestInSeconds;
        Description = description;
    }

    public ExerciseDrill(String nameOfExercise, int durationOfRunInMin, String description) {
        NameOfExercise = nameOfExercise;
        DurationOfRunInMin = durationOfRunInMin;
        Description = description;
    }

    public ExerciseDrill(String nameOfExercise, float lengthOfRunInKM, String description) {
        NameOfExercise = nameOfExercise;
        LengthOfRunInKM = lengthOfRunInKM;
        Description = description;
    }

    //endregion

    //region Getters

    public String getNameOfExercise() {
        return NameOfExercise;
    }

    public String getLinkToVideo() {
        return LinkToVideo;
    }

    public float getWeightInKg() {
        return WeightInKg;
    }

    public int getNumberOfSets() {
        return NumberOfSets;
    }

    public int getNumberOfRepeat() {
        return NumberOfRepeat;
    }

    public int getNumberOfRestInSeconds() {
        return NumberOfRestInSeconds;
    }

    public int getDurationOfRunInMin() {
        return DurationOfRunInMin;
    }

    public float getLengthOfRunInKM() {
        return LengthOfRunInKM;
    }

    public String getDescription() {
        return Description;
    }


    //endregion
}
