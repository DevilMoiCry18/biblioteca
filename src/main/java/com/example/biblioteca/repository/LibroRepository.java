package com.example.biblioteca.repository;

import com.example.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByTitulo(String titulo);

    boolean existsByTitulo(String titulo);

    List<Libro> findByDisponible(boolean disponible);

    List<Libro> findByAutorNombre(String nombre);

    List<Libro> findByAutorNombreAndDisponible(String nombre, boolean disponible);
}
