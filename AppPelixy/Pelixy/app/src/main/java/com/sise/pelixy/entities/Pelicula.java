package com.sise.pelixy.entities;

public class Pelicula {
    private int id;
    private String titulo;
    private String descripcion;
    private String genero;

    public Pelicula(int id, String titulo, String descripcion, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGenero() {
        return genero;
    }
}
