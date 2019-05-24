package com.example.appcardview.model;

public class Postagens {

    int Thumbnail;
    String Titulo;


    public Postagens(int Thumbnail, String Titulo) {
        this.Thumbnail = Thumbnail;
        this.Titulo = Titulo;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.Thumbnail = thumbnail;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        this.Titulo = titulo;
    }
}
