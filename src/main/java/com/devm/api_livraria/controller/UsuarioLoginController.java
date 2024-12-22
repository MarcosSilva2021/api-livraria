package com.devm.api_livraria.controller;

import com.devm.api_livraria.dto.UsuarioDeLogin;
import com.devm.api_livraria.dto.UsuarioDeRegistro;
import com.devm.api_livraria.dto.UsuarioToken;
import com.devm.api_livraria.entity.Usuario;
import com.devm.api_livraria.service.JWTService;
import com.devm.api_livraria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api")
public class UsuarioLoginController {

    private UsuarioService usuarioService;
    private JWTService jwtService;

    @Autowired
    public UsuarioLoginController(UsuarioService usuarioService, JWTService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/registro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody UsuarioDeRegistro usuarioDeRegistro){
        var usuarioParaSalvar = usuarioService.cadastrarUsuario(usuarioDeRegistro);
        return ResponseEntity.ok(usuarioParaSalvar);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UsuarioToken> autenticarUsuario(@RequestBody UsuarioDeLogin usuarioDeLogin){
        var tokenDoUsuario = jwtService.autenticaUsuario(usuarioDeLogin);
        return ResponseEntity.ok(tokenDoUsuario);
    }


}
