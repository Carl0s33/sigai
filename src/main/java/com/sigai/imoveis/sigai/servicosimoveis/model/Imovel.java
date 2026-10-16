package com.sigai.imoveis.sigai.servicosimoveis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "imoveis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String endereco;
    @Column(name = "valor_aluguel", nullable = false)
    private double valorAluguel;
    @Column(length = 500)
    private String descricao;
}




