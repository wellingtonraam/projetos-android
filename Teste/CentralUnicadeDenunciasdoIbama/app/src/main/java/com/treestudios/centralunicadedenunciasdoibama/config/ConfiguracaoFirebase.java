package com.treestudios.centralunicadedenunciasdoibama.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {

    private static FirebaseAuth firebaseAutenticacao;


    public static FirebaseAuth getFirebaseAutenticacao() {


        if (firebaseAutenticacao == null) {
            firebaseAutenticacao = FirebaseAuth.getInstance();
        }
        return firebaseAutenticacao;
    }

}