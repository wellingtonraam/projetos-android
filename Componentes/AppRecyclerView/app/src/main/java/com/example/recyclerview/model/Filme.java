package com.example.recyclerview.model;

import android.graphics.drawable.Drawable;

public class Filme {

    private String titulo;
    private String canal;
    private String visualizacoes;
    private int thumbnail;


    public Filme(String titulo, String canal, String visualizacoes, int thumbnail) {
        this.titulo = titulo;
        this.canal = canal;
        this.visualizacoes = visualizacoes;
        this.thumbnail = thumbnail;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(String visualizacoes) {
        this.visualizacoes = visualizacoes;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
