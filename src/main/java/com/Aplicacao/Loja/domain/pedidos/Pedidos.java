package com.Aplicacao.Loja.domain.pedidos;

import com.Aplicacao.Loja.DTO.pedidos.DadosAtualizarPedido;
import com.Aplicacao.Loja.DTO.pedidos.DadosCadastroPedido;
import com.Aplicacao.Loja.domain.cliente.Cliente;
import com.Aplicacao.Loja.domain.produtos.Produtos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataPedido;
    @ManyToMany
    @JoinTable(
            name = "pedido_cliente",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id")
    )
    private List<Cliente> clientes;
    @OneToMany
    private List<Produtos> produtos;
    private boolean ativo;

    public Pedidos(DadosCadastroPedido dadosCadastroPedido) {
        this.dataPedido = dadosCadastroPedido.dataPedido();
        this.clientes = dadosCadastroPedido.clientes();
        this.produtos = dadosCadastroPedido.produtos();
        this.ativo = true;
    }

    public void atualzarInformacoes(DadosAtualizarPedido dadosAtualizarPedido) {
        if(dadosAtualizarPedido.clientes() != null){
            this.clientes = dadosAtualizarPedido.clientes();
        }
        if(dadosAtualizarPedido.produtos() != null){
            this.produtos = dadosAtualizarPedido.produtos();
        }
    }

    public void excluir(){
        this.ativo = false;
    }
}
