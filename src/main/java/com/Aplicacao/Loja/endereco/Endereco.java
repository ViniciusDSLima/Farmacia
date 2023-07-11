package com.Aplicacao.Loja.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String numero;
    private String uf;
    private String cidade;
    private String bairro;
    private String cep;
    private String complemento;

    public Endereco(DadosEndereco dadosEndereco) {
        this.logradouro = dadosEndereco.logradouro();
        this.numero = dadosEndereco.numero();
        this.uf = dadosEndereco.uf();
        this.cidade = dadosEndereco.cidade();
        this.complemento = dadosEndereco.complemento();
        this.cep = dadosEndereco.cep();
        this.bairro = dadosEndereco.bairro();

    }


    public void atualizarInformacoes(DadosAtualizarEndereco dadosAtualizarEndereco) {
        if(dadosAtualizarEndereco.logradouro() != null){
            this.logradouro = dadosAtualizarEndereco.logradouro();
        }
        if(dadosAtualizarEndereco.numero() != null){
            this.numero = dadosAtualizarEndereco.numero();
        }
        if(dadosAtualizarEndereco.uf() != null){
            this.uf = dadosAtualizarEndereco.uf();
        }
        if(dadosAtualizarEndereco.cidade() != null){
            this.cidade = dadosAtualizarEndereco.cidade();
        }
        if(dadosAtualizarEndereco.cep() != null){
            this.cep = dadosAtualizarEndereco.cep();
        }
        if(dadosAtualizarEndereco.complemento() != null){
            this.complemento = dadosAtualizarEndereco.complemento();
        }
        if(dadosAtualizarEndereco.bairro() != null){
            this.bairro = dadosAtualizarEndereco.bairro();
        }
    }
}
