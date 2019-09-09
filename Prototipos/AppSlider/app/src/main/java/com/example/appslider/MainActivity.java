package com.example.appslider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(new SimpleSlide.Builder().title("Ajude o meio-ambiente!")
                .description("Nosso país é rico em zonas ambientais, mas estamos perdendo elas")
                .background(android.R.color.white)
                .image(R.drawable.um)
                .build()
        );

        addSlide(new SimpleSlide.Builder().title("A gente precisa de você")
                .description("Temos mais da metade de toda zona ambiental da Amazonia desmatada.")
                .background(android.R.color.white)
                .image(R.drawable.dois)
                .build()
        );

        addSlide(new SimpleSlide.Builder().title("Você pode nos ajudar")
                .description("Com apenas uma foto e uma descrição, você pode nos ajudar a combater o desmatamento.")
                .background(android.R.color.white)
                .image(R.drawable.um)
                .build()
        );


    }
}
