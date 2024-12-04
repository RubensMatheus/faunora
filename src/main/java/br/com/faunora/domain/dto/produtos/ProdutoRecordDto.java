package br.com.faunora.domain.dto.produtos;

import br.com.faunora.domain.enums.ProdutoTipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoRecordDto(@NotBlank String nome, @NotNull @Positive BigDecimal preco, @NotBlank String marca, @NotBlank String descricao, @NotNull ProdutoTipo tipo) {
}
