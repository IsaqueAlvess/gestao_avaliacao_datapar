package com.datapar.gestao_avaliacao_datapar.modules.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datapar.gestao_avaliacao_datapar.modules.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    Usuario findByEmail(String email);
}
