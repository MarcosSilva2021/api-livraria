package com.devm.api_livraria.controller;

import com.devm.api_livraria.entity.Usuario;
import com.devm.api_livraria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth/usuarios")
public class UsuarioController {

    private UsuarioService service;
    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<Usuario> buscaUsuario(@PathVariable String email, @RequestHeader("Authorization") String header){
        var usuarioRetornado = service.buscaUsuario(email, header);
        return ResponseEntity.ok(usuarioRetornado);
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> removeUsuario(@PathVariable String email, @RequestHeader("Authorization") String header){
        service.removerUsuario(email, header);
        return ResponseEntity.ok().build();
    }

}
