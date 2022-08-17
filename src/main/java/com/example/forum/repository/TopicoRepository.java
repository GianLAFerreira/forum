package com.example.forum.repository;

import com.example.forum.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TopicoRepository extends CrudRepository<Topico, UUID> {

    Page<Topico> findAll();

    Page<Topico> findByCursoNome(String nomecurso, Pageable paginacao);

    Topico getOne(UUID id);


    Page<Topico> findAll(Pageable paginacao);
}
