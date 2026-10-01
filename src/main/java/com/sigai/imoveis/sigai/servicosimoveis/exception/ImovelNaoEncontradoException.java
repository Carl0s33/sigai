package com.sigai.imoveis.sigai.servicosimoveis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImovelNaoEncontradoException extends RuntimeException {

    public ImovelNaoEncontradoException() {
        super("Imóvel não encontrado.");
    }

    public ImovelNaoEncontradoException(Long id) {
        super("Imóvel não encontrado com o ID: " + id);
    }

    public ImovelNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ImovelNaoEncontradoException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}

