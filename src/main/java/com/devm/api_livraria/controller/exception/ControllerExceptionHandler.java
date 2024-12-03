package com.devm.api_livraria.controller.exception;

import com.devm.api_livraria.service.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<ErrorResponse> notFound(EntityNotFound e, HttpServletRequest request){
        String error = "Not Found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UsuarioJaExisteException.class)
    public ResponseEntity<ErrorResponse> usuariojaExisteErro(UsuarioJaExisteException e, HttpServletRequest request){
        String error = "Usuário já esta cadastrado no sistema";
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UsuarioNaoExisteException.class)
    public ResponseEntity<ErrorResponse> usuarioNaoExisteErro(UsuarioNaoExisteException e, HttpServletRequest request){
        String error = "Usuário não existe no sistema";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(LoginInvalidoException.class)
    public ResponseEntity<ErrorResponse> loginInvalidoErro(LoginInvalidoException e, HttpServletRequest request){
        String error = "Login inválido ...";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UsuarioNaoTemPermissaoException.class)
    public ResponseEntity<ErrorResponse> usuarioNaoTemPermissaoErro(UsuarioNaoTemPermissaoException e, HttpServletRequest request){
        String error = "Usuário não tem permissão !";
        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorResponse> erroDeSegurancaDoToken(SecurityException e, HttpServletRequest request){
        String error = "Token foi inválido !";
        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorResponse err = new ErrorResponse(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
