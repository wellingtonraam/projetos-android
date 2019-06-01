package com.example.appmediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = new MediaPlayer();
        mediaPlayer = mediaPlayer.create(getApplicationContext(), R.raw.bach);
        inicializarSeekBar();
    }

    public void inicializarSeekBar(){
    seekVolume = findViewById(R.id.seekVolume);

    //Configurando audioMananger

    audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

    //Recuperando os valores do volume maximo e atual

    int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

    //Configurando seekBar apartir dos novos valores obtidos

    seekVolume.setMax(volumeMaximo);
    seekVolume.setProgress(volumeAtual);

    //Configurando OnClick
    seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
         audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress,0);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });


    }

    public void executarSom(View view){

        if (mediaPlayer != null){
            mediaPlayer.start();
        }

    }

    public void pausarSom(View view){

        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public void pararSom(View view)
    {

        mediaPlayer.stop();

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null || mediaPlayer.isPlaying() ){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
