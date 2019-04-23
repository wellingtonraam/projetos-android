package com.example.appcardview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.appcardview.R;
import com.example.appcardview.adapter.AdapterPostagens;
import com.example.appcardview.model.Postagens;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List <Postagens> postagensList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);


        //configurando lista
        setPostagensList();

        //configurando o recyclerview
        recyclerView.setHasFixedSize(true);

        //configurando o adapter
        AdapterPostagens adapterPostagens = new AdapterPostagens(postagensList);


        //configuração do Layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterPostagens);

    }

    public void setPostagensList() {

        Postagens postagens = new Postagens(R.drawable.img1, "Pelo parabrisas" );
        postagensList.add(postagens);
        postagens = new Postagens(R.drawable.img2,"Através do vento" );
        postagensList.add(postagens);
        postagens = new Postagens(R.drawable.img3,"No deserto" );
        postagensList.add(postagens);
        postagens = new Postagens(R.drawable.img4,"Modernismo" );
        postagensList.add(postagens);
        postagens = new Postagens(R.drawable.img5,"Inverno de novembro" );
        postagensList.add(postagens);



    }
}
