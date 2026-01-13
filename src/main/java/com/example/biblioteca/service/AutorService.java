package com.example.biblioteca.service;

import com.example.biblioteca.model.Autor;
import com.example.biblioteca.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    private AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public List<Autor> encontrarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    public Autor encontrarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
    }

    public List<Autor> encontrarTodos() {
        return repository.findAll();
    }

    @Transactional
    public static void guardarEnLote(List<Autor> autores) {
        for (Autor autor : autores) {
            if (autor.getNombre() == null || autor.getNombre().isBlank()) {
                throw new RuntimeException("Nombre no añadido");
            }
            if ("ERROR".equalsIgnoreCase(autor.getNombre())) {
                throw new RuntimeException("Nombre inválido detectado");
            }
        }
    }
}
