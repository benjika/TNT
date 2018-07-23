package com.example.luput.tnt;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class FullProgramFragment extends Fragment {
    TextView ProgramHeadLine;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_trainingprogram, container, false);

        context = container.getContext();

        final TrainingProgram ProgramToShow = (TrainingProgram) getArguments().getSerializable("ProgramToShow");
        ProgramHeadLine = view.findViewById(R.id.fullProgram_headline);
        ProgramHeadLine.setText(ProgramToShow.getNameOfTheProgram());

        ExpandableLayout expandableLayout = (ExpandableLayout) view.findViewById(R.id.fullProgram_expandable);
        expandableLayout.setRenderer(new ExpandableLayout.Renderer<MusculeCategory, ExerciseDrill>() {
            @Override
            public void renderParent(View view, MusculeCategory musculeCategory, boolean isExpanded, int parentPosition) {
                ((TextView) view.findViewById(R.id.expandable_parentName)).setText(musculeCategory.name);
                view.findViewById(R.id.expandable_arrow).setBackgroundResource
                        (isExpanded ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down);
            }

            @Override
            public void renderChild(View view2, final ExerciseDrill exerciseDrill, int parentPosition, int childPosition) {
                ((TextView) view2.findViewById(R.id.drillHolder_drillName)).
                        setText(exerciseDrill.getNameOfExercise());
                ((TextView) view2.findViewById(R.id.drillHolder_NumberOfSets)).
                        setText(exerciseDrill.getNumberOfSets() + "");
                ((TextView) view2.findViewById(R.id.drillHolder_weightInKg)).
                        setText(exerciseDrill.getWeightInKg() + "");
                ((TextView) view2.findViewById(R.id.drillHolder_NumberOfRepeats)).
                        setText(exerciseDrill.getNumberOfRepeat() + "");
                ((TextView) view2.findViewById(R.id.drillHolder_RestInSeconds)).
                        setText(exerciseDrill.getNumberOfRestInSeconds() + "");
                if (!(exerciseDrill.getDescription()).equals("")) {
                    ((TextView) view2.findViewById(R.id.drillHolder_drillDescription)).
                            setText(exerciseDrill.getDescription());
                } else {
                    ((TextView) view2.findViewById(R.id.drillHolder_drillDescription)).
                            setVisibility(View.GONE);
                }
                if (!(exerciseDrill.getLinkToVideo()).equals("")) {
                    final String str = exerciseDrill.getLinkToVideo();
                    ((TextView) view2.findViewById(R.id.drillHolder_LinkToDrillMovie)).
                            setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view1) {
                                    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + exerciseDrill.getLinkToVideo()));
                                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("http://www.youtube.com/watch?v=" + exerciseDrill.getLinkToVideo()));
                                    try {
                                        context.startActivity(appIntent);
                                    } catch (ActivityNotFoundException ex) {
                                        context.startActivity(webIntent);
                                    }
                                }
                            });
                } else {
                    ((TextView) view2.findViewById(R.id.drillHolder_LinkToDrillMovie)).setVisibility(View.GONE);
                }

            }

        });

        inputToExpandableLayout(expandableLayout, ProgramToShow);
        return view;
    }

    void inputToExpandableLayout(ExpandableLayout expandableLayout, TrainingProgram trainingProgram) {
        String[] musclesGroup = getResources().getStringArray(R.array.muscle_groups);
        if (trainingProgram.getSizeOfDrillsChest() > 0) {
            Section<MusculeCategory, ExerciseDrill> section = new Section<>();
            section.parent = new MusculeCategory(musclesGroup[1]);
            section.children = trainingProgram.getListOfDrillsChest();
            expandableLayout.addSection(section);
        }
        if (trainingProgram.getSizeOfDrillsBack() > 0) {
            Section<MusculeCategory, ExerciseDrill> section = new Section<>();
            section.parent = new MusculeCategory(musclesGroup[2]);
            section.children = trainingProgram.getListOfDrillsBack();
            expandableLayout.addSection(section);
        }
        if (trainingProgram.getSizeOfDrillsBiceps() > 0) {
            Section<MusculeCategory, ExerciseDrill> section = new Section<>();
            section.parent = new MusculeCategory(musclesGroup[3]);
            section.children = trainingProgram.getListOfDrillsBiceps();
            expandableLayout.addSection(section);
        }
        if (trainingProgram.getSizeOfDrillsTriceps() > 0) {
            Section<MusculeCategory, ExerciseDrill> section = new Section<>();
            section.parent = new MusculeCategory(musclesGroup[4]);
            section.children = trainingProgram.getListOfDrillsTriceps();
            expandableLayout.addSection(section);
        }
        if (trainingProgram.getSizeOfDrillsABs() > 0) {
            Section<MusculeCategory, ExerciseDrill> section = new Section<>();
            section.parent = new MusculeCategory(musclesGroup[5]);
            section.children = trainingProgram.getListOfDrillsABs();
            expandableLayout.addSection(section);
        }
        if (trainingProgram.getSizeOfDrillsShoulders() > 0) {
            Section<MusculeCategory, ExerciseDrill> section = new Section<>();
            section.parent = new MusculeCategory(musclesGroup[6]);
            section.children = trainingProgram.getListOfDrillsShoulders();
            expandableLayout.addSection(section);
        }
        if (trainingProgram.getSizeOfDrillsLegs() > 0) {
            Section<MusculeCategory, ExerciseDrill> section = new Section<>();
            section.parent = new MusculeCategory(musclesGroup[7]);
            section.children = trainingProgram.getListOfDrillsLegs();
            expandableLayout.addSection(section);
        }
    }
}
