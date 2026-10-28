package com.sigai.servicosimoveis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FotoRequestDTO {

    @NotNull(message = "O ID do imóvel não pode ser nulo")
    private Long imovelId;

    @NotBlank(message = "A URL é obrigatória")
    private String url;

    @Size(max = 200, message = "A legenda deve ter no máximo 200 caracteres")
    private String legenda;

    private Boolean principal;
}