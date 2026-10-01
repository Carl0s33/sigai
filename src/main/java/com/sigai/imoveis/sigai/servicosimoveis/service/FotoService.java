package com.sigai.imoveis.sigai.servicosimoveis.service;

import com.sigai.imoveis.sigai.servicosimoveis.dto.FotoRequestDTO;
import com.sigai.imoveis.sigai.servicosimoveis.dto.FotoResponseDTO;
import com.sigai.imoveis.sigai.servicosimoveis.exception.FotoNaoEncontradaException;
import com.sigai.imoveis.sigai.servicosimoveis.model.Foto;
import com.sigai.imoveis.sigai.servicosimoveis.repository.FotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FotoService {
    private final FotoRepository fotoRepository;

    public List<FotoResponseDTO> listar() {
        return fotoRepository.listar().stream()
                .map(FotoResponseDTO::fromModel)
                .toList();
    }

    public FotoResponseDTO criarFoto(FotoRequestDTO fotoRequestDTO) {
        Foto salvo = fotoRepository.salvar(fotoRequestDTO.toModel());
        return FotoResponseDTO.fromModel(salvo);
    }

    public FotoResponseDTO buscar(Long id) {
        Foto foto = fotoRepository.buscarPorId(id)
                .orElseThrow(() -> new FotoNaoEncontradaException(id));
        return FotoResponseDTO.fromModel(foto);
    }

}