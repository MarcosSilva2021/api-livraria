package com.devm.api_livraria.service;

import com.devm.api_livraria.dto.UsuarioDeRegistro;
import com.devm.api_livraria.entity.Usuario;
import com.devm.api_livraria.repository.UsuarioRepository;
import com.devm.api_livraria.service.exception.UsuarioJaExisteException;
import com.devm.api_livraria.service.exception.UsuarioNaoExisteException;
import com.devm.api_livraria.service.exception.UsuarioNaoTemPermissaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrarUsuario(UsuarioDeRegistro usuarioDeRegistro){
        Optional<Usuario> usuarioRetornado = repository.findByEmail(usuarioDeRegistro.email());

        if (usuarioRetornado.isPresent()){
            throw new UsuarioJaExisteException("Usuário já existe no sistema");
        }

        Usuario usuarioParaSalvar = new Usuario(usuarioDeRegistro);

        repository.save(usuarioParaSalvar);

        return usuarioParaSalvar;
    }

    private boolean usuarioTemPermissao(String header, String email){
        String sujeitoDoToken = "já já voltamos pessoal";
        Optional<Usuario> usuarioRetornado = repository.findByEmail(sujeitoDoToken);
        return usuarioRetornado.isPresent() && usuarioRetornado.get().getEmail().equals(email);
    }

    public Usuario buscaUsuario(String email, String header){
        Optional<Usuario> usuarioRetornado = repository.findByEmail(email);

        if (usuarioRetornado.isEmpty()){
            throw new UsuarioNaoExisteException("Usuário não existe");
        }

        if (!usuarioTemPermissao(header, email)){
            throw new UsuarioNaoTemPermissaoException("O usuário não tem permissão de acessar este recurso !");
        }

        return usuarioRetornado.get();
    }

    public void removerUsuario(String email, String header){
        Optional<Usuario> usuarioParaRemover = repository.findByEmail(email);

        if (usuarioParaRemover.isEmpty()){
            throw new UsuarioNaoExisteException("Usuário não existe");
        }

        if (!usuarioTemPermissao(header, email)){
            throw new UsuarioNaoTemPermissaoException("O usuário não tem permissão de acessar este recurso !");
        }

        repository.delete(usuarioParaRemover.get());

    }
}
