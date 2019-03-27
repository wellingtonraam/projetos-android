package com.example.pedrapapeltesoura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvScore;
    TextView tvMain;
    int scoreUser;
    int scoreComputer;
    String choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore = findViewById(R.id.tvScore);
        tvMain =  findViewById(R.id.tvMain);
    }

    public void rock(View view){
        choice = "pedra";
        selectedOption(choice);
    }

    public void paper(View view){
        choice = "papel";
        selectedOption(choice);
    }

    public void scissor(View view){
        choice = "tesoura";
        selectedOption(choice);
    }

   public void selectedOption (String choice){
        String[] options = {"pedra", "papel", "tesoura"};
        int computerChoice = new Random().nextInt(3);


        switch (computerChoice){
           case 0:

               if (choice.equals("pedra")){
               tvMain.setText("Pedra empata com Pedra. \nNinguém ganhou!");
               }
               else if (choice.equals("papel")) {
               tvMain.setText("Papel ganha de Pedra. \nVocê ganhou!");
               scoreUser += 1;
               }
               else {
                   tvMain.setText("Tesoura perde para Pedra. \nVocê perdeu!");
               scoreComputer += 1;
               }
               break;
           case 1:
               if (choice.equals("pedra")){
                   tvMain.setText("Pedra perde para Papel. \nVocê perdeu!");
               scoreComputer += 1;
               }
               else if (choice.equals("papel")) {
                   tvMain.setText("Papel empata com Papel. \nNinguém ganhou!");
               }
               else {
                   tvMain.setText("Tesoura ganha de Papel. \nVocê ganhou!");
               scoreUser += 1;
               }
               break;
           case 2:
               if (choice.equals("pedra")){
                   tvMain.setText("Pedra ganha de Tesoura. \nVocê ganhou!");
                   scoreUser += 1;

               }
               else if (choice.equals("papel")) {
                   tvMain.setText("Pedra perde para Papel. \nVocê perdeu!");
                   scoreComputer += 1;
               }
               else {
                   tvMain.setText("Tesoura empata com Tesoura. \nNinguém ganhou!");

               }
               break;
       }

       tvScore.setText(scoreUser + ":" + scoreComputer);

   }
}
