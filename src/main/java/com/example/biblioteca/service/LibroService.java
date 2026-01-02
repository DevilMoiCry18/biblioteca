package com.example.biblioteca.service;

import com.example.biblioteca.dto.LibroDTO;
import com.example.biblioteca.model.Libro;
import com.example.biblioteca.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private static LibroRepository repo;

    public LibroService(LibroRepository repo) {
        this.repo = repo;
    }

    public List<Libro> encontrarPorDisponible(boolean disponible) {
        return repo.findByDisponible(disponible);
    }

    public List<Libro> encontrarPorAutor(String nombre) {
        return repo.findByAutorNombre(nombre);
    }

    public List<Libro> encontrarPorAutorAndDisponible(String nombre, boolean disponible) {
        return repo.findByAutorNombreAndDisponible(nombre, disponible);
    }

    public Libro encontrarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    public List<Libro> encontrarTodos() {
        return repo.findAll();
    }

    public List<LibroDTO> encontrarTodosDTO() {
        return repo.findAll()
                .stream()
                .map(libro -> new LibroDTO(
                        libro.getId(),
                        libro.getTitulo(),
                        libro.getAutor().getNombre(),
                        libro.isDisponible()
                )).toList();
    }

    @Transactional
    public static void guardarEnLote(List<Libro> libros) {
        for (Libro libro : libros) {
            if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
                throw new RuntimeException("El título está vacío");
            }
            if ("ERROR".equalsIgnoreCase(libro.getTitulo())) {
                throw new RuntimeException("Título prohibido detectado");
            }
            repo.save(libro);
        }
    }
}