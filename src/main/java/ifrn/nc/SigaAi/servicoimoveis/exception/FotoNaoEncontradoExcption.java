package ifrn.nc.SigaAi.servicoimoveis.exception;

public class FotoNaoEncontradoExcption extends RuntimeException {
    public FotoNaoEncontradoExcption(Long id) {

        super("Foto não encontrada com ID: " + id);
    }
}
