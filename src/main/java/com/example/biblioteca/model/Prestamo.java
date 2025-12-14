package com.example.biblioteca.model;

import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

import java.time.LocalDate;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    private LocalDate fechaPrestamo;

    public Prestamo(){}

    public Prestamo(Libro libro, LocalDate fechaPrestamo) {
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
    }

    public Long getId() {
        return id;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
}
