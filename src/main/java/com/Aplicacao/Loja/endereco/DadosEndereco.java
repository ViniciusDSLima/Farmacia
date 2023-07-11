package com.Aplicacao.Loja.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosEndereco(@NotBlank String logradouro, String numero, @NotBlank String cep, @NotBlank String bairro, @NotBlank String uf, String complemento, @NotBlank String cidade) {
}
