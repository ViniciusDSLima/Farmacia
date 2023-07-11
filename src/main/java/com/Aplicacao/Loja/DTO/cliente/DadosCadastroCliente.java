package com.Aplicacao.Loja.DTO.cliente;

import com.Aplicacao.Loja.domain.cliente.Tipo;
import com.Aplicacao.Loja.endereco.DadosEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCliente(@NotBlank String nome, @NotBlank String email, @NotBlank String senha, @NotNull Tipo tipo,@NotNull DadosEndereco endereco) {
}
