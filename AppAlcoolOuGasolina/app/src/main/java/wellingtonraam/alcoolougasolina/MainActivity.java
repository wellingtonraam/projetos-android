package wellingtonraam.alcoolougasolina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etPrecoAlcool;
    private EditText etPrecoGasolina;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPrecoAlcool = findViewById(R.id.etPrecoAlcool);
        etPrecoGasolina = findViewById(R.id.etPrecoGasolina);
        tvResultado = findViewById(R.id.tvResultado);
    }



    public void calcularPreco(View view){
    String precoGasolina = etPrecoGasolina.getText().toString();
    String precoAlcool = etPrecoAlcool.getText().toString();
    Boolean camposValidados = this.validarCampos(precoAlcool, precoGasolina);
    if(camposValidados){
        calcularMelhorPreco(precoAlcool, precoGasolina);
    } else {
        tvResultado.setText("Preencher todos os campos primeiro!");
    }

    }

    public void calcularMelhorPreco(String pAlcool, String pGasolina){
        Double alcool = Double.parseDouble(pAlcool);
        Double gasolina = Double.parseDouble(pGasolina);
        if ((alcool/gasolina) >= 0.7 ){
            tvResultado.setText("Melhor utilizar Gasolina!");
        }
        else {
            tvResultado.setText("Melhor utilizar Álcool!");
        }
    }

    public Boolean validarCampos (String pAlcool, String pGasolina){
        Boolean camposValidados = true;
        if (pAlcool == null || pAlcool.equals("")){
            camposValidados = false;
        } else if (pGasolina == null || pGasolina.equals("")){
            camposValidados = false;
        }
        return camposValidados;

    }
}
