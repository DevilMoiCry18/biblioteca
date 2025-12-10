package com.example.biblioteca.controller;

import com.example.biblioteca.model.Libro;
import com.example.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bibliotecadb")
public class LibroController {

    private LibroService libroService;

    @Autowired
    public void LibroService(LibroService libroService){
        this.libroService=libroService;
    }

    @GetMapping("/libros")
    public List<Libro> obtenerTodos() {
        return libroService.findAll();
    }

}
