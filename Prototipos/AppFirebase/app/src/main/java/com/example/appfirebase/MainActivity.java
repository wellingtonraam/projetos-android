package com.example.appfirebase;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Usuario usuario;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth autenticacao = FirebaseAuth.getInstance();
    private ImageView imageExemplo;
    private Button buttonUpload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageExemplo = findViewById(R.id.imageExemplo);
        buttonUpload = findViewById(R.id.buttonUpload);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //passando imagem pro cache
                imageExemplo.setDrawingCacheEnabled(true);
                imageExemplo.buildDrawingCache();

                //passando imagem para bitmap
                Bitmap bitmap = imageExemplo.getDrawingCache();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                //convertendo e passando para jpg
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, stream );
                byte[] dadosImagem = stream.toByteArray();

                //configurando nós do Storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagensReference = storageReference.child("imagens");
                //Criando a id unico para imagem
                String nomeArquivo = UUID.randomUUID().toString();

                StorageReference imageReference = imagensReference.child( nomeArquivo + ".jpg");

                //passando dados da imagem para a referencia
                imageReference.putBytes(dadosImagem);

                //fazendo upload
                UploadTask uploadTask = imagensReference.putBytes(dadosImagem);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    }
                });


                imageReference.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

            }
        });




        //Cadastrando e Autenticando usuario

        autenticacao.createUserWithEmailAndPassword("email@gmail.com", "senha123").addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Log.i("CreateUser", "Sucesso ao cadastrar!");
                }
                else
                {
                    Log.i("CreateUser", "Erro ao cadastrar!");

                }
            }
        });

       //Testando se usuário está logado (lembrando que após o cadastro o login é automático)

        if (autenticacao.getCurrentUser() != null ) {
            Log.i("CreateUser", "Usuario logado");

        }
        else {

            Log.i("CreateUser", "Usuario não logado");
        }

        //Logando usuario

        autenticacao.signInWithEmailAndPassword("email@.com", "senha123").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

    }
});

  /*
        autenticacao.signOut();
        final DatabaseReference usuarios = reference.child("Usuários");
        //Criando uma referencia para o usuario 001
        final DatabaseReference usuarioPesquisa = usuarios.child("001");

        usuarios.push().setValue(usuario);

        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario dadosUsuario = dataSnapshot.getValue(Usuario.class);
           // Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(3);

              //Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(40);

                //Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(40);

                //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(40).endAt(80);

                Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("J").endAt("J");
                Log.i("Dados do Usuario", dadosUsuario.getNome());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */
    }


}
