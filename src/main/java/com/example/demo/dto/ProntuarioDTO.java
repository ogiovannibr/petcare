package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProntuarioDTO {
    private Long id;
    private Long animalId;
    private String animalNome;
    private String historico;
    private LocalDateTime ultimaAtualizacao;
}