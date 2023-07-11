package com.Aplicacao.Loja.domain.cliente;

import com.Aplicacao.Loja.DTO.cliente.DadosAtualizarCliente;
import com.Aplicacao.Loja.DTO.cliente.DadosCadastroCliente;
import com.Aplicacao.Loja.domain.pedidos.Pedidos;
import com.Aplicacao.Loja.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @Embedded
    private Endereco endereco;
    private boolean ativo;
    @ManyToMany(mappedBy = "clientes")
    private List<Pedidos> pedidos;


    public Cliente(DadosCadastroCliente dadosCadastroCliente) {
        this.nome = dadosCadastroCliente.nome();
        this.email = dadosCadastroCliente.email();
        this.senha = dadosCadastroCliente.senha();
        this.endereco = new Endereco(dadosCadastroCliente.endereco());
        this.tipo = dadosCadastroCliente.tipo();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizarCliente dadosAtualizarCliente) {
        if(dadosAtualizarCliente.email() != null){
            this.email = dadosAtualizarCliente.email();
        }
        if(dadosAtualizarCliente.tipo() != null){
            this.tipo = dadosAtualizarCliente.tipo();
        }
        if(dadosAtualizarCliente.dadosAtualizarEndereco() != null){
            this.endereco.atualizarInformacoes(dadosAtualizarCliente.dadosAtualizarEndereco());
        }
    }

    public void excluir(){
        this.ativo = false;
    }
}
