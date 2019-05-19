package com.example.snackbar;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
Button btnIniciar;
ImageView ivIniciar;
ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraintLayout);
        ivIniciar = findViewById(R.id.ivLogo);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Você quer trocar o GIF?", Snackbar.LENGTH_LONG)
                        .setAction("Sim", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ivIniciar.setImageResource(R.drawable.animation);
                                constraintLayout.setBackgroundColor(getResources().getColor(R.color.backgroundGreen));

                            }
                        })
                        .show();
            }
        });
    }
}
