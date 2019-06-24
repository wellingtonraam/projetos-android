package com.example.appsharedpref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSalvar;
    TextInputEditText inputNome;
    private final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSalvar = findViewById(R.id.btnSalvar);
        inputNome = findViewById(R.id.inputNome);

        //recuperando dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        if(preferences.contains("nome")){
            startActivity(new Intent(getApplicationContext(), ResultadoActivity.class ).putExtra("nome", preferences.getString("nome", "")));

        }

    }





    public void salvarNome (View view){

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        SharedPreferences.Editor editor = preferences.edit();

        //Validando nome (caixa de texto)
        if (inputNome.getText().toString().equals("") ){

            Toast.makeText(getApplicationContext(), "O nome é obrigatório!", Toast.LENGTH_LONG).show();
        }
        else
        {
            editor.putString("nome", inputNome.getText().toString());
            editor.commit();
            startActivity(new Intent(getApplicationContext(), ResultadoActivity.class ).putExtra("nome", preferences.getString("nome", "")));
        }

    }


}
