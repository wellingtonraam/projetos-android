package com.treestudios.sosibama.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static FirebaseAuth firebaseAutenticacao;
    private static DatabaseReference databaseReference;

    public static FirebaseAuth getFirebaseAutenticacao(){




        if (firebaseAutenticacao == null){
            firebaseAutenticacao = FirebaseAuth.getInstance();
        }
        return  firebaseAutenticacao;
    }

    public static DatabaseReference getDatabaseReference() {

        if (databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return databaseReference;


            }
    }
