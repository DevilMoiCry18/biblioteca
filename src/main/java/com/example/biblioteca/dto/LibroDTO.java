package com.example.biblioteca.dto;

public class LibroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private boolean disponible;

    public LibroDTO(Long id, String titulo, String autor, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponible() {
        return disponible;
    }
}
