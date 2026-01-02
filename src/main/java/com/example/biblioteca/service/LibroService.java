package com.example.biblioteca.service;

import com.example.biblioteca.dto.LibroDTO;
import com.example.biblioteca.model.Libro;
import com.example.biblioteca.model.Prestamo;
import com.example.biblioteca.repository.LibroRepository;
import com.example.biblioteca.repository.PrestamoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LibroService {
    private static LibroRepository repo;
    private static PrestamoRepository prestamoRepo;

    public LibroService(LibroRepository repo, PrestamoRepository prestamoRepo) {
        this.repo = repo;
        this.prestamoRepo = prestamoRepo;
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
            if (repo.existsByTitulo(libro.getTitulo())) {
                throw new RuntimeException("Ya existe un Libro con ese Título");
            }
            if ("ERROR".equalsIgnoreCase(libro.getTitulo())) {
                throw new RuntimeException("Título prohibido detectado");
            }
            repo.save(libro);
        }
    }

    @Transactional
    public void guardarLibrosYPrestamos(List<Libro> libros) {
        for (Libro libro : libros) {
            if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
                throw new RuntimeException("El título está vacío");
            }
            if (repo.existsByTitulo(libro.getTitulo())) {
                throw new RuntimeException("Ya existe un Libro con ese Título");
            }
            if ("ERROR".equalsIgnoreCase(libro.getTitulo())) {
                throw new RuntimeException("Título prohibido detectado");
            }
            repo.save(libro);

            Prestamo prestamo = new Prestamo(libro, LocalDate.now());
            prestamoRepo.save(prestamo);
        }
    }
}