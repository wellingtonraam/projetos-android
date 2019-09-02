package com.example.beeplanner.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beeplanner.R;
import com.example.beeplanner.config.ConfiguracaoFirebase;
import com.example.beeplanner.helper.Base64Custom;
import com.example.beeplanner.helper.DateCustom;
import com.example.beeplanner.models.Movimentacao;
import com.example.beeplanner.models.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ReceitasActivity extends AppCompatActivity {

    private TextInputEditText dataReceita, categoriaReceita , descricaoReceita;
    private EditText valorReceita;
    private Movimentacao movimentacao;
    private DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference();
    private FirebaseAuth firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double receitaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        recuperarReceitaTotal();

        valorReceita = findViewById(R.id.etValor);
        dataReceita = findViewById(R.id.editData);
        categoriaReceita = findViewById(R.id.editCategoria);
        descricaoReceita = findViewById(R.id.editDescricao);

        dataReceita.setText(DateCustom.dataAtual());



    }


    public void salvarReceita(View view){

        if (validarReceita()){
            String data = dataReceita.getText().toString();
            Double valor = Double.parseDouble(valorReceita.getText().toString());
            movimentacao = new Movimentacao();
            movimentacao.setValor(valor);
            movimentacao.setCategoria(categoriaReceita.getText().toString());
            movimentacao.setDescricao(categoriaReceita.getText().toString());
            movimentacao.setData(data);
            movimentacao.setTipo("r");

            Double receitaAtualizada = receitaTotal + valor;
            atualizarReceita(receitaAtualizada);

            movimentacao.salvar(data);
        }

    }

    public boolean validarReceita(){
        String textoValor = valorReceita.getText().toString();
        String textoCategoria= categoriaReceita.getText().toString();
        String textoData= dataReceita.getText().toString();
        String textoDescricao= descricaoReceita.getText().toString();


        if (!textoValor.isEmpty()){

            if (!textoCategoria.isEmpty()){

                if (!textoData.isEmpty()){

                    if (!textoDescricao.isEmpty()){
                        return  true;
                    } else {

                        Toast.makeText(ReceitasActivity.this, "Preencha a descrição!", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                } else {

                    Toast.makeText(ReceitasActivity.this, "Preencha a data!", Toast.LENGTH_SHORT).show();
                    return false;
                }

            } else {

                Toast.makeText(ReceitasActivity.this, "Preencha a Categoria!", Toast.LENGTH_SHORT).show();
                return false;
            }

        } else {

            Toast.makeText(ReceitasActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }



    public void recuperarReceitaTotal(){

        String idUsuario = Base64Custom.codificarBase64(firebaseAuth.getCurrentUser().getEmail());
        DatabaseReference usuarioReference = databaseReference.child("usuarios").child(idUsuario);

        usuarioReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                receitaTotal = usuario.getReceitaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public void atualizarReceita(Double receitaAtualizada){

        String idUsuario = Base64Custom.codificarBase64(firebaseAuth.getCurrentUser().getEmail());
        DatabaseReference usuarioReference = databaseReference.child("usuarios").child(idUsuario);

        usuarioReference.child("receitaTotal").setValue(receitaAtualizada);


    }


}
