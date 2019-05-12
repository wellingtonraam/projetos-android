package com.example.dadosentreactivies.activies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dadosentreactivies.R;

public class SegundaActivity extends AppCompatActivity {
TextView tvResultado;
ImageView ivResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        tvResultado = findViewById(R.id.tvResultado);
        ivResultado = findViewById(R.id.ivResultado);
        Bundle dados = getIntent().getExtras();
        int felicidade = dados.getInt("felicidade");
        if (felicidade < 5 ) {
            ivResultado.setImageResource(R.drawable.happy5);
            tvResultado.setText("Você é incrivel! Nunca desista dos seus sonhos!");

        }
        else{
            tvResultado.setText("Parabéns! Continue assim!");
            ivResultado.setImageResource(R.drawable.happy10);
    }

    }
}
