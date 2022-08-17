package com.example.forum.dto;

import com.example.forum.model.Topico;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.UUID;

public class TopicoDto {

    private UUID id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    public TopicoDto(Topico topico){
        this.id          = topico.getId();
        this.titulo      = topico.getTitulo();
        this.mensagem    = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    public static Page<TopicoDto> converter(Page<Topico> topicos) {
        return topicos.map(TopicoDto::new);
    }

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
