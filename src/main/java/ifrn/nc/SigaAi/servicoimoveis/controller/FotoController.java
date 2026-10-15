package ifrn.nc.SigaAi.servicoimoveis.controller;


import ifrn.nc.SigaAi.servicoimoveis.model.dto.foto.FotoRequestDTO;
import ifrn.nc.SigaAi.servicoimoveis.model.dto.foto.FotoResponseDTO;
import ifrn.nc.SigaAi.servicoimoveis.service.FotoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/foto")
@AllArgsConstructor
public class FotoController {



    private  final FotoService fotoService;

    @GetMapping
    public ResponseEntity<List<FotoResponseDTO>> listarFotos() {
        return ResponseEntity.ok(fotoService.listarFotos());
    }

    @PostMapping()
    public ResponseEntity<FotoResponseDTO> salvarFoto(@Valid @RequestBody FotoRequestDTO response) {
        FotoResponseDTO fotoResponse = fotoService.salvarFoto(response);
        URI location = URI.create(String.format("/foto/%s", fotoResponse.getId()));
        return ResponseEntity.created(location).body(fotoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FotoResponseDTO> buscarFoto(@PathVariable Long id) {
        FotoResponseDTO response = fotoService.buscarFotoId(id);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<FotoResponseDTO> atualizarFoto(@PathVariable Long id, @Valid @RequestBody FotoRequestDTO record) {
        FotoResponseDTO response = fotoService.atualizarFoto(id, record);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FotoResponseDTO> deletarFoto(@PathVariable Long id) {
        FotoResponseDTO foto = fotoService.deletarFoto(id);
        return ResponseEntity.ok(foto);
    }


}
