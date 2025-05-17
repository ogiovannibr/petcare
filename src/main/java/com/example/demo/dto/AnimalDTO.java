package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AnimalDTO {
    private Long id;
    private String nome;
    private String especie;
    private String raca;
    private LocalDate dataNascimento;
    private Long tutorId;
    private String tutorNome;
}