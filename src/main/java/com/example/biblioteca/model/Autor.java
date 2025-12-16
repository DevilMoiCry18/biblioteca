package com.example.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    public Autor(){}

    public Autor(String nombre) {
        this.nombre = nombre;
    }

    public long getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
