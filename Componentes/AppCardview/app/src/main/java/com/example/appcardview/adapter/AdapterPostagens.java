package com.example.appcardview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcardview.R;
import com.example.appcardview.model.Postagens;

import java.util.List;

public class AdapterPostagens extends RecyclerView.Adapter<AdapterPostagens.MyViewHolder> {

    private List<Postagens> postagensList;

    public AdapterPostagens(List<Postagens> lista) {
        this.postagensList = lista;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_postagens, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Postagens postagens = postagensList.get(position);
        holder.tvTitulo.setText(postagens.getTitulo());
        holder.ivThumbnail.setImageResource(postagens.getThumbnail());

    }

    @Override
    public int getItemCount() {
        return postagensList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{


        TextView tvTitulo;
        ImageView ivThumbnail;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
        }

    }
}
