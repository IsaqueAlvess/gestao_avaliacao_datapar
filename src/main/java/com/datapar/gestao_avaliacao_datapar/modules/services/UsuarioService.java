package com.datapar.gestao_avaliacao_datapar.modules.services;

import com.datapar.gestao_avaliacao_datapar.modules.models.Usuario;

public interface UsuarioService {
    void saveUsuario(Usuario usuario);
    Usuario getUsuarioByEmail(String email);        
}
