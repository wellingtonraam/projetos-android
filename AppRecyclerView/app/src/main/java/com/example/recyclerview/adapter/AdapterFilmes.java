package com.example.recyclerview.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerview.R;
import com.example.recyclerview.model.Filme;

import java.util.List;

public class AdapterFilmes extends RecyclerView.Adapter<AdapterFilmes.MyViewHolder> {

    private List<Filme> listaFilmes;

    public AdapterFilmes(List<Filme> lista) {
    this.listaFilmes = lista;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filmes, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Filme filme = listaFilmes.get(position);
        holder.tvNomeCanal.setText(filme.getCanal());
        holder.tvTituloVideo.setText(filme.getTitulo());
        holder.tvVisualizacoes.setText(filme.getVisualizacoes());
        holder.ivThumbnail.setImageResource(filme.getThumbnail());

    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{


        TextView tvTituloVideo;
        TextView tvNomeCanal;
        TextView tvVisualizacoes;
        ImageView ivThumbnail;

        public MyViewHolder(View itemView) {
                super(itemView);
                tvTituloVideo = itemView.findViewById(R.id.tvTituloVideo);
                tvNomeCanal = itemView.findViewById(R.id.tvNomeCanal);
                tvVisualizacoes = itemView.findViewById(R.id.tvVisualizacoes);
                ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            }

        }

}
