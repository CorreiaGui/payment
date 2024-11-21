package br.com.payment.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.payment.dtos.UsuarioDto;
import br.com.payment.model.usuario.Usuario;
import br.com.payment.services.UsuarioService;

@RestController
@RequestMapping(("/usuarios"))
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity<Usuario> createUser(@RequestBody UsuarioDto usuarioDto) {
		Usuario newUsuario = service.createUsuario(usuarioDto);
		return new ResponseEntity<>(newUsuario, CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getUsuarios() {
		List<Usuario> usuarios = this.service.getUsuarios();
		return new ResponseEntity<>(usuarios, OK);
	}
}
