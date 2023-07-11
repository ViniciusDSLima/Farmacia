package com.Aplicacao.Loja.DTO.pedidos;

import com.Aplicacao.Loja.domain.cliente.Cliente;
import com.Aplicacao.Loja.domain.produtos.Produtos;

import java.time.LocalDateTime;
import java.util.List;

public record DadosCadastroPedido(LocalDateTime dataPedido, List<Cliente> clientes, List<Produtos> produtos) {
}
