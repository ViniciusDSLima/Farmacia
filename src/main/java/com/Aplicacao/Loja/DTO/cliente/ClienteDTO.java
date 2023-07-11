package com.Aplicacao.Loja.DTO.cliente;

import com.Aplicacao.Loja.domain.cliente.Cliente;
import com.Aplicacao.Loja.domain.cliente.Tipo;
import com.Aplicacao.Loja.endereco.Endereco;

public record ClienteDTO(String nome, Tipo tipo, String email, Endereco endereco) {

    public ClienteDTO(Cliente cliente){
        this(cliente.getNome(), cliente.getTipo(), cliente.getEmail(), cliente.getEndereco());
    }
}
