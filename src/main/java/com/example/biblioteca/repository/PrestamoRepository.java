package com.example.biblioteca.repository;

import com.example.biblioteca.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findByPrestamoBetween(LocalDate min, LocalDate max);

    List<Prestamo> findByPrestamoGreaterThanEqual(Long id);

    List<Prestamo> findByPrestamoLessThanEqual(Long id);
}
