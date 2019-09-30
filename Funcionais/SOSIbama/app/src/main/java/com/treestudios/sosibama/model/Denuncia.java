package com.treestudios.sosibama.model;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.treestudios.sosibama.DenunciaActivity;
import com.treestudios.sosibama.config.ConfiguracaoFirebase;
import com.treestudios.sosibama.helper.DataUtil;

public class Denuncia {

    private String data, tipo, descricao, local;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Denuncia() {
    }


    public void salvar(String dataMovimentacao){

        String mesAno = DataUtil.mesAnoDataAtual(dataMovimentacao);

        DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference();
        //primeiro nó, movimentação
        databaseReference.child("denuncia")
                //terceiro nó, data
                .child(mesAno)
                //gerando nó unico do firebase
                .push()
                .setValue(this);
    }
}
