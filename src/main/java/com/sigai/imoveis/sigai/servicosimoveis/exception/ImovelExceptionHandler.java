package com.sigai.imoveis.sigai.servicosimoveis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ImovelExceptionHandler {

    @ExceptionHandler(ImovelNaoEncontradoException.class)
    public ProblemDetail handleImovelNaoEncontrado(ImovelNaoEncontradoException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Imóvel não encontrado");
        pd.setDetail(ex.getMessage());
        return pd;
    }
}
