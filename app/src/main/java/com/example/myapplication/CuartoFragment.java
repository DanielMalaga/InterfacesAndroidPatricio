package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.FragmentCuartoBinding;
import com.example.myapplication.databinding.FragmentTerceroBinding;

import java.io.File;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class CuartoFragment extends Fragment {

    private FragmentCuartoBinding binding;

    Button captura;
    Intent camara;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCuartoBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CuartoFragment.this)
                        .navigate(R.id.action_cuartoFragment_to_FirstFragment);
            }
        });

        captura = (Button) view.findViewById(R.id.captura);



        captura.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        camara=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        if(camara.resolveActivity(view.getContext().getPackageManager())!=null){
                            startActivityForResult(camara,1888);
                        }

                    }
                }
        );
    }

    public void onActivityResult(int request, int result, Intent data)
    {
        if(request==1888 && result== Activity.RESULT_OK)
        {
            Bundle extras=data.getExtras();
            Bitmap imageBitmap=(Bitmap) extras.get("data");
            binding.imagen.setImageBitmap(imageBitmap);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}