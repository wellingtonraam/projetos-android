package com.example.minhastarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static  int VERSION = 1;
    public static String NOME_DB = "DB_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Criando Banco de Dados

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL ); ";

        try {
            db.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar a tabela" );
        }

        catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


