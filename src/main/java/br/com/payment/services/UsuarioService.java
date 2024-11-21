package br.com.payment.services;

import static br.com.payment.enums.TipoUsuario.LOJISTA;
import static java.lang.Boolean.TRUE;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.payment.dtos.UsuarioDto;
import br.com.payment.model.usuario.Usuario;
import br.com.payment.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public void validaTransacao(Usuario remetente, BigDecimal valor) throws Exception {
		verificaUsuarioTransacao(remetente);
		verificaSaldoTransacao(remetente, valor);
	}
	
	public void verificaEmailExistente(String email) throws Exception {
		if(TRUE.equals(repository.existsByEmail(email))) {
			throw new Exception("Email informado já cadastrado.");
		}
	}

	private void verificaSaldoTransacao(Usuario remetente, BigDecimal valor) throws Exception {
		if(remetente.getSaldo().compareTo(valor) < 0) {
			throw new Exception("Saldo insuficiente para a transação.");
		}
	}

	private void verificaUsuarioTransacao(Usuario remetente) {
		if (remetente.getTipoUsuario().equals(LOJISTA)) {
			throw new IllegalArgumentException("A operação de transação não é permitida para usuários lojistas");
		}
	}

	public Usuario findUserById(Long id) throws Exception {
		return this.repository.findById(id).orElseThrow(() -> new Exception("Usuario não encontrado."));
	}
	
	public void saveUsuario(Usuario usuario) {
		this.repository.save(usuario);
	}
	
	public Usuario createUsuario(UsuarioDto usuarioDto) {
		Usuario newUsuario = new Usuario(usuarioDto);
		this.saveUsuario(newUsuario);
		return newUsuario;
	}

	public List<Usuario> getUsuarios() {
		return this.repository.findAll();
	}
}
