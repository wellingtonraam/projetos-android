package com.treestudios.sosibama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.treestudios.sosibama.adapter.AdapterDenuncia;
import com.treestudios.sosibama.config.ConfiguracaoFirebase;
import com.treestudios.sosibama.model.Denuncia;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAutenticacao;
    private DatabaseReference databaseReference, denunciasRef;
    private ValueEventListener valueEventListenerDenuncia;
    private MaterialCalendarView calendarView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterDenuncia adapterDenuncia;
    private List<Denuncia> denuncias = new ArrayList<>();
    private String mesAnoSelecionado ="", mesSelecionado ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        calendarView = findViewById(R.id.calendarView);
        databaseReference = ConfiguracaoFirebase.getDatabaseReference();
        firebaseAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("");
        recyclerView = findViewById(R.id.recyclerDenuncias);
        layoutManager = new LinearLayoutManager(this);
        adapterDenuncia = new AdapterDenuncia(denuncias, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterDenuncia);
        configurarCalendario();

    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarDenuncias();
    }

    public void adicionarDespesa(View view){
        startActivity(new Intent(this, DenunciaActivity.class));

    }


    public void configurarCalendario(){
        CharSequence meses[] = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
                "Agosto", "Setembro", "Outubro", "Novembro",  "Dezembro"};
        calendarView.setTitleMonths(meses);
        CalendarDay dataAtual = calendarView.getCurrentDate();
        mesSelecionado = String.format("%02d", (dataAtual.getMonth()));
        mesAnoSelecionado = String.valueOf(mesSelecionado + "" + dataAtual.getYear());

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

               mesSelecionado = String.format("%02d", (date.getMonth()));
                mesAnoSelecionado = String.valueOf( mesSelecionado + "" + date.getYear());
                denunciasRef.removeEventListener(valueEventListenerDenuncia);
                recuperarDenuncias();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSair :

                firebaseAutenticacao.signOut();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void recuperarDenuncias(){
        denunciasRef = databaseReference.child("denuncia").child(mesAnoSelecionado);

        valueEventListenerDenuncia = denunciasRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              denuncias.clear();
              for (DataSnapshot dados : dataSnapshot.getChildren()){
                  Denuncia  denuncia = dados.getValue(Denuncia.class);
                  denuncias.add(denuncia);
              }
              adapterDenuncia.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }
}
