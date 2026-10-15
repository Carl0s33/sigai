package ifrn.nc.SigaAi.servicoimoveis.controller;

import ifrn.nc.SigaAi.servicoimoveis.exception.ImovelNaoEncontradoExcption;
import ifrn.nc.SigaAi.servicoimoveis.model.mapper.ImovelMapper;
import ifrn.nc.SigaAi.servicoimoveis.model.Imovel;
import ifrn.nc.SigaAi.servicoimoveis.model.dto.imovel.ImovelResponseDTO;
import ifrn.nc.SigaAi.servicoimoveis.model.dto.imovel.ImovelRequestDTO;
import ifrn.nc.SigaAi.servicoimoveis.repository.ImovelRepository;
import ifrn.nc.SigaAi.servicoimoveis.service.ImovelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/imovel")
@AllArgsConstructor
public class ImovelController {

    private  final ImovelService imovelService;

    @GetMapping
    public ResponseEntity<List<ImovelResponseDTO>> listar(@RequestParam String endereco, @RequestParam Double valor) {
        List<ImovelResponseDTO> imoveis = imovelService.listaImoveis(endereco, valor);
        return ResponseEntity.ok(imoveis);
    }

    @PostMapping()
    public ResponseEntity<ImovelResponseDTO> salvarImovel(@Valid @RequestBody  ImovelRequestDTO response) {
        ImovelResponseDTO imovelResponse = imovelService.salvarImovel(response);
        URI location = URI.create(String.format("/imovel/%s", imovelResponse.getId()));
        return ResponseEntity.created(location).body(imovelResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImovelResponseDTO> buscarImovel(@PathVariable Long id) {
        ImovelResponseDTO response = imovelService.buscarImovelId(id);
        return ResponseEntity.ok(response);
    }


}
