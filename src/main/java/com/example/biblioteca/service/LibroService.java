package com.example.biblioteca.service;

import com.example.biblioteca.model.Libro;
import com.example.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository repo;

    @Autowired
    public LibroService(final LibroRepository repo){
        this.repo= repo;
    }

    public List<Libro> encontrarTodos() {
        return repo.findAll();
    }

}
