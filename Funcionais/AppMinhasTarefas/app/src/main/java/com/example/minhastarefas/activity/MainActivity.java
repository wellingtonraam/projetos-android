package com.example.minhastarefas.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.minhastarefas.R;
import com.example.minhastarefas.adapter.TarefaAdapter;
import com.example.minhastarefas.helper.RecyclerItemClickListener;
import com.example.minhastarefas.helper.TarefaDAO;
import com.example.minhastarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Tarefa> listaTarefas = new ArrayList<>();
    private TarefaAdapter tarefaAdapter;
    Tarefa tarefaSelecionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerTarefas);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), AdicionarTarefaActivity.class));

            }
        });

        // Implementando OnClickListener no RecyclerView

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),  recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onItemClick(View view, int position) {

                        //Recuperando tarefa selecionada

                        tarefaSelecionada = listaTarefas.get(position);

                        //Enviando tarefa para outra Activity

                        Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
                        intent.putExtra("Tarefa Selecionada", tarefaSelecionada);

                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        //Recuperando tarefa selecionada

                        tarefaSelecionada = listaTarefas.get(position);

                        //Exibindo AlertDialog
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                        alertDialog.setTitle("Confirmar exclusão");
                        alertDialog.setMessage("Deseja excluir a tarefa: " + tarefaSelecionada.getNomeTarefa() + " ?");
                        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());


                            if (tarefaDAO.deletar(tarefaSelecionada)){
                                carregarListaTarefas();
                                Toast.makeText(getApplicationContext(),"Tarefa excluida com sucesso!", Toast.LENGTH_SHORT);
                            }
                            else {

                                Toast.makeText(getApplicationContext(),"Erro ao excluir tarefa!", Toast.LENGTH_SHORT);

                            }



                            }
                        })
                        .setNegativeButton("Não", null)
                        .create()
                        .show();


                    }
                }));
    }


        //Implementando o metodo para carregar a lista

    public void carregarListaTarefas(){

        //Listar tarefas
        TarefaDAO tarefaDAO = new TarefaDAO( getApplicationContext() );
        listaTarefas = tarefaDAO.listar();

        /*
            Exibe lista de tarefas no Recyclerview
        */

        //Configurar um adapter
        tarefaAdapter = new TarefaAdapter( listaTarefas );

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter( tarefaAdapter );

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        carregarListaTarefas();
        super.onStart();
    }
}

