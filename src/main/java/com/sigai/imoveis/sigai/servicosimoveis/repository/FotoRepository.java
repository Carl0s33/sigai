package com.sigai.imoveis.sigai.servicosimoveis.repository;

import com.sigai.imoveis.sigai.servicosimoveis.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
    List<Foto> findByImovelId(Long imovelId);
}