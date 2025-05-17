package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class VeterinarioDTO {
    private Long id;
    private String nome;
    private String crmv;
    private String telefone;
    private String email;
    private List<String> especializacoes;
}