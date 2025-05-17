package com.example.demo.dto;

import lombok.Data;

@Data
public class TutorDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
}