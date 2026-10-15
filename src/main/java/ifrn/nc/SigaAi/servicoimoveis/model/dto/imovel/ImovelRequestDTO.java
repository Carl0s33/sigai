package ifrn.nc.SigaAi.servicoimoveis.model.dto.imovel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ImovelRequestDTO {

    @NotBlank(message = "O campo 'endereco' não pode estar em branco.")
    private String endereco;

    @NotNull @Positive
    private Double valorAluguel;

    @Size(max = 500)
    private String descricao;
}
