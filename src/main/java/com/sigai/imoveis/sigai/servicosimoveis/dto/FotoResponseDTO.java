package com.sigai.imoveis.sigai.servicosimoveis.dto;

import com.sigai.imoveis.sigai.servicosimoveis.model.Foto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;

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

    public FotoResponseDTO(@NonNull Foto foto) {
        this(
                foto.getId(),
                foto.getImovelId(),
                foto.getUrl(),
                foto.getLegenda(),
                foto.getPrincipal()
        );
    }

    public static FotoResponseDTO fromModel(Foto foto) {
        return FotoResponseDTO.builder()
                .id(foto.getId())
                .imovelId(foto.getImovelId())
                .url(foto.getUrl())
                .legenda(foto.getLegenda())
                .principal(foto.getPrincipal())
                .build();
    }
}