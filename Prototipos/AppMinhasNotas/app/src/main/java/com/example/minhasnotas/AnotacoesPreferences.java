package com.example.minhasnotas;

import android.content.Context;
import android.content.SharedPreferences;

public class AnotacoesPreferences {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private  final String NOME_ARQUIVO = "anotacoes.preferences";
    private final String CHAVE_NOME = "nome";

    public AnotacoesPreferences(Context c) {
        this.context = c;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, 0);
        editor = preferences.edit();

    }


    public void setAnotacao(String anotacao){
        editor.putString(CHAVE_NOME, anotacao);
        editor.commit();
    }


    public String getAnotacao(){
        return preferences.getString(CHAVE_NOME, "");

    }
}
