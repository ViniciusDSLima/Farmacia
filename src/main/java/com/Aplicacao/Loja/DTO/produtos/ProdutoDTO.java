package com.Aplicacao.Loja.DTO.produtos;

import com.Aplicacao.Loja.domain.produtos.Descricao;
import com.Aplicacao.Loja.domain.produtos.Produtos;

public record ProdutoDTO(String nome, Descricao descricao, String estoque, String preco) {
    public ProdutoDTO(Produtos produtos) {
        this(produtos.getNome(), produtos.getDescricao(), produtos.getEstoque(), produtos.getPreco());
    }
}
