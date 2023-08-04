package br.com.nivaldo.usuario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.nivaldo.usuario.entities.Usuario;
import br.com.nivaldo.usuario.repositories.RepositorioUsuario;

import java.util.List;
import java.util.Optional;

@Component
public class ServicoUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public Usuario novoUsuario(Usuario usuario) {
        return repositorioUsuario.save(usuario);
    }

    public List<Usuario> listaTodosUsuarios() {
        return repositorioUsuario.findAll();
    }

    public void excluirUsuarioPorId(Long id) {
    	repositorioUsuario.deleteById(id);
    }
    
    public Optional<Usuario> encontrarUsuarioPorId(Long id) {
        return repositorioUsuario.findById(id);
    }  
}
