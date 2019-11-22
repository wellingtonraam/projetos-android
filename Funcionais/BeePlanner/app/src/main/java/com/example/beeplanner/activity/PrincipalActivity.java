package com.example.beeplanner.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beeplanner.R;
import com.example.beeplanner.adapter.AdapterMovimentacao;
import com.example.beeplanner.config.ConfiguracaoFirebase;
import com.example.beeplanner.helper.Base64Custom;
import com.example.beeplanner.models.Movimentacao;
import com.example.beeplanner.models.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

private FirebaseAuth autenticacao;
private DatabaseReference databaseReference;
private Double despesaTotal = 0.0;
private Double receitaTotal = 0.0;
private Double resumoUsuario = 0.0;
private MaterialCalendarView calendarView;
private DatabaseReference usuarioReference;
private ValueEventListener valueEventListenerUsuario;
private ValueEventListener valueEventListenerMovimentacao;
private TextView textSaudacao, textSaldo;
private DecimalFormat decimalFormat;
private RecyclerView recyclerView;
private AdapterMovimentacao adapterMovimentacao;
private List<Movimentacao> movimentacoes = new ArrayList<>();
private Movimentacao movimentacao;
private DatabaseReference movimentacaoReference;
private String mesAnoSelecionado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        calendarView = findViewById(R.id.calendarView);
        movimentacaoReference = ConfiguracaoFirebase.getDatabaseReference();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        databaseReference = ConfiguracaoFirebase.getDatabaseReference();
        textSaldo = findViewById(R.id.tvSaldo);
        textSaudacao = findViewById(R.id.tvSaudacao);
        decimalFormat = new DecimalFormat("0.##");
        recyclerView = findViewById(R.id.recyclerViewPrincipal);
        adapterMovimentacao = new AdapterMovimentacao(movimentacoes, getApplicationContext());
        configurarCalendario();

        //Config recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterMovimentacao);


/*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();/
                autenticacao.signOut();
                finish();

                     }
        });
    */
        recuperarResumo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarResumo();
        recuperarMovimentacoes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sairMenu){
            autenticacao.signOut();

        }
        return super.onOptionsItemSelected(item);
    }

    public void adicionarReceita(View view){
        startActivity(new Intent( this, ReceitasActivity.class ));
    }

    public void adicionarDespesa(View view){
        startActivity(new Intent(this, DespesasActivity.class));

    }

    public void logout(View view){

        autenticacao.signOut();
    }


    public void recuperarResumo(){
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
       usuarioReference = databaseReference.child("usuarios").child(idUsuario);
       valueEventListenerUsuario =  usuarioReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
               /** String resultadoFormatado;
                despesaTotal = usuario.getDespesaTotal();
                receitaTotal = usuario.getReceitaTotal();
                resumoUsuario = (receitaTotal - despesaTotal) ;
                resultadoFormatado = decimalFormat.format(resumoUsuario);
                textSaldo.setText(resultadoFormatado);
                textSaudacao.setText("Olá, " + usuario.getNome());
                **/



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    public void configurarCalendario(){

        CharSequence meses[] = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
                "Agosto", "Setembro", "Outubro", "Novembro",  "Dezembro"};
        calendarView.setTitleMonths(meses);

        CalendarDay dataAtual = calendarView.getCurrentDate();
        mesAnoSelecionado = (dataAtual.getMonth()  + 1)+ "" + dataAtual.getYear();

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
            mesAnoSelecionado = (date.getMonth() + 1)  + "" + date.getYear();

            movimentacaoReference.removeEventListener(valueEventListenerMovimentacao);
            recuperarMovimentacoes();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        usuarioReference.removeEventListener(valueEventListenerUsuario);
        movimentacaoReference.removeEventListener(valueEventListenerMovimentacao);
    }


    public void recuperarMovimentacoes(){
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        movimentacaoReference.child("movimentacao").child(idUsuario).child(mesAnoSelecionado);
        valueEventListenerMovimentacao = movimentacaoReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            movimentacoes.clear();
            for (DataSnapshot dados: dataSnapshot.getChildren()){
                Movimentacao movimentacao = dados.getValue(Movimentacao.class);
                movimentacao.setKey(dados.getKey());
                movimentacoes.add(movimentacao);
            }
            adapterMovimentacao.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void swipe(){
        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

                return makeMovementFlags(ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.START | ItemTouchHelper.END);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                excluirMovimentacao(viewHolder);
            }
        };
        new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerView);
    }


    public void excluirMovimentacao(final RecyclerView.ViewHolder viewHolder){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());
        alertDialog.setTitle("Excluir movimentação da Conta");
        alertDialog.setMessage("Você tem certeza que deseja excluir essa movimentação da sua conta?");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int position = viewHolder.getAdapterPosition();
                movimentacao = movimentacoes.get(position);


                String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
                movimentacaoReference.child("movimentacao").child(idUsuario).child(mesAnoSelecionado);

                movimentacaoReference.child(movimentacao.getKey()).removeValue();
                adapterMovimentacao.notifyItemRemoved(position);
                atualizarSaldo();

            }
        });
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PrincipalActivity.this, "Cancelado", Toast.LENGTH_SHORT).show();
                adapterMovimentacao.notifyDataSetChanged();
            }
        });
    }


    public void atualizarSaldo(){

        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        usuarioReference = databaseReference.child("usuarios").child(idUsuario);

        if (movimentacao.getTipo().equals("r")){
            receitaTotal =- movimentacao.getValor();
            usuarioReference.child("receitaTotal").setValue(receitaTotal);
        }
        if(movimentacao.getTipo().equals("d")){
            despesaTotal =- movimentacao.getValor();
            usuarioReference.child("despesaTotal").setValue(despesaTotal);

        }

    }
}
