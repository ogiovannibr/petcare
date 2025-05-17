package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class VacinacaoDTO {
    private Long id;
    private Long animalId;
    private String animalNome;
    private Long veterinarioId;
    private String veterinarioNome;
    private String vacina;
    private String lote;
    private LocalDate dataAplicacao;
    private LocalDate dataProximaDose;
}