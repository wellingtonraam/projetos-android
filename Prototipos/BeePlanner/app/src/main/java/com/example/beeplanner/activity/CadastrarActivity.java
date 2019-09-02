package com.example.beeplanner.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beeplanner.R;
import com.example.beeplanner.config.ConfiguracaoFirebase;
import com.example.beeplanner.helper.Base64Custom;
import com.example.beeplanner.models.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastrarActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Button botaoCadastrar;
    private FirebaseAuth firebaseAutenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        campoNome = findViewById(R.id.etNomeCadastro);
        campoEmail = findViewById(R.id.etEmailCadastro);
        campoSenha = findViewById(R.id.etPasswordCadastro);
        botaoCadastrar = findViewById(R.id.btnCadastro);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ------ validação sem método ------ //
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

        });


    }

    private void cadastrarUsuario(){

        firebaseAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        firebaseAutenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setIdUsuario(idUsuario);
                    usuario.salvar();
                    finish();
                }
                else
                {
                    String excecao = "";
                    try{
                        throw task.getException();
                    }
                    catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte!";
                    }
                    catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Digite um e-mail válido!";
                    }
                    catch (FirebaseAuthUserCollisionException e){
                        excecao = "Esta conta já foi cadastrada!";
                    }
                    catch (Exception e)
                    {
                        excecao = "Erro ao cadastrar usuario: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastrarActivity.this, excecao, Toast.LENGTH_SHORT).show();

                }
            }
        });




    }

}


