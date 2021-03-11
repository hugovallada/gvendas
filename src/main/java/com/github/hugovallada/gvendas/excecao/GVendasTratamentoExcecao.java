package com.github.hugovallada.gvendas.excecao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GVendasTratamentoExcecao extends ResponseEntityExceptionHandler {

    public static final String NOT_BLANK = "NotBlank";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Erro> erros = gerarListDeErros(ex.getBindingResult());

        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String msgUsuario = "Não foi possível encontrar um objeto com o código passado";
        String msgDesenvolvedor = ex.toString();

        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String msgUsuario = ex.getMessage();
        String msgDesenvolvedor = ex.toString();

        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex, WebRequest request) {
        String msgUsuario = ex.getMessage();
        String msgDesenvolvedor = ex.getMessage();

        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> gerarListDeErros(BindingResult bindingResult) {

        List<Erro> erros = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(erro -> {
            String msgUsuario = tratarMensagemDeErroParaUsuario(erro);
            String msgDesenvolvedor = erro.toString();

            erros.add(new Erro(msgUsuario, msgDesenvolvedor));

        });

        return erros;
    }

    private String tratarMensagemDeErroParaUsuario(FieldError erro) {
        if (erro.getCode().equals(NOT_BLANK)) {
            return erro.getDefaultMessage().concat(" - é obrigatório");
        }

        return erro.getDefaultMessage();
    }
}
