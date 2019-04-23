package com.example.appcardview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.appcardview.R;
import com.example.appcardview.adapter.AdapterPostagens;
import com.example.appcardview.model.Postagens;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
       private  RecyclerView recyclerView ;
       private List<Postagens>  postagensList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //configurando itens da lista
        this.setPostagensList();

        //configurando RecyclerView
        recyclerView.setHasFixedSize(true);

        //configurando adapter
        AdapterPostagens adapterPostagens = new AdapterPostagens(postagensList);

        //configurando layoutManager

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterPostagens);

    }


    public void setPostagensList() {
        Postagens postagens = new Postagens("Pelo parabrisas", R.drawable.img1);
        postagensList.add(postagens);
        postagens = new Postagens("Através do vento", R.drawable.img2);
        postagensList.add(postagens);
        postagens = new Postagens("No deserto", R.drawable.img3);
        postagensList.add(postagens);
        postagens = new Postagens("Modernismo", R.drawable.img4);
        postagensList.add(postagens);
        postagens = new Postagens("Inverno de novembro", R.drawable.img5);
        postagensList.add(postagens);

    }
}
