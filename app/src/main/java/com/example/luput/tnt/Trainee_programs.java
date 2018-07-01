package com.example.luput.tnt;

import java.util.List;

public class Trainee_programs {
    private String TrainneUID;
    private List<String> PtogramsUID;

    public Trainee_programs(){/*defult ctor*/}

    public String getTrainneUID() {
        return TrainneUID;
    }

    public void setTrainneUID(String trainneUID) {
        TrainneUID = trainneUID;
    }

    public List<String> getPtograms() {
        return PtogramsUID;
    }

    public void setPtograms(List<String> ptograms) {
        PtogramsUID = ptograms;
    }
}
