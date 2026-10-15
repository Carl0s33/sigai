package ifrn.nc.SigaAi.servicoimoveis.model.dto.imovel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ImovelResponseDTO {
    private Long id;
    private String endereco;
    private Double valorAluguel;
}
