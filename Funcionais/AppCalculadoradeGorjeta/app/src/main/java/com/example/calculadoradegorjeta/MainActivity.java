package com.example.calculadoradegorjeta;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar percentSB;
    TextView percent;
    TextView resultGorjeta;
    TextView resultTotal;
    EditText valuePrice;
    double valorDigitado = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        percentSB = findViewById(R.id.seekBar);
        percent = findViewById(R.id.tvPercent);
        resultGorjeta = findViewById(R.id.tvGorjeta);
        resultTotal = findViewById(R.id.tvTotal);
        valuePrice = findViewById(R.id.etPrice);
        percentSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percent.setText(percentSB.getProgress() + "%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
             if("".equals(valuePrice.getText().toString())) {


               valuePrice.setText("");


                } else {

                 valorDigitado = Double.parseDouble(valuePrice.getText().toString());
                 double porcentagem = percentSB.getProgress();

                 double gorjeta = valorDigitado * (porcentagem / 100);
                 double total = valorDigitado + gorjeta;
                 resultGorjeta.setText("R$ " + Math.round(gorjeta));
                 resultTotal.setText("R$ " + Math.round(total));
             }
             }
        });

    }




}
