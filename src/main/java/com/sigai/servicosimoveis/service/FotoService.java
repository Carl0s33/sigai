package com.sigai.servicosimoveis.service;

import ifrn.sigai.servicoimoveis.dto.FotoRequestDTO;
import ifrn.sigai.servicoimoveis.dto.FotoResponseDTO;
import ifrn.sigai.servicoimoveis.exception.FotoNaoEncontradaException;
import ifrn.sigai.servicoimoveis.exception.ImovelNaoEncontradoException;
import ifrn.sigai.servicoimoveis.model.Foto;
import ifrn.sigai.servicoimoveis.model.Imovel;
import ifrn.sigai.servicoimoveis.repository.FotoRepository;
import ifrn.sigai.servicoimoveis.repository.ImovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FotoService {

    private final FotoRepository fotoRepository;
    private final ImovelRepository imovelRepository;

    public List<FotoResponseDTO> listar() {
        return fotoRepository.findAll().stream()
                .map(FotoResponseDTO::fromModel)
                .toList();
    }

    public FotoResponseDTO criarFoto(FotoRequestDTO dto) {
        Imovel imovel = imovelRepository.findById(dto.getImovelId())
                .orElseThrow(() -> new ImovelNaoEncontradoException(dto.getImovelId()));

        Foto foto = Foto.builder()
                .imovel(imovel)
                .url(dto.getUrl())
                .legenda(dto.getLegenda())
                .principal(dto.getPrincipal())
                .build();

        Foto salva = fotoRepository.save(foto);
        return FotoResponseDTO.fromModel(salva);
    }

    public FotoResponseDTO buscar(Long id) {
        Foto foto = fotoRepository.findById(id)
                .orElseThrow(() -> new FotoNaoEncontradaException(id));
        return FotoResponseDTO.fromModel(foto);
    }
}