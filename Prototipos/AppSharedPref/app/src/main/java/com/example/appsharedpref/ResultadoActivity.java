package com.example.appsharedpref;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        tvResultado = findViewById(R.id.tvResultado);
        Bundle dados = getIntent().getExtras();
        tvResultado.setText("Olá, " + dados.get("nome") +"!");

    }
}
