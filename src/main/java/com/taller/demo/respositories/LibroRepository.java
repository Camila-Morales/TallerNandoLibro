package com.taller.demo.respositories;

import com.taller.demo.entities.Libro;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface LibroRepository extends CrudRepository<Libro, Integer> {



}
