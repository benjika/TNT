package com.example.luput.tnt;


import android.app.Activity;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;

public class Utils {
    public final String TRAINEE_BRANCH = "trainee";
    public final String COACH_BRANCH = "coach";
    public final String PROGRAM_BRANCH = "programs";
    public final String IMG_BRANCH = "img";

    public void makeSimplePopup(String HeadLine, String Content, Activity activity) {
        new OoOAlertDialog.Builder(activity)
                .setTitle(HeadLine)
                .setMessage(Content)
                .setAnimation(Animation.POP)
                .setPositiveButton("Ok", null)
                .build();
    }
}
