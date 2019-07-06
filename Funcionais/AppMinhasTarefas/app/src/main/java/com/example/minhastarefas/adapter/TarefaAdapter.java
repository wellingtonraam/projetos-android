package com.example.minhastarefas.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minhastarefas.R;
import com.example.minhastarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder>{

    List<Tarefa> listTarefas = new ArrayList<>();

    public TarefaAdapter(List<Tarefa> listTarefas) {
        this.listTarefas = listTarefas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_tarefa_adapter, viewGroup, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Tarefa tarefa = listTarefas.get(i);
        myViewHolder.tvTarefas.setText(tarefa.getNomeTarefa());
    }

    @Override
    public int getItemCount() {
        return listTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTarefas;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTarefas = itemView.findViewById(R.id.tvTarefas);
    }
}

}