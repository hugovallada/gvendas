package com.github.hugovallada.gvendas.excecao;

public class RegraNegocioException extends RuntimeException {

    private String mensagem;

    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }

}
