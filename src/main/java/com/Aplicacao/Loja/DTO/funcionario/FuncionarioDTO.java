package com.Aplicacao.Loja.DTO.funcionario;

import com.Aplicacao.Loja.domain.funcionario.Cargo;
import com.Aplicacao.Loja.domain.funcionario.Funcionario;
import com.Aplicacao.Loja.endereco.Endereco;

public record FuncionarioDTO(String nome, Cargo cargo, String registro, Endereco endereco) {

    public FuncionarioDTO(Funcionario funcionario){
        this(funcionario.getNome(), funcionario.getCargo(), funcionario.getRegistro(), funcionario.getEndereco());
    }
}
