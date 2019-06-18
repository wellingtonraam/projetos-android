package com.example.appsmarttab.activities;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appsmarttab.R;
import com.example.appsmarttab.fragments.EmAltaFragment;
import com.example.appsmarttab.fragments.HomeFragment;
import com.example.appsmarttab.fragments.InscricoesFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SmartTabLayout smartTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        smartTabLayout = findViewById(R.id.viewPagerTab);

        //Configurando a ActionBar
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Youtube");


        //Configurando os fragments
        FragmentPagerAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                        .add("Home", HomeFragment.class)
                        .add("Inscrições", InscricoesFragment.class)
                        .add("Em Alta", EmAltaFragment.class)
                        .create()
                        );

        //Configurando o ViewPager para receber os fragments
            viewPager.setAdapter(adapter);
            smartTabLayout.setViewPager(viewPager);

    }
}
