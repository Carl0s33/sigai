package com.sigai.servicosimoveis.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sigai.servicosimoveis.dto.ImovelRequestDTO;
import com.sigai.servicosimoveis.dto.ImovelResponseDTO;
import com.sigai.servicosimoveis.service.ImovelService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/imoveis")
@RequiredArgsConstructor
public class ImovelController {

    private final ImovelService service;

    @GetMapping
    public List<ImovelResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ImovelResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<ImovelResponseDTO> criar(@Valid @RequestBody ImovelRequestDTO requestDTO) {
        ImovelResponseDTO imovel = service.criarImovel(requestDTO);
        URI location = URI.create("/imoveis/" + imovel.getId());
        return ResponseEntity.created(location).body(imovel);
    }
}
