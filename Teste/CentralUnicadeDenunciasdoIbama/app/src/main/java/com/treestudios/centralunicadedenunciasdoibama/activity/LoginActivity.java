package com.treestudios.centralunicadedenunciasdoibama.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.treestudios.centralunicadedenunciasdoibama.R;
import com.treestudios.centralunicadedenunciasdoibama.config.ConfiguracaoFirebase;
import com.treestudios.centralunicadedenunciasdoibama.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etEmailLogin, etSenhaLogin;
    Button buttonEntrar;
    Usuario usuario;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonEntrar = findViewById(R.id.buttonEntrarLogin);
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = etEmailLogin.getText().toString();
                String textoSenha = etSenhaLogin.getText().toString();

                if (!textoEmail.isEmpty()) {
                    if (!textoSenha.isEmpty()) {


                        usuario = new Usuario();
                        usuario.setEmail(etEmailLogin.toString());
                        usuario.setSenha(etSenhaLogin.toString());

                    }   else {
                        Toast.makeText(LoginActivity.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Preencha o email!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void validarLogin(){


        firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        firebaseAuth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    abrirTelaInicial();

                } else
                {
                    String excecao = "";
                    try{
                        throw task.getException();
                    }
                    catch (FirebaseAuthInvalidUserException e){
                        excecao = "Usuário não cadastrado!";
                    }
                    catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Senha incorreta!";
                    }
                    catch (Exception e)
                    {
                        excecao = "Erro ao cadastrar usuario: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show();


                }
            }
        });
    }




    public void abrirCadastro(View view){
        Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(intent);

    }

    public void abrirTelaInicial(){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}