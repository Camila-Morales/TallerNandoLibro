package com.taller.demo.services;

import com.taller.demo.entities.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    List<Libro> findAll();
    Optional<Libro> findById(Integer id);
    Libro save(Libro asignatura);
    void deleteById(Integer id);
}
