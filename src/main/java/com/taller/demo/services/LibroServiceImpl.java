package com.taller.demo.services;

import com.taller.demo.entities.Libro;
import com.taller.demo.respositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService{
    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Libro> findAll() {
        return (List<Libro>) libroRepository.findAll();

    }

    @Override
    public Optional<Libro> findById(Integer id) {
        return libroRepository.findById(id);
    }

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);

    }

    @Override
    public void deleteById(Integer id) {
        libroRepository.deleteById(id);
    }
}
