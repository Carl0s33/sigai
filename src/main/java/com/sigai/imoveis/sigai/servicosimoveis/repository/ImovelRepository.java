package com.sigai.imoveis.sigai.servicosimoveis.repository;

import com.sigai.imoveis.sigai.servicosimoveis.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    List<Imovel> findByValorAluguelLessThan(double valorAluguel);

    List<Imovel> findByEnderecoIgnoreCase(String endereco);

    List<Imovel> findByDescricaoIgnoreCase(String descricao);

}
