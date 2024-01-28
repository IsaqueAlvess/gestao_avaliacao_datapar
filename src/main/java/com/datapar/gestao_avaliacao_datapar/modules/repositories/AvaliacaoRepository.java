package com.datapar.gestao_avaliacao_datapar.modules.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datapar.gestao_avaliacao_datapar.modules.models.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID>{
     
}
