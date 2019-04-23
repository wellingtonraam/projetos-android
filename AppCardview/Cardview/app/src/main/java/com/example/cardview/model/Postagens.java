package com.example.cardview.model;

public class Postagens {


    private String titulo;
    private int thumbnail;

    public Postagens(String titulo, int thumbnail) {
        this.titulo = titulo;
        this.thumbnail = thumbnail;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
