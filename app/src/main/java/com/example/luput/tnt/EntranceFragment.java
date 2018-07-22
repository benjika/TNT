package com.example.luput.tnt;


import android.content.Context;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EntranceFragment extends Fragment {

    private VideoView videoview;


    public static EntranceFragment newInstance() {
        EntranceFragment entranceFragment = new EntranceFragment();
        return entranceFragment;
    }

    public EntranceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final View view = inflater.inflate(R.layout.fragment_entrance, container, false);
        videoview = (VideoView) view.findViewById(R.id.video_view);
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.movie);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}


