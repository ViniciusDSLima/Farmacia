package com.Aplicacao.Loja.DTO.cliente;

import com.Aplicacao.Loja.domain.cliente.Tipo;
import com.Aplicacao.Loja.endereco.DadosAtualizarEndereco;

public record DadosAtualizarCliente(Long id,String email, Tipo tipo, DadosAtualizarEndereco dadosAtualizarEndereco) {
}
