package com.treestudios.sosibama.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.treestudios.sosibama.config.ConfiguracaoFirebase;

public class Usuario {


        private String nome,  email,  senha, idUsuario;


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

    @Exclude
        public String getSenha() {
            return senha;
        }


        public void setSenha(String senha) {
            this.senha = senha;
        }

    @Exclude
        public String getIdUsuario() {
            return idUsuario;
        }


        public void setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
        }

        public void salvar(){


            DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference();
            databaseReference.child("usuarios")
                    .child(this.idUsuario)
                    .setValue(this);
        }

    }

