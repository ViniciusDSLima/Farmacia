package com.Aplicacao.Loja.DTO.produtos;

import com.Aplicacao.Loja.domain.produtos.Descricao;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarProdutos(@NotNull Long id,String nome,String preco, String estoque, Descricao descricao) {
}
