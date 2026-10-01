package com.sigai.imoveis.sigai.servicosimoveis.service;

import com.sigai.imoveis.sigai.servicosimoveis.dto.ImovelRequestDTO;
import com.sigai.imoveis.sigai.servicosimoveis.dto.ImovelResponseDTO;
import com.sigai.imoveis.sigai.servicosimoveis.exception.ImovelNaoEncontradoException;
import com.sigai.imoveis.sigai.servicosimoveis.model.Imovel;
import com.sigai.imoveis.sigai.servicosimoveis.repository.ImovelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ImovelService {
    private final ImovelRepository imovelRepository;

    public List<ImovelResponseDTO> listar() {
        return imovelRepository.findAll().stream()
                .map(ImovelResponseDTO::fromModel)
                .toList();
    }


    public ImovelResponseDTO criarImovel(ImovelRequestDTO dto) {
        Imovel salvo = imovelRepository.save(dto.toModel());
        return ImovelResponseDTO.fromModel(salvo);
    }

    public ImovelResponseDTO buscar(Long id) {
        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new ImovelNaoEncontradoException(id));
        return ImovelResponseDTO.fromModel(imovel);
    }

    public List<ImovelResponseDTO> buscarPorValorAluguelMenorQue(double valorAluguel) {
        return imovelRepository.findByValorAluguelLessThan(valorAluguel).stream()
                .map(ImovelResponseDTO::fromModel)
                .toList();
    }

    public List<ImovelResponseDTO> buscarPorEndereco(String endereco) {
        return imovelRepository.findByEnderecoIgnoreCase(endereco).stream()
                .map(ImovelResponseDTO::fromModel)
                .toList();
    }

    public List<ImovelResponseDTO> buscarPorDescricao(String descricao) {
        return imovelRepository.findByDescricaoIgnoreCase(descricao).stream()
                .map(ImovelResponseDTO::fromModel)
                .toList();
    }

}
