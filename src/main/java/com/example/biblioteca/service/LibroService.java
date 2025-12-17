package com.example.biblioteca.service;

import com.example.biblioteca.dto.LibroDTO;
import com.example.biblioteca.model.Libro;
import com.example.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository repo;

    public LibroService(LibroRepository repo){
        this.repo= repo;
    }

    public List<Libro> encontrarPorDisponible(boolean disponible){return repo.findByDisponible(disponible);}

    public List<Libro> encontrarPorAutor(String nombre){return repo.findByAutorNombre(nombre);}

    public List<Libro> encontrarPorAutorAndDisponible(String nombre, boolean disponible){return repo.findByAutorAndDisponible(nombre,disponible);}

    public List<Libro> encontrarTodos() {
        return repo.findAll();
    }
    public List<LibroDTO> encontrarTodosDTO(){
        return repo.findAll()
                .stream()
                .map(libro -> new LibroDTO(
                        libro.getId(),
                        libro.getTitulo(),
                        libro.getAutor().getNombre(),
                        libro.isDisponible()
                ))
                .toList();
    }

    public Libro encontrarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    public Libro guardar(Libro libro) {
        if (!repo.existsByTitulo(libro.getTitulo())){
            throw new RuntimeException("Ta existe un recurso con este título");
        }
        return repo.save(libro);
    }

    public Libro actualizar(Long id, Libro libroActualizado) {
        Libro libroExistente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        libroExistente.setTitulo(libroActualizado.getTitulo());
        libroExistente.setAutor(libroActualizado.getAutor());
        libroExistente.setDisponible(libroActualizado.isDisponible());
        return repo.save(libroExistente);
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Libro no encontrado");
        }
        repo.deleteById(id);
    }

}
