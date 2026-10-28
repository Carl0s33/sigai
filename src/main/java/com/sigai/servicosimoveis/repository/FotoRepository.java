package com.sigai.servicosimoveis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigai.servicosimoveis.model.Foto;

import java.util.List;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
    List<Foto> findByImovelId(Long imovelId);
}