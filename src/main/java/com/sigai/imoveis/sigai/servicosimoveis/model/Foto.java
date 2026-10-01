package com.sigai.imoveis.sigai.servicosimoveis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Foto {
    private Long id;
    private Long imovelId;
    private String url;
    private String legenda;
    private Boolean principal;

}
