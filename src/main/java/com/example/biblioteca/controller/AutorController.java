package com.example.biblioteca.controller;

import com.example.biblioteca.model.Autor;
import com.example.biblioteca.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autor")
public class AutorController {
    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Autor> obtenerAutor(@RequestParam(required = false) String nombre) {
        if (nombre != null) {
            return service.encontrarPorNombre(nombre);
        }
        return service.encontrarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerAutorPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.encontrarPorId(id));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/lote")
    public ResponseEntity<Void> postEnLote(@RequestBody List<Autor> autores) {
        service.guardarEnLote(autores);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
