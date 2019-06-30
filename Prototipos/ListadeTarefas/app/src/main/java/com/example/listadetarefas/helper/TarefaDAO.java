package com.example.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements  ITarefaDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;



    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {

         ContentValues cv  = new ContentValues();

         cv.put("nome", tarefa.getNomeTarefa());

        try {

            escreve.insert(DbHelper.TABELA_TAREFAS, null, cv);
            Log.e("INFO DB", "Tarefa salva com sucesso!");

        }

        catch (Exception e){
            Log.e("INFO DB", "Erro ao salvar dados: " + e.getMessage() );
            return  false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        return false;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> listar() {


        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ;";

        Cursor  cursor = le.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Tarefa tarefa = new Tarefa();
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String nomeTarefa = cursor.getString(cursor.getColumnIndex("nome"));
            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);
            tarefas.add(tarefa);
        }

        return tarefas;
    }
}
