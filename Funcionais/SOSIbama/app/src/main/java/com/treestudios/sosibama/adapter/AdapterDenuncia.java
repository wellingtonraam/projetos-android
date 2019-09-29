package com.treestudios.sosibama.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.treestudios.sosibama.R;
import com.treestudios.sosibama.model.Denuncia;

import java.util.List;

public class AdapterDenuncia extends RecyclerView.Adapter<AdapterDenuncia.MyViewHolder> {

        List<Denuncia> denuncias;
        Context context;

        public AdapterDenuncia(List<Denuncia> denuncias, Context context) {
            this.denuncias = denuncias;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_denuncia, parent, false);
            return new MyViewHolder(itemLista);
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Denuncia denuncia = denuncias.get(position);

            holder.titulo.setText(denuncia.getTipo());
            holder.local.setText(denuncia.getLocal());

        }


        @Override
        public int getItemCount() {
            return denuncias.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView titulo, local;

            public MyViewHolder(View itemView) {
                super(itemView);

                titulo = itemView.findViewById(R.id.tvAdapterTitulo);
                local = itemView.findViewById(R.id.tvAdapterLocal);
            }

        }

    }
