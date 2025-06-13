package com.sise.pelixy.entities;

public class Comentario {
    private int id;
    private String autor;
    private String contenido;
    private int pelicula_id;

    public Comentario() {
    }

    public Comentario(int id, String autor, String contenido, int pelicula_id) {
        this.id = id;
        this.autor = autor;
        this.contenido = contenido;
        this.pelicula_id = pelicula_id;
    }

    public int getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getContenido() {
        return contenido;
    }

    public int getPelicula_id() {
        return pelicula_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setPelicula_id(int pelicula_id) {
        this.pelicula_id = pelicula_id;
    }
}
