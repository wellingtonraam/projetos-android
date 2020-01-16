package com.treestudios.sosibama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.treestudios.sosibama.config.ConfiguracaoFirebase;
import com.treestudios.sosibama.helper.Base64Custom;
import com.treestudios.sosibama.model.Usuario;

public class CadastrarActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private FirebaseAuth firebaseAutenticacao;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        campoNome = findViewById(R.id.etNomeCadastro);
        campoEmail = findViewById(R.id.etEmailCadastro);
        campoSenha = findViewById(R.id.etPasswordCadastro);


    }


    private void cadastrarUsuario(){

        firebaseAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        firebaseAutenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                            usuario.setIdUsuario(idUsuario);
                            usuario.salvar();
                            abrirTelaPrincipal();

                        } else {
                            String excecao = "";
                            try {
                                throw task.getException();
                            } catch (Exception e) {
                                excecao = "Erro ao cadastrar usuario: " + e.getMessage();
                                e.printStackTrace();
                            }

                            Toast.makeText(CadastrarActivity.this, excecao, Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    public void validarCadastrar(View view){
        String textoNome = campoNome.getText().toString();
        String textoEmail = campoEmail.getText().toString();
        String textoSenha = campoSenha.getText().toString();


        if (!textoNome.isEmpty()) {
            if (!textoEmail.isEmpty()) {
                if (!textoSenha.isEmpty()) {


                    usuario = new Usuario();
                    usuario.setNome(textoNome);
                    usuario.setEmail(textoEmail);
                    usuario.setSenha(textoSenha);
                    cadastrarUsuario();




                }   else {
                    Toast.makeText(CadastrarActivity.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(CadastrarActivity.this, "Preencha o email!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(CadastrarActivity.this, "Preencha o nome!", Toast.LENGTH_SHORT).show();
        }
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }

    public void abrirTermosDeUso(View view){
        String url = "https://www.evernote.com/l/Ab7fJskT5QFNy6jDua9UcNK928Q0B7kJKmM/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}


