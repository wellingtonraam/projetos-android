package com.example.beeplanner.models;

import com.example.beeplanner.config.ConfiguracaoFirebase;
import com.example.beeplanner.helper.Base64Custom;
import com.example.beeplanner.helper.DateCustom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Movimentacao {

    private String data, categoria, descricao, tipo, key;
    private double valor;

    public Movimentacao() {
    }

    public void salvar(String dataMovimentacao){

        FirebaseAuth firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(firebaseAuth.getCurrentUser().getEmail());
        String mesAno = DateCustom.mesAnoDataAtual(dataMovimentacao);

        DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference();
        //primeiro nó, movimentação
        databaseReference.child("movimentacao")
        //segundo nó, id usuario
                        .child(idUsuario)
        //terceiro nó, data
                        .child(mesAno)
        //gerando nó unico do firebase
                        .push()
                        .setValue(this);
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
