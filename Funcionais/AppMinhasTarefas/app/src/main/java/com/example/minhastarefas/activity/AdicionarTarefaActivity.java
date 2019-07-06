package com.example.minhastarefas.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.minhastarefas.R;
import com.example.minhastarefas.helper.TarefaDAO;
import com.example.minhastarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;

    private Tarefa tarefaAtual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);

        //Caso seja edição, Recuperando a tarefa
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("Tarefa Selecionada");

        //Configurar tarefa na caixa de texto
        if ( tarefaAtual != null ){
            editTarefa.setText( tarefaAtual.getNomeTarefa() );
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() ){
            case R.id.itemSalvar :
                //Executa açao para o item salvar

                TarefaDAO tarefaDAO = new TarefaDAO( getApplicationContext() );
                String nomeTarefa = editTarefa.getText().toString();

                //Editar a tarefa
                if(tarefaAtual != null) {
                    if (!nomeTarefa.isEmpty()) {
                        Tarefa tarefa = new Tarefa();

                        tarefa.setNomeTarefa(nomeTarefa);
                        tarefa.setId(tarefaAtual.getId());
                        if (tarefaDAO.atualizar(tarefa)){
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Sucesso ao atualizar tarefa.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(),
                                "O nome da tarefa não pode ser vazio!",
                                Toast.LENGTH_SHORT).show();
                    }

                } else {
                    //Salvar a tarefa

                    if (!nomeTarefa.isEmpty()) {
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        //Tentando salvar
                        if (tarefaDAO.salvar(tarefa)) {
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Sucesso ao salvar tarefa!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Erro ao salvar tarefa!",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                }




                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
