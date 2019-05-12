package com.example.appcaraoucoroa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnJogar;
    Random random;
    int resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJogar = findViewById(R.id.btnJogar);
        random = new Random();

        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado = random.nextInt(2);
                Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
                intent.putExtra("Resultado", resultado);
                startActivity(intent);
            }
        });
    }
}
