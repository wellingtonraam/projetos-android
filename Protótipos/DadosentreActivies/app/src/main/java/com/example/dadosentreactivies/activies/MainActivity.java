package com.example.dadosentreactivies.activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dadosentreactivies.R;

public class MainActivity extends AppCompatActivity {
SeekBar seekBarFelicidade;
Button btnEnviar;
TextView tvFelicidade;
int felicidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarFelicidade = findViewById(R.id.seekBar);
        btnEnviar = findViewById(R.id.btnEnviar);
        tvFelicidade = findViewById(R.id.tvFelicidade);

        seekBarFelicidade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                felicidade = seekBarFelicidade.getProgress();
                tvFelicidade.setText("Me sinto: " + felicidade + "% feliz hoje!");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SegundaActivity.class);
                intent.putExtra("felicidade",felicidade );
                startActivity(intent);
            }
        });

    }
}
