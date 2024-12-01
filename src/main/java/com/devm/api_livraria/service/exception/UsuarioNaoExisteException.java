package com.devm.api_livraria.service.exception;

public class UsuarioNaoExisteException extends RuntimeException {

    public UsuarioNaoExisteException(String mensagem) {
        super(mensagem);
    }
}
