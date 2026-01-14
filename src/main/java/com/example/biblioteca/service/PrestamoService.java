package com.example.biblioteca.service;

import com.example.biblioteca.model.Prestamo;
import com.example.biblioteca.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    private static PrestamoRepository prestamoRepo;

    public PrestamoService(PrestamoRepository prestamoRepo) {
        this.prestamoRepo = prestamoRepo;
    }

    public List<Prestamo> encontrarPorRangoFecha(LocalDate min, LocalDate max){
        return prestamoRepo.findByPrestamoBetween(min,max);
    }

    public static List<Prestamo> encontrarPorIdMayor(Long id){
        return prestamoRepo.findByPrestamoGreaterThanEqual(id);
    }

    public List<Prestamo> encontrarPorIdMenor(Long id){
        return prestamoRepo.findByPrestamoLessThanEqual(id);
    }
}
