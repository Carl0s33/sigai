package com.sigai.imoveis.sigai.servicosimoveis.dto;

import com.sigai.imoveis.sigai.servicosimoveis.model.Foto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FotoResponseDTO {

    private Long id;
    private Long imovelId;
    private String url;
    private String legenda;
    private Boolean principal;

    public static FotoResponseDTO fromModel(Foto foto) {
        return FotoResponseDTO.builder()
                .id(foto.getId())
                .imovelId(foto.getImovel() != null ? foto.getImovel().getId() : null)
                .url(foto.getUrl())
                .legenda(foto.getLegenda())
                .principal(foto.getPrincipal())
                .build();
    }
}