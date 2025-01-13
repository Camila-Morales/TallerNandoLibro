package com.taller.demo.controllers;

import com.taller.demo.entities.Libro;
import com.taller.demo.services.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    @Autowired
    private LibroService service;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Libro libro, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores= new HashMap<>();
            result.getFieldErrors().forEach(error -> errores.put(
                    error.getField(), error.getDefaultMessage()
            ));
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(libro));
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Optional<Libro> libroOptional = service.findById(id);
        if (libroOptional.isPresent()) {
            return ResponseEntity.ok(libroOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Libro libro, @PathVariable Integer id) {
        Optional<Libro> libroOptional = service.findById(id);

        if (libroOptional.isPresent()) {
            Libro libroDB = libroOptional.get();
            libroDB.setTitulo(libro.getTitulo());
            libroDB.setAutor(libro.getAutor());
            libroDB.setNumPaginas(libro.getNumPaginas());
            libroDB.setFechaPublicacion(libro.getFechaPublicacion());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(libroDB));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        Optional<Libro> libroOptional = service.findById(id);

        if (libroOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}




