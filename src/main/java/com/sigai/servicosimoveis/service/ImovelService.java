package com.sigai.servicosimoveis.service;

import com.sigai.servicosimoveis.dto.ImovelRequestDTO;
import com.sigai.servicosimoveis.dto.ImovelResponseDTO;
import com.sigai.servicosimoveis.exception.ImovelNaoEncontradoException;
import com.sigai.servicosimoveis.model.Imovel;
import com.sigai.servicosimoveis.repository.ImovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImovelService {

    private final ImovelRepository imovelRepository;

    public List<ImovelResponseDTO> listar() {
        return imovelRepository.findAll().stream()
                .map(ImovelResponseDTO::fromModel)
                .toList();
    }

    public ImovelResponseDTO criarImovel(ImovelRequestDTO dto) {
        Imovel imovel = Imovel.builder()
                .endereco(dto.getEndereco())
                .valorAluguel(dto.getValorAluguel())
                .descricao(dto.getDescricao())
                .build();

        Imovel salvo = imovelRepository.save(imovel);
        return ImovelResponseDTO.fromModel(salvo);
    }

    public ImovelResponseDTO buscar(Long id) {
        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new ImovelNaoEncontradoException(id));
        return ImovelResponseDTO.fromModel(imovel);
    }
}