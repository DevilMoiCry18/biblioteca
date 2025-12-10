package com.example.biblioteca.model;

import com.example.biblioteca.service.LibroService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String isbn;
    private Date fechaPublicacion;
    private String genero;

    public Libro(long id, String titulo, String isbn, Date fechaPublicacion, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.fechaPublicacion = fechaPublicacion;
        this.genero = genero;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getGenero() {
        return genero;
    }
}