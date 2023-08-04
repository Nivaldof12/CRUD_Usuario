package br.com.nivaldo.usuario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.nivaldo.usuario.entities.Usuario;
import br.com.nivaldo.usuario.services.ServicoUsuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class ControllerUsuario {

	@Autowired
	private ServicoUsuario servicoUsuario;

	@GetMapping("/listartodos")
	public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
		List<Usuario> usuarios = servicoUsuario.listaTodosUsuarios();
		return ResponseEntity.ok(usuarios);
	}

	@PostMapping("/cadastrar")
	public Usuario novoUsuario(@RequestBody Usuario usuario) {
		return servicoUsuario.novoUsuario(usuario);
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		servicoUsuario.excluirUsuarioPorId(id);
		return ResponseEntity.ok("Horário excluído com sucesso!");
	}

	@PutMapping("/{id}/alterar")
	public ResponseEntity<Map<String, String>> alterar(@PathVariable Long id, @RequestBody Usuario usuarioAlterado) {
		Optional<Usuario> usuarioExistente = servicoUsuario.encontrarUsuarioPorId(id);
		if (usuarioExistente.isPresent()) {
			Usuario usuario = usuarioExistente.get();
			usuario.setNome(usuarioAlterado.getNome());
			usuario.setEmail(usuarioAlterado.getEmail());
			usuario.setDataCadastro(usuarioAlterado.getDataCadastro());

			servicoUsuario.novoUsuario(usuario);

			Map<String, String> response = new HashMap<>();
			response.put("message", "Usuario alterado com sucesso!");
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/encontrarid/{id}")
	public ResponseEntity<Usuario> encontrarUsuarioPorId(@PathVariable Long id) {
		Optional<Usuario> usuario = servicoUsuario.encontrarUsuarioPorId(id);
		if (usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
