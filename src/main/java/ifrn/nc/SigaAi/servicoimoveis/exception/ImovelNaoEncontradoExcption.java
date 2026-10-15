package ifrn.nc.SigaAi.servicoimoveis.exception;

public class ImovelNaoEncontradoExcption extends RuntimeException{

    public ImovelNaoEncontradoExcption(Long id) {
        super("Imovél não encontrado com o ID: " + id);
    }
}
