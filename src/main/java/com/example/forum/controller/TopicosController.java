package com.example.forum.controller;

import com.example.forum.Form.AtualizacaoTopicoForm;
import com.example.forum.Form.TopicoForm;
import com.example.forum.dto.DetalheTopicoDto;
import com.example.forum.dto.TopicoDto;
import com.example.forum.model.Topico;
import com.example.forum.repository.CursoRepository;
import com.example.forum.repository.TopicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Inject
    TopicoRepository topicoRepository;
    @Inject
    CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){
        if (nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        }else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);
        }

    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder builder){

        Topico  topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri =  builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheTopicoDto> detalhar(@PathVariable UUID id){

        Optional<Topico> topico = topicoRepository.findById(id);

        return topico.map(value -> ResponseEntity.ok(new DetalheTopicoDto(value))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<TopicoDto> atualizar(@PathVariable UUID id, @RequestBody @Valid AtualizacaoTopicoForm form){

        Optional<Topico> optional = topicoRepository.findById(id);

        if (optional.isPresent()){
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<TopicoDto> remover(@PathVariable UUID id){
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
