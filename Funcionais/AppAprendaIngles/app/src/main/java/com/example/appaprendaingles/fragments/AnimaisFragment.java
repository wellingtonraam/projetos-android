package com.example.appaprendaingles.fragments;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.appaprendaingles.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimaisFragment extends Fragment implements View.OnClickListener{

    private ImageView imageDog, imageCat, imageLion, imageMonkey, imageSheep, imageCow;
    private MediaPlayer mediaPlayer;
    public AnimaisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_animais, container, false);

       //instanciando/referenciando os componentes da tela
       imageDog = view.findViewById(R.id.imageDog);
       imageCat = view.findViewById(R.id.imageCat);
       imageLion = view.findViewById(R.id.imageLion);
       imageMonkey = view.findViewById(R.id.imageMonkey);
       imageCow = view.findViewById(R.id.imageCow);
       imageSheep = view.findViewById(R.id.imageSheep);

       //configurando os eventos de click
        imageDog.setOnClickListener(this);
        imageCat.setOnClickListener(this);
        imageLion.setOnClickListener(this);
        imageMonkey.setOnClickListener(this);
        imageCow.setOnClickListener(this);
        imageSheep.setOnClickListener(this);

       return view;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageDog:
                tocarSom(R.raw.dog);
                break;
            case R.id.imageCat:
                tocarSom(R.raw.cat);
                break;
            case R.id.imageLion:
                tocarSom(R.raw.lion);
                break;
            case R.id.imageCow:
                tocarSom(R.raw.cow);
                break;
            case R.id.imageMonkey:
                tocarSom(R.raw.monkey);
                break;
            case R.id.imageSheep:
                tocarSom(R.raw.sheep);

                break;



        }
    }

    public void tocarSom(int som) {

        mediaPlayer = MediaPlayer.create(getActivity(), som);

            if (mediaPlayer != null)
            {
                mediaPlayer.start();
                if (mediaPlayer.isPlaying() == false)
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.release();
                    }
                });

            }

        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    }

