package com.sigai.servicosimoveis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImovelRequestDTO {

    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;

    @Positive(message = "O valor do aluguel deve ser maior que zero")
    private double valorAluguel;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;
}
