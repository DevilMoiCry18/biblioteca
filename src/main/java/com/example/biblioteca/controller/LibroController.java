package com.example.biblioteca.controller;

import com.example.biblioteca.dto.LibroDTO;
import com.example.biblioteca.model.Libro;
import com.example.biblioteca.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> obtenerLibros(@RequestParam(required = false) String autor, @RequestParam(required = false) Boolean disponible) {
        if (autor != null && disponible != null) {
            return libroService.encontrarPorAutorAndDisponible(autor, disponible);
        } else if (autor != null) {
            return libroService.encontrarPorAutor(autor);
        } else if (disponible != null) {
            return libroService.encontrarPorDisponible(disponible);
        }
        return libroService.encontrarTodos();
    }

    @GetMapping("/dto")
    public ResponseEntity<List<LibroDTO>> obtenerLibrosDTO(
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) Boolean disponible) {
        return ResponseEntity.ok(libroService.encontrarTodosDTO());
    }

    @PostMapping("/lote")
    public ResponseEntity<String> crearLibrosEnLote(@RequestBody List<Libro> libros) {
        try {
            libroService.guardarEnLote(libros);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/lote-dto")
    public ResponseEntity<Void> crearLibrosEnLoteDTO(@RequestBody List<LibroDTO> librosDTO) {
        libroService.guardarEnLoteDTO(librosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/lote-prestamos")
    public ResponseEntity<String> crearLibrosYPrestamos(@RequestBody List<Libro> libros) {
        try {
            libroService.guardarLibrosYPrestamos(libros);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(libroService.encontrarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}