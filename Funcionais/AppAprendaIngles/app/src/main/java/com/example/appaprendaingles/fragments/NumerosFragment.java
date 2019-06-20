package com.example.appaprendaingles.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
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


public class NumerosFragment extends Fragment implements View.OnClickListener {

    ImageView imageOne, imageTwo, imageThree, imageFour, imageFive, imageSix;
    MediaPlayer mediaPlayer;


    public NumerosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view = inflater.inflate(R.layout.fragment_numeros, container, false);

       imageOne = view.findViewById(R.id.imageOne);
       imageTwo = view.findViewById(R.id.imageTwo);
       imageThree = view.findViewById(R.id.imageThree);
       imageFour = view.findViewById(R.id.imageFour);
       imageFive = view.findViewById(R.id.imageFive);
       imageSix = view.findViewById(R.id.imageSix);

        imageOne.setOnClickListener(this);
        imageTwo.setOnClickListener(this);
        imageThree.setOnClickListener(this);
        imageFour.setOnClickListener(this);
        imageFive.setOnClickListener(this);
        imageSix.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.imageOne:
                tocarSom(R.raw.one);
                break;
            case R.id.imageTwo:
                tocarSom(R.raw.two);
                break;
            case R.id.imageThree:
                tocarSom(R.raw.three);
                break;
            case R.id.imageFour:
                tocarSom(R.raw.four);
                break;
            case R.id.imageFive:
                tocarSom(R.raw.five);
                break;
            case R.id.imageSix:
                tocarSom(R.raw.six);
                break;
        }

    }

    public void tocarSom(int som){

        mediaPlayer = MediaPlayer.create(getActivity(), som);
        if(mediaPlayer != null)
        {
            mediaPlayer.start();
            if (mediaPlayer.isPlaying() == false){

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.release();
                    }
                });

            }

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
