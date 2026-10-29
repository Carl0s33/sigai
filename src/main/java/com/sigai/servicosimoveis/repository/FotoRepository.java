package com.sigai.servicosimoveis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sigai.servicosimoveis.model.Foto;

public interface FotoRepository extends JpaRepository<Foto, Long> {}