package com.sigai.imoveis.sigai.servicosimoveis.repository;

import com.sigai.imoveis.sigai.servicosimoveis.model.Foto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class FotoRepository {
    private final List<Foto> fotos = new ArrayList<>();
    private final AtomicLong proximoId = new AtomicLong();

    public List<Foto> listar() {
        return fotos;
    }

    public Optional<Foto> buscarPorId(Long id) {
        return fotos.stream().filter(foto -> foto.getId().equals(id)).findFirst();
    }

    public Foto salvar(Foto foto) {
        if (foto.getId() == null) {
            foto.setId(proximoId.getAndIncrement());
            fotos.add(foto);
            return foto;
        } else {
            deletarPorId(foto.getId());
            fotos.add(foto);
        }
        return foto;
    }

    public void deletarPorId(Long id) {
        fotos.removeIf(foto -> foto.getId().equals(id));
    }
}