package com.Aplicacao.Loja.DTO.pedidos;

import com.Aplicacao.Loja.domain.cliente.Cliente;
import com.Aplicacao.Loja.domain.produtos.Produtos;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosAtualizarPedido(@NotNull Long id, List<Cliente> clientes, List<Produtos> produtos) {
}
