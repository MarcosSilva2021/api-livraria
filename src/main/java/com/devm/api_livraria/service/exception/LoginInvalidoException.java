package com.devm.api_livraria.service.exception;

public class LoginInvalidoException extends RuntimeException {

    public LoginInvalidoException(String mensagem) {
        super(mensagem);
    }
}
