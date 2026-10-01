package com.sigai.imoveis.sigai.servicosimoveis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FotoNaoEncontradaException extends RuntimeException {

    public FotoNaoEncontradaException() {
        super("Foto não encontrada.");
    }

    public FotoNaoEncontradaException(Long id) {
        super("Foto não encontrada com o ID: " + id);
    }

    public FotoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FotoNaoEncontradaException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
