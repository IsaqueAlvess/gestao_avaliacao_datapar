package com.datapar.gestao_avaliacao_datapar.modules.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity( name = "avaliacao")
public class Avaliacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private int pontuacao;
    private String observacoes;
    private String emailUsuario;
    private String contato;

}
