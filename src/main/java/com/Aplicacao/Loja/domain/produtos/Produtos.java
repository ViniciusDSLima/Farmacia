package com.Aplicacao.Loja.domain.produtos;

import com.Aplicacao.Loja.DTO.produtos.DadosAtualizarProdutos;
import com.Aplicacao.Loja.DTO.produtos.DadosCadastroProduto;
import com.Aplicacao.Loja.repository.ProdutosRepositoru;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String preco;
    private LocalDate dataCadastro;
    @Enumerated(EnumType.STRING)
    private Descricao descricao;
    private String estoque;
    private boolean ativo;


    public Produtos(DadosCadastroProduto dadosCadastroProduto) {
        this.nome = dadosCadastroProduto.nome();
        this.preco = dadosCadastroProduto.preco();
        this.dataCadastro = dadosCadastroProduto.dataCadatro();
        this.descricao = dadosCadastroProduto.descricao();
        this.estoque = dadosCadastroProduto.estoque();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizarProdutos dadosAtualizarProdutos) {
        if(dadosAtualizarProdutos.nome() != null){
            this.nome = dadosAtualizarProdutos.nome();
        }
        if(dadosAtualizarProdutos.preco() != null){
            this.preco = dadosAtualizarProdutos.preco();
        }
        if(dadosAtualizarProdutos.descricao() != null){
            this.descricao = dadosAtualizarProdutos.descricao();
        }
        if(dadosAtualizarProdutos.estoque() != null){
            this.estoque = dadosAtualizarProdutos.estoque();
        }
    }

    public void excluir(){
        this.ativo = false;
    }
}


