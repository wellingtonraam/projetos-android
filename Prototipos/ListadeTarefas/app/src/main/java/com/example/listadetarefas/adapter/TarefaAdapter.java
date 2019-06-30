package com.example.listadetarefas.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.listadetarefas.R;
import com.example.listadetarefas.model.Tarefa;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {

  private List<Tarefa> listaTarefa;


    public TarefaAdapter(List<Tarefa> lista) {

        this.listaTarefa = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_adicionar_tarefa, viewGroup, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


                Tarefa tarefa = listaTarefa.get(i);
                myViewHolder.tvNomeTarefa.setText(tarefa.getNomeTarefa());

    }

    @Override
    public int getItemCount() {

        return this.listaTarefa.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNomeTarefa;

        public MyViewHolder (View itemView){
            super(itemView);
            tvNomeTarefa = itemView.findViewById(R.id.tvNomeTarefa);
        }


    }
}
