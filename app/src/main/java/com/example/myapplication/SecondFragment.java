package com.example.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private VideoView mVideoView;
    private final String videoUrl="http://techslides.com/demos/sample-videos/small.mp4";



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mVideoView = (VideoView) view.findViewById(R.id.videoView);
        Uri uri = Uri.parse(videoUrl);
        mVideoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(view.getContext());

        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(mVideoView);

        // sets the media player to the videoView
        mediaController.setMediaPlayer(mVideoView);

        // sets the media controller to the videoView
        mVideoView.setMediaController(mediaController);

        // starts the video
        mVideoView.start();

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}