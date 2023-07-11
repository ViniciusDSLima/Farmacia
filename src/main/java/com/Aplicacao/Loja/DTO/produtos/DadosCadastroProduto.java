package com.Aplicacao.Loja.DTO.produtos;

import com.Aplicacao.Loja.domain.produtos.Descricao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.management.jfr.SettingDescriptorInfo;

import java.time.LocalDate;

public record DadosCadastroProduto(@NotBlank String nome, @NotBlank String estoque, @NotBlank String preco, @NotNull Descricao descricao,@NotNull LocalDate dataCadatro) {
}
