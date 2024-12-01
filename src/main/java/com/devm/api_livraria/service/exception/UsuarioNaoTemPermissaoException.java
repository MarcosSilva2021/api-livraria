package com.devm.api_livraria.service.exception;

public class UsuarioNaoTemPermissaoException extends RuntimeException {

    public UsuarioNaoTemPermissaoException(String mensagem) {
        super(mensagem);
    }
}
