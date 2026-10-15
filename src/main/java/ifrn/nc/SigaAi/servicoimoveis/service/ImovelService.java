package ifrn.nc.SigaAi.servicoimoveis.service;

import ifrn.nc.SigaAi.servicoimoveis.exception.ImovelNaoEncontradoExcption;
import ifrn.nc.SigaAi.servicoimoveis.model.Imovel;
import ifrn.nc.SigaAi.servicoimoveis.model.dto.foto.FotoRequestDTO;
import ifrn.nc.SigaAi.servicoimoveis.model.dto.foto.FotoResponseDTO;
import ifrn.nc.SigaAi.servicoimoveis.model.dto.imovel.ImovelRequestDTO;
import ifrn.nc.SigaAi.servicoimoveis.model.dto.imovel.ImovelResponseDTO;
import ifrn.nc.SigaAi.servicoimoveis.model.Foto;
import ifrn.nc.SigaAi.servicoimoveis.model.mapper.FotoMapper;
import ifrn.nc.SigaAi.servicoimoveis.model.mapper.ImovelMapper;
import ifrn.nc.SigaAi.servicoimoveis.repository.ImovelRepository;
import ifrn.nc.SigaAi.servicoimoveis.repository.FotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ImovelService {

    private  final ImovelRepository imovelRepository;
    private final ImovelMapper Mapper;

    public ImovelResponseDTO salvarImovel(ImovelRequestDTO request) {
        Imovel imovelModel = Mapper.toModel(request);
        imovelRepository.save(imovelModel);
        return Mapper.toResponseDTO(imovelModel);

    }

    public ImovelResponseDTO buscarImovelId(Long id) {
        Imovel imovel = imovelRepository.findById(id).orElseThrow(
                () -> new ImovelNaoEncontradoExcption(id)
        );
        return Mapper.toResponseDTO(imovel);
    }

    public List<ImovelResponseDTO> listaImoveis(String endereco, Double valor) {
        List<Imovel> imoveis;
        if (endereco != null && valor != null) {
            imoveis = imovelRepository.findByEnderecoContainingIgnoreCaseAndValorAluguelLessThanEqual(endereco, valor);

        } else if (endereco != null) {
            imoveis = imovelRepository.findByEnderecoContainingIgnoreCase(endereco);
            return Mapper.toListResponseDTO(imoveis);
        } else if (valor != null) {
            imoveis = imovelRepository.findByValorAluguelLessThan(valor);
            return Mapper.toListResponseDTO(imoveis);
        } else {
            imoveis = imovelRepository.findAll();

        }
        return Mapper.toListResponseDTO(imoveis);
    }

    public ImovelResponseDTO atualizarImovel(Long id, ImovelRequestDTO request) {
        Imovel imovel = imovelRepository.findById(id).orElseThrow(
                () -> new ImovelNaoEncontradoExcption(id)
        );
        Imovel imovelModel = Mapper.toModel(request);
        imovelModel.setId(imovel.getId());

        imovelRepository.save(imovelModel);
        return Mapper.toResponseDTO(imovelModel);
    }

    public void deletarImovel(Long id) {
        Imovel imovel = imovelRepository.findById(id).orElseThrow(
                () -> new ImovelNaoEncontradoExcption(id)
        );
        imovelRepository.deleteById(id);
    }




}
