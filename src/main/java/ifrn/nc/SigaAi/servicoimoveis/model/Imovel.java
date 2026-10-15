package ifrn.nc.SigaAi.servicoimoveis.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imoveis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String endereco;
    private Double valorAluguel;
    private String descricao;
}
