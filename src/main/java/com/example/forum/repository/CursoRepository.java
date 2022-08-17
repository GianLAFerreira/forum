package com.example.forum.repository;

import com.example.forum.model.Curso;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CursoRepository extends CrudRepository<Curso, UUID> {


    Curso findByNome(String nomeCurso);
}
