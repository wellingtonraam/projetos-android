package com.example.minhasnotas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editAnotacao;
    AnotacoesPreferences anotacoesPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editAnotacao = findViewById(R.id.editText);
        anotacoesPreferences = new AnotacoesPreferences(getApplicationContext());


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Validand
                if(editAnotacao.getText().toString().equals("")){

                    Snackbar.make(view,"Texto não pode estar vazio!", Snackbar.LENGTH_LONG).show();
                }
                else {

                    anotacoesPreferences.setAnotacao(editAnotacao.getText().toString());
                    Snackbar.make(view, "Texto salvo com sucesso!", Snackbar.LENGTH_LONG).show();


                }
            }
        });


        // Recuperando anotação


        if (!anotacoesPreferences.getAnotacao().equals("")){
            editAnotacao.setText(anotacoesPreferences.getAnotacao());
        }

    }

}
