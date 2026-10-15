package ifrn.nc.SigaAi.servicoimoveis.service;

import ifrn.nc.SigaAi.servicoimoveis.exception.ImovelNaoEncontradoExcption;
import ifrn.nc.SigaAi.servicoimoveis.model.dto.foto.FotoRequestDTO;
import ifrn.nc.SigaAi.servicoimoveis.model.dto.foto.FotoResponseDTO;
import ifrn.nc.SigaAi.servicoimoveis.model.Foto;
import ifrn.nc.SigaAi.servicoimoveis.model.Imovel;
import ifrn.nc.SigaAi.servicoimoveis.repository.FotoRepository;
import ifrn.nc.SigaAi.servicoimoveis.repository.ImovelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class FotoService {

    private final FotoRepository fotoRepository;
    private final ImovelRepository imovelRepository;


    public List<FotoResponseDTO> listarFotos() {
        return fotoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public FotoResponseDTO salvarFoto(FotoRequestDTO record) {
        Foto fotoModel = toEntity(record);
        Foto savedFoto = fotoRepository.save(fotoModel);
        return toResponse(savedFoto);
    }



    public FotoResponseDTO buscarFotoId(Long id) {
        Foto fotoModel = fotoRepository.findById(id).orElseThrow(() -> new RuntimeException("Foto não encontrada"));
        return toResponse(fotoModel);
    }


    public FotoResponseDTO atualizarFoto(Long id, FotoRequestDTO record) {
        Foto existingFoto = fotoRepository.findById(id).orElseThrow(() -> new RuntimeException("Foto não encontrada"));
        Foto updatedFoto = toEntity(record);
        updatedFoto.setId(existingFoto.getId());
        Foto savedFoto = fotoRepository.save(updatedFoto);
        return toResponse(savedFoto);
    }


    private Foto toEntity(FotoRequestDTO dto) {
        Imovel imovel = imovelRepository.findById(dto.getImovelId())
                .orElseThrow(() -> new ImovelNaoEncontradoExcption(dto.getImovelId()));

        return new Foto(
                null,
                imovel,
                dto.getUrl(),
                dto.isPrincipal(),
                dto.getLegenda()
        );
    }
    private FotoResponseDTO toResponse(Foto foto) {
        Imovel imovel = foto.getImovel();
        long idimovel = imovel.getId();

                return new FotoResponseDTO(
                foto.getId(),
                foto.getImovel() != null ? foto.getImovel().getId() : null,
                foto.getUrl(),
                foto.getLegenda(),
                foto.isPrincipal()
        );
    }

    public FotoResponseDTO deletarFoto(Long id) {
        FotoResponseDTO foto = buscarFotoId(id);
        fotoRepository.deleteById(id);
        return foto;
    }
}
