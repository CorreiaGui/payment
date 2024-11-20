package br.com.payment.service;

import static br.com.payment.enums.TipoUsuario.LOJISTA;
import static java.lang.Boolean.TRUE;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.payment.model.usuario.Usuario;
import br.com.payment.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public void validaTransacao(Usuario remetente, BigDecimal valor) {
		verificaUsuarioTransacao(remetente);
		verificaSaldoTransacao(remetente, valor);
	}
	
	public void verificaEmailExistente(String email) {
		if(TRUE.equals(repository.existsByEmail(email))) {
			throw new IllegalArgumentException("Email informado já cadastrado.");
		}
	}
	
	public Usuario findUserById(Long id) {
		return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado."));
	}
	
	public void saveUsuario(Usuario usuario) {
		this.repository.save(usuario);
	}

	private void verificaSaldoTransacao(Usuario remetente, BigDecimal valor) {
		if(remetente.getSaldo().compareTo(valor) < 0) {
			throw new IllegalArgumentException("Saldo insuficiente para a transação.");
		}
	}

	private void verificaUsuarioTransacao(Usuario remetente) {
		if (remetente.getTipo().equals(LOJISTA)) {
			throw new IllegalArgumentException("A operação de transação não é permitida para usuários lojistas");
		}
	}
}
