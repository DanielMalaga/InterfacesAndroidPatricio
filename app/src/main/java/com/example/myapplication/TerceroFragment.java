package com.example.myapplication;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.myapplication.databinding.FragmentTerceroBinding;

import java.io.Console;
import java.io.File;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class TerceroFragment extends Fragment {

    private FragmentTerceroBinding binding;

    MediaRecorder recorder;
    File audiofile = null;
    Button boton;
    boolean estadoG;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentTerceroBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        estadoG=false;
        boton = (Button) view.findViewById(R.id.grabar);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!estadoG) {
                    grabar(view);
                    estadoG=true;
                }
                else{
                    detener(view);
                    estadoG=false;
                }

            }
        });

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TerceroFragment.this)
                        .navigate(R.id.action_terceroFragment_to_FirstFragment);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void grabar(View v) {

        File dir = Environment.getExternalStorageDirectory();
        try {
            audiofile = File.createTempFile("sound", ".3gp", dir);
        } catch (IOException e) {
            Log.e("errorP", "external storage access error");
            return;
        }


        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(audiofile.getAbsolutePath());


        try {
            recorder.prepare();
            recorder.start();
            boton.setText("Pausa");
        } catch (IOException e) {
            Log.d("errorP", e.toString());
            Toast.makeText(v.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    public void detener(View v) {
        recorder.stop();
        recorder.release();
        boton.setText("Grabar");

    }

    public void reproducir(View v)
    {
        MediaPlayer player = new MediaPlayer();
        //player.setOnCompletionListener(this);
        try {
            player.setDataSource(audiofile.getAbsolutePath());
            player.prepare();
        } catch (IOException e) {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}