package br.com.nivaldo.usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nivaldo.usuario.entities.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
}
