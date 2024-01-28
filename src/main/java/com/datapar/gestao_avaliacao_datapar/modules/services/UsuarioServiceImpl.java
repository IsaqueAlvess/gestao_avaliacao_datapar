package com.datapar.gestao_avaliacao_datapar.modules.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datapar.gestao_avaliacao_datapar.modules.models.Usuario;
import com.datapar.gestao_avaliacao_datapar.modules.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void saveUsuario(Usuario usuario) {
        Usuario existingUser = usuarioRepository.findByEmail(usuario.getEmail());

        if (existingUser != null) {
            throw new IllegalStateException("Já existe um usuário com o e-mail: " + usuario.getEmail());
        }

        this.usuarioRepository.save(usuario);
    }

    @Override
    public Usuario getUsuarioByEmail(String email) {
       return usuarioRepository.findByEmail( email );
               
    }

}
