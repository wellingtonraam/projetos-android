package com.example.recyclerview.activity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.recyclerview.R;
import com.example.recyclerview.adapter.AdapterFilmes;
import com.example.recyclerview.model.Filme;
import com.example.recyclerview.model.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Filme> listFilmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recyclerView);

        //Configuração dos items
        this.setListFilmes();

        //Configuração do RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration( new DividerItemDecoration(this, LinearLayout.VERTICAL));

        //Configuração do Adapter
        AdapterFilmes adapterFilmes = new AdapterFilmes(listFilmes);


        //Configuração do Layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterFilmes);

        //Eventos de Click
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView,

                new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), getTitle() + " Pressionado!", Toast.LENGTH_SHORT);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));



    }


    public void setListFilmes() {
    Filme filme = new Filme("A corrida mortal", "Paramount","1000 views", R.drawable.img1);
    listFilmes.add(filme);
    filme = new Filme("O Aprendiz", "Rede Band","1000 views", R.drawable.img2);
    listFilmes.add(filme);
    filme = new Filme("O Magnata", "MTV Channel","1000 views", R.drawable.img3);
    listFilmes.add(filme);
    filme = new Filme("A última jornada", "Discovery Channel","1000 views", R.drawable.img4);
    listFilmes.add(filme);
    filme = new Filme("Bom pra cachorro!", "Hanna Barbera","1000 views", R.drawable.img5 );
    listFilmes.add(filme);
    filme = new Filme("A Lista Negra", "Paramount","1000 views", R.drawable.img6);
    listFilmes.add(filme);
    filme = new Filme("Os perigosos", "Marvel", "1000 views", R.drawable.img7);
    listFilmes.add(filme);

    };
}
