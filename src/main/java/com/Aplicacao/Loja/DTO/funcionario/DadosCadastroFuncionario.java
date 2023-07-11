package com.Aplicacao.Loja.DTO.funcionario;

import com.Aplicacao.Loja.domain.funcionario.Cargo;
import com.Aplicacao.Loja.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroFuncionario(@NotBlank String nome, @NotBlank String senha, @NotNull Cargo cargo, @NotBlank String registro, @NotNull @Valid DadosEndereco endereco) {
}
