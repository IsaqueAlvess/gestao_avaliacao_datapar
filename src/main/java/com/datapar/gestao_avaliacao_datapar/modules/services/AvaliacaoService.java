package com.datapar.gestao_avaliacao_datapar.modules.services;

import java.util.List;

import com.datapar.gestao_avaliacao_datapar.modules.models.Avaliacao;

public interface AvaliacaoService {
    List<Avaliacao> getAllAvaliacoes();
    String saveAvaliacao(Avaliacao avaliacao);
}
