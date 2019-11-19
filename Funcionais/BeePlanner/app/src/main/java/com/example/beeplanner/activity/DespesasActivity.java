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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText dataDespesa, categoriaDespesa , descricaoDespesa;
    private EditText valorDespesa;
    private Movimentacao movimentacao;
    private DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference();
    private FirebaseAuth firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double despesaTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        recuperarDespesaTotal();

        valorDespesa = findViewById(R.id.etValor);
        dataDespesa = findViewById(R.id.editData);
        categoriaDespesa = findViewById(R.id.editCategoria);
        descricaoDespesa = findViewById(R.id.editDescricao);

        dataDespesa.setText(DateCustom.dataAtual());

    }

    public void salvarDespesa(View view){

        if (validarDespesa()){
        String data = dataDespesa.getText().toString();
        Double valor = Double.parseDouble(valorDespesa.getText().toString());
        movimentacao = new Movimentacao();
        movimentacao.setValor(valor);
        movimentacao.setCategoria(categoriaDespesa.getText().toString());
        movimentacao.setDescricao(categoriaDespesa.getText().toString());
        movimentacao.setData(data);
        movimentacao.setTipo("d");

        Double despesaAtualizada = despesaTotal + valor;
        atualizarDespesa(despesaAtualizada);

        movimentacao.salvar(data);
        finish();
        }

    }

    public boolean validarDespesa(){
        String textoValor = valorDespesa.getText().toString();
        String textoCategoria= categoriaDespesa.getText().toString();
        String textoData= dataDespesa.getText().toString();
        String textoDescricao= descricaoDespesa.getText().toString();


        if (!textoValor.isEmpty()){

            if (!textoCategoria.isEmpty()){

                if (!textoData.isEmpty()){

                    if (!textoDescricao.isEmpty()){
                        return  true;
                    } else {

                        Toast.makeText(DespesasActivity.this, "Preencha a descrição!", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                } else {

                    Toast.makeText(DespesasActivity.this, "Preencha a data!", Toast.LENGTH_SHORT).show();
                    return false;
                }

            } else {

                Toast.makeText(DespesasActivity.this, "Preencha a Categoria!", Toast.LENGTH_SHORT).show();
                return false;
            }

        } else {

            Toast.makeText(DespesasActivity.this, "Preencha o valor!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void recuperarDespesaTotal(){

        String idUsuario = Base64Custom.codificarBase64(firebaseAuth.getCurrentUser().getEmail());
        DatabaseReference usuarioReference = databaseReference.child("usuarios").child(idUsuario);

        usuarioReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    public void atualizarDespesa(Double despesaAtualizada){

        String idUsuario = Base64Custom.codificarBase64(firebaseAuth.getCurrentUser().getEmail());
        DatabaseReference usuarioReference = databaseReference.child("usuarios").child(idUsuario);

        usuarioReference.child("despesaTotal").setValue(despesaAtualizada);


    }
}
