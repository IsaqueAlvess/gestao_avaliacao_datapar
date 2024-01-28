package com.datapar.gestao_avaliacao_datapar.modules.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datapar.gestao_avaliacao_datapar.modules.models.Avaliacao;
import com.datapar.gestao_avaliacao_datapar.modules.repositories.AvaliacaoRepository;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService{

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Override
    public List<Avaliacao> getAllAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    @Override
    public String saveAvaliacao(Avaliacao avaliacao) {

        try {
            this.avaliacaoRepository.save(avaliacao);
            return "Enviado com sucesso!";
        } catch (Exception e) {
            return "Houve um erro com o envio.";
        }

    }
    
}
