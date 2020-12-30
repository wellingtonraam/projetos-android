package com.treestudios.centralunicadedenunciasdoibama.model;

import com.treestudios.centralunicadedenunciasdoibama.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;


public class Usuario {

    private String nome, email, senha, id;

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void salvar(){


        DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference();
        databaseReference.child("usuarios")
                .child(this.idUsuario)
                .setValue(this);
    }
}
