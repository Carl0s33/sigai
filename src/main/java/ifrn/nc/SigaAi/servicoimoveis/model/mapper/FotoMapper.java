package ifrn.nc.SigaAi.servicoimoveis.model.mapper;

import ifrn.nc.SigaAi.servicoimoveis.model.dto.foto.FotoRequestDTO;
import ifrn.nc.SigaAi.servicoimoveis.model.dto.foto.FotoResponseDTO;
import ifrn.nc.SigaAi.servicoimoveis.model.Foto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FotoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "imovelId", target = "imovel.id")
    Foto toModel(FotoRequestDTO record);

    @Mapping(source = "imovel.id", target = "imovelId")
    FotoResponseDTO toResponse(Foto savedFoto);
}
