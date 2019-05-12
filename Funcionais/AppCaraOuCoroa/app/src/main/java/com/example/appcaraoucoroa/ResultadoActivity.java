package com.example.appcaraoucoroa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;


public class ResultadoActivity extends AppCompatActivity implements Serializable {

    ImageView tvResultado;
    Button btnVoltar;
    int resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        tvResultado = findViewById(R.id.ivResultado);
        btnVoltar = findViewById(R.id.btnVoltar);

        Bundle dados = getIntent().getExtras();
        resultado = dados.getInt("Resultado");

        if (resultado == 0){
            tvResultado.setImageResource(R.drawable.moeda_cara);
        }
        else
        {
            tvResultado.setImageResource(R.drawable.moeda_coroa);
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
