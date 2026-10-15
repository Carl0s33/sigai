package ifrn.nc.SigaAi.servicoimoveis.model.dto.foto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FotoRequestDTO {
    @NotNull
    private Long imovelId;

    @NotNull @NotBlank
    private String url;
    private boolean principal;

    @Size(max = 200)
    private String legenda;
}
