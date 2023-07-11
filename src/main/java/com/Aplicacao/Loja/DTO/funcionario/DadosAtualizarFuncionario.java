package com.Aplicacao.Loja.DTO.funcionario;

import com.Aplicacao.Loja.domain.funcionario.Cargo;
import com.Aplicacao.Loja.endereco.DadosAtualizarEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarFuncionario(@NotNull Long id, String registro, Cargo cargo, String senha, DadosAtualizarEndereco dadosAtualizarEndereco) {
}
