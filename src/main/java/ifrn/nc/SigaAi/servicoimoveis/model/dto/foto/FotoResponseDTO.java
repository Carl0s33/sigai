package ifrn.nc.SigaAi.servicoimoveis.model.dto.foto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FotoResponseDTO {

    public FotoResponseDTO(Long id, Long imovelId, String url, String legenda, boolean principal) {
        this.id = id;
        this.imovelId = imovelId;
        this.url = url;
        this.legenda = legenda;
        this.principal = principal;
    }

    private Long id;
    private Long imovelId;
    private String url;
    private boolean principal;
    private String legenda;

}
