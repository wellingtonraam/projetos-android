package com.example.appfragments.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.appfragments.R;
import com.example.appfragments.fragments.ContatosFragment;

public class MainActivity extends AppCompatActivity {
Button btnContatos, bntConversas;
Fragment contatosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciando fragment
        contatosFragment = new ContatosFragment();

        //configurando fragment



    }
}
