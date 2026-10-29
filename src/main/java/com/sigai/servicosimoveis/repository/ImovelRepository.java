package com.sigai.servicosimoveis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigai.servicosimoveis.model.Imovel;

import java.util.List;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {
    List<Imovel> findByEnderecoContainingIgnoreCase(String endereco);
    List<Imovel> findByValorAluguelLessThanEqual(Double valorMaximo);
    List<Imovel> findByEnderecoContainingIgnoreCaseAndValorAluguelLessThanEqual(String endereco, Double valorMaximo);

}