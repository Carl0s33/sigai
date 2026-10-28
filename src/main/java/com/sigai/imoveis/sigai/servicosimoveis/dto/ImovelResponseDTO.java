package com.sigai.imoveis.sigai.servicosimoveis.dto;
import com.sigai.imoveis.sigai.servicosimoveis.model.Imovel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImovelResponseDTO {

    private Long id;
    private String endereco;
    private double valorAluguel;
    private String descricao;

    public static ImovelResponseDTO fromModel(Imovel imovel) {
        return ImovelResponseDTO.builder()
                .id(imovel.getId())
                .endereco(imovel.getEndereco())
                .valorAluguel(imovel.getValorAluguel())
                .descricao(imovel.getDescricao())
                .build();
    }
}