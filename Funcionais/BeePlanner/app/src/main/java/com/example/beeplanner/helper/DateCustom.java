package com.example.beeplanner.helper;

import java.text.SimpleDateFormat;

public class DateCustom {

    public static String dataAtual(){

        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format(date);
        return dataString;
    }

    public static String mesAnoDataAtual(String data){

       String retornoData[] = data.split("/");
       //dia = retornoData[0]
       String mesAno = retornoData[1] + retornoData[2];
       return mesAno;

    }
}
