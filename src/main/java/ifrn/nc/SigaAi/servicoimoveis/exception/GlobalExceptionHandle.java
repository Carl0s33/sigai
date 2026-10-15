package ifrn.nc.SigaAi.servicoimoveis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandle {

    private Map<String, String> extrairErros(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            erros.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return erros;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        var pd =ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Erro de validação");
        pd.setProperty("erros", extrairErros(ex));
        return pd;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ImovelNaoEncontradoExcption.class)
    public ProblemDetail handleNaoEncontrado(MethodArgumentNotValidException ex) {
        var pd =ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Imovel nao encontrado");
        pd.setDetail(ex.getMessage());
        return pd;
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleValidation(Exception ex) {
        var pd =ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Erro interno do servidor");
        pd.setDetail(ex.getMessage() + "ocorreu um erro inesperado");
        return pd;
    }

    @ExceptionHandler(FotoNaoEncontradoExcption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleValidation(FotoNaoEncontradoExcption ex) {
        var pd =ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Foto não encontrada");
        pd.setDetail(ex.getMessage());
        return pd;
    }




}
