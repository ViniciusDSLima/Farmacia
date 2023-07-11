package com.Aplicacao.Loja.domain.funcionario;

import com.Aplicacao.Loja.DTO.funcionario.DadosAtualizarFuncionario;
import com.Aplicacao.Loja.DTO.funcionario.DadosCadastroFuncionario;
import com.Aplicacao.Loja.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    private String senha;
    private String registro;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Funcionario(DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
        this.cargo = dados.cargo();
        this.senha = dados.senha();
        this.registro= dados.registro();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizarFuncionario dados) {
        if(dados.cargo() != null){
            this.cargo = dados.cargo();
        }
        if(dados.senha() != null){
            this.senha = dados.senha();
        }
        if(dados.registro() != null){
            this.registro = dados.registro();
        }
        if(dados.dadosAtualizarEndereco() != null){
            this.endereco.atualizarInformacoes(dados.dadosAtualizarEndereco());
        }

    }

    public void excluir(){
        this.ativo = false;
    }
}
