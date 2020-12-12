package com.treestudios.centralunicadedenunciasdoibama.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.treestudios.centralunicadedenunciasdoibama.R;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               abrirFazerDenuncia();
            }
        });
    }


    public void abrirFazerDenuncia(){

        Intent intent = new Intent(getApplicationContext(), FazerDenunciaActivity.class);
        startActivity(intent);

    }

}