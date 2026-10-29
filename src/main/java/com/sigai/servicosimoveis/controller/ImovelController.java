package com.sigai.servicosimoveis.controller;

import com.sigai.servicosimoveis.dto.ImovelRequestDTO;
import com.sigai.servicosimoveis.dto.ImovelResponseDTO;
import com.sigai.servicosimoveis.service.ImovelService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/imoveis")
public class ImovelController {

    private final ImovelService service;

    public ImovelController(ImovelService service) {
        this.service = service;
    }
    @GetMapping
    public List<ImovelResponseDTO> listar(@RequestParam (required = false) String endereco, @RequestParam(required = false) Double valorMaximo) {
        return service.listar(endereco, valorMaximo);
    }
    @GetMapping("/{id}")
    public ImovelResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
    @PostMapping
    public ResponseEntity<ImovelResponseDTO> criar(@Valid @RequestBody ImovelRequestDTO dto) {
        ImovelResponseDTO imovel = service.criar(dto);
        URI uri = URI.create("/imoveis/" + imovel.getId());
        return ResponseEntity.created(uri).body(imovel);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ImovelResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ImovelRequestDTO imovel) {
        ImovelResponseDTO atualizado = service.atualizar(id, imovel);
        return ResponseEntity.ok(atualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    } 
}
