package com.treestudios.sosibama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.treestudios.sosibama.helper.DataUtil;
import com.treestudios.sosibama.model.Denuncia;

public class DenunciaActivity extends AppCompatActivity {

    private EditText dataDenuncia, tipoDenuncia, descricaoDenuncia, localDenuncia;
    Denuncia denuncia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        dataDenuncia = findViewById(R.id.editData);
        tipoDenuncia = findViewById(R.id.editCategoria);
        descricaoDenuncia = findViewById(R.id.editDescricao);
        localDenuncia = findViewById(R.id.editLocalizacao);

        dataDenuncia.setText(DataUtil.dataAtual());
    }

    public void salvarDenuncia (View view){

        if(denunciaValidacao()) {
            String data = dataDenuncia.getText().toString();
            denuncia = new Denuncia();
            denuncia.setData(dataDenuncia.getText().toString());
            denuncia.setTipo(tipoDenuncia.getText().toString());
            denuncia.setDescricao(descricaoDenuncia.getText().toString());
            denuncia.setLocal(localDenuncia.getText().toString());
            denuncia.salvar(data);
            finish();
        }

    }

    public boolean denunciaValidacao(){

        String textoTipo = tipoDenuncia.getText().toString();
        String textoData= dataDenuncia.getText().toString();
        String textoDescricao= tipoDenuncia.getText().toString();
        String textoLocal = localDenuncia.getText().toString();

        if (!textoLocal.isEmpty()){

        if (!textoTipo.isEmpty()){


            if (!textoData.isEmpty()){


                if (!textoDescricao.isEmpty()){
                    return true;

                } else {


                    Toast.makeText(DenunciaActivity.this, "Preencha a descrição!", Toast.LENGTH_SHORT).show();
                    return false;
                }


            } else {


                Toast.makeText(DenunciaActivity.this, "Preencha a data!", Toast.LENGTH_SHORT).show();
                return false;
            }


        } else {


            Toast.makeText(DenunciaActivity.this, "Preencha o tipo!", Toast.LENGTH_SHORT).show();
            return false;
        }


    } else {


        Toast.makeText(DenunciaActivity.this, "Preencha o local!", Toast.LENGTH_SHORT).show();
        return false;

    }
    }
}
