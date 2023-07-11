package com.Aplicacao.Loja.DTO.authentication;

import com.Aplicacao.Loja.domain.usuario.UsuarioRole;

public record CadastroDTO(String email, String senha, UsuarioRole usuarioRole) {
}
