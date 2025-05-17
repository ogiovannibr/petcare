package com.example.demo.dto;

import com.example.demo.model.Consulta.StatusConsulta;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ConsultaDTO {
    private Long id;
    private Long animalId;
    private String animalNome;
    private Long veterinarioId;
    private String veterinarioNome;
    private String local;
    private LocalDateTime dataHora;
    private String observacoes;
    private StatusConsulta status;
}