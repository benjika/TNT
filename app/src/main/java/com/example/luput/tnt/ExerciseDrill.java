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

    public ExerciseDrill(String nameOfExercise, String description) {
        NameOfExercise = nameOfExercise;
        Description = description;
    }

    public ExerciseDrill() {
    }

    //endregion

    //region Getters + Setters

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

    public void setNameOfExercise(String nameOfExercise) {
        NameOfExercise = nameOfExercise;
    }

    public void setLinkToVideo(String linkToVideo) {
        LinkToVideo = linkToVideo;
    }

    public void setWeightInKg(float weightInKg) {
        WeightInKg = weightInKg;
    }

    public void setNumberOfSets(int numberOfSets) {
        NumberOfSets = numberOfSets;
    }

    public void setNumberOfRepeat(int numberOfRepeat) {
        NumberOfRepeat = numberOfRepeat;
    }

    public void setNumberOfRestInSeconds(int numberOfRestInSeconds) {
        NumberOfRestInSeconds = numberOfRestInSeconds;
    }

    public void setDurationOfRunInMin(int durationOfRunInMin) {
        DurationOfRunInMin = durationOfRunInMin;
    }

    public void setLengthOfRunInKM(float lengthOfRunInKM) {
        LengthOfRunInKM = lengthOfRunInKM;
    }

    public void setDescription(String description) {
        Description = description;
    }

    //endregion
}
