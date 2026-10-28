package com.sigai.servicosimoveis.service;

import com.sigai.servicosimoveis.dto.FotoRequestDTO;
import com.sigai.servicosimoveis.dto.FotoResponseDTO;
import com.sigai.servicosimoveis.exception.FotoNaoEncontradaException;
import com.sigai.servicosimoveis.exception.ImovelNaoEncontradoException;
import com.sigai.servicosimoveis.model.Foto;
import com.sigai.servicosimoveis.model.Imovel;
import com.sigai.servicosimoveis.repository.FotoRepository;
import com.sigai.servicosimoveis.repository.ImovelRepository;
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