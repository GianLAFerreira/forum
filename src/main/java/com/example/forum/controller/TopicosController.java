package com.example.forum.controller;

import com.example.forum.dto.TopicoDto;
import com.example.forum.model.Curso;
import com.example.forum.model.Topico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicosController {

    @RequestMapping("/topicos")
    public List<TopicoDto> lista(){
        Topico topico = new Topico("Dúvidas", "Dúvida com spring", new Curso("Prog", "Programação"));

        return TopicoDto.converter(List.of(topico, topico, topico,topico, topico));
    }
}
