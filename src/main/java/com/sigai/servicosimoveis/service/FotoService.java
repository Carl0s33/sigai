package com.sigai.servicosimoveis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sigai.servicosimoveis.dto.FotoRequestDTO;
import com.sigai.servicosimoveis.dto.FotoResponseDTO;
import com.sigai.servicosimoveis.exception.FotoNaoEncontradaException;
import com.sigai.servicosimoveis.exception.ImovelNaoEncontradoException;
import com.sigai.servicosimoveis.model.Foto;
import com.sigai.servicosimoveis.model.Imovel;
import com.sigai.servicosimoveis.repository.FotoRepository;
import com.sigai.servicosimoveis.repository.ImovelRepository;

@Service 
public class FotoService {

    private final FotoRepository repository;
    private final ImovelRepository imovelRepository;

    public FotoService(FotoRepository repository, ImovelRepository imovelRepository) {
        this.repository = repository;
        this.imovelRepository = imovelRepository;
    }
    public FotoResponseDTO criar(FotoRequestDTO dto) {
        Foto salvo = repository.save(toEntity(dto));
        return toResponseDTO(salvo);
    }
    public FotoResponseDTO buscarPorId(Long id) {
        Foto foto = repository.findById(id)
            .orElseThrow(() -> new FotoNaoEncontradaException(id));
        return toResponseDTO(foto);
    }
    public FotoResponseDTO atualizar(Long id, FotoRequestDTO dto) {
        repository.findById(id)
                .orElseThrow(() -> new FotoNaoEncontradaException(id));

        Foto fotoAtualizada = toEntity(dto);
        fotoAtualizada.setId(id);
        fotoAtualizada = repository.save(fotoAtualizada); 

        return toResponseDTO(fotoAtualizada);
    }
    public void deletar(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new FotoNaoEncontradaException(id));
        repository.deleteById(id);
    }
    public List<FotoResponseDTO> listar() {
        List<Foto> fotos = repository.findAll();
        return fotos.stream()
                .map(this::toResponseDTO)
                .toList();
    }


    private Foto toEntity(FotoRequestDTO dto) {
        Imovel imovel = imovelRepository.findById(dto.getImovelId())
            .orElseThrow(() -> new ImovelNaoEncontradoException(dto.getImovelId()));
        return new Foto(null,imovel, dto.getUrl(), dto.getLegenda(), dto.getPrincipal());
    }
    private FotoResponseDTO toResponseDTO(Foto foto) {
        Imovel imovel = foto.getImovel();
        long idimovel = imovel.getId();
        return new FotoResponseDTO(foto.getId(), idimovel, foto.getUrl(), foto.getLegenda(), foto.getPrincipal());
       
    }
}