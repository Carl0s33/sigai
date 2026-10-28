package com.sigai.servicosimoveis.controller;

import com.sigai.servicosimoveis.dto.FotoRequestDTO;
import com.sigai.servicosimoveis.dto.FotoResponseDTO;
import com.sigai.servicosimoveis.service.FotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/fotos")
@RequiredArgsConstructor
public class FotoController {

    private final FotoService service;

    @GetMapping
    public List<FotoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public FotoResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<FotoResponseDTO> criar(@Valid @RequestBody FotoRequestDTO requestDTO) {
        FotoResponseDTO foto = service.criarFoto(requestDTO);
        URI location = URI.create("/fotos/" + foto.getId());
        return ResponseEntity.created(location).body(foto);
    }
}