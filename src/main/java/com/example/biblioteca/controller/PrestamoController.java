package com.example.biblioteca.controller;

import com.example.biblioteca.model.Prestamo;
import com.example.biblioteca.service.PrestamoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prestamo")
public class PrestamoController {
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping("/buscar-por-mayor/{id}")
    public List<Prestamo> encontrarPorIdMayorIgual(@RequestParam Long id){
        return PrestamoService.encontrarPorIdMayor(id);
    }

    @GetMapping("/buscar-por-menor/{id}")
    public List<Prestamo> findByIdLessEqual(@RequestParam Long id){
        return prestamoService.encontrarPorIdMenor(id);
    }

    @GetMapping("/buscar-por-rango")
    List<Prestamo> findByFechaBetween(@RequestParam LocalDate min, LocalDate max){
        return prestamoService.encontrarPorRangoFecha(min,max);
    }
}
