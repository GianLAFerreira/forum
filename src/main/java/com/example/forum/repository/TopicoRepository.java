package com.example.forum.repository;

import com.example.forum.model.Topico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TopicoRepository extends CrudRepository<Topico, UUID> {

    List<Topico> findAll();

    List<Topico> findByCursoNome(String nomecurso);

    Topico getOne(UUID id);
}
