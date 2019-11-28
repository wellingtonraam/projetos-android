package com.example.beeplanner.models;

import com.example.beeplanner.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Usuario {

    private String nome,  email,  senha, idUsuario;
    private Double receitaTotal = 0.00;
    private Double despesaTotal = 0.00;

    public Usuario() {
    }

    public void salvar(){

            DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference();
            databaseReference.child("usuarios")
                    .child(this.idUsuario)
                    .setValue(this);
        }

    public Double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(Double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public Double getDespesaTotal() {
        return despesaTotal;
    }

    public void setDespesaTotal(Double despesaTotal) {
        this.despesaTotal = despesaTotal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

}
