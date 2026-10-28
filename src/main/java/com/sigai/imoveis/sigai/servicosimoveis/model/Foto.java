package com.sigai.imoveis.sigai.servicosimoveis.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fotos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imovel_id", nullable = false)
    private Imovel imovel;

    private String url;
    private String legenda;
    private Boolean principal;
}