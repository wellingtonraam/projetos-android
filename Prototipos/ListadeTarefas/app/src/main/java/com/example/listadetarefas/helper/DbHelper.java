package com.example.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

public static int VERSION  = 1;
public static String NOME_DB = "DB_TAREFAS";
public static String TABELA_TAREFAS = "tarefas";


    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS +
                     " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL ) ;";
        try {

            db.execSQL(sql);
            Log.e("INFO DB", "Sucesso ao criar tabela!");
        }

        catch (Exception e){
            Log.e("INFO DB", "Erro ao criar tabela: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
