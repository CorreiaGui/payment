package br.com.payment.service;

import static java.time.ZonedDateTime.now;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.payment.model.dtos.TransacaoDto;
import br.com.payment.model.transacao.Transacao;
import br.com.payment.model.usuario.Usuario;
import br.com.payment.repositories.TransacaoRepository;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository repository;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void criarTransacao(TransacaoDto transacaoDto) {
		Usuario remetente = usuarioService.findUserById(transacaoDto.remetenteId());
		Usuario destinatario = usuarioService.findUserById(transacaoDto.remetenteId());
		usuarioService.validaTransacao(remetente, transacaoDto.valor());
		transacaoAutorizada(remetente, transacaoDto.valor());
		
		if(!transacaoAutorizada(remetente, transacaoDto.valor())) {
			throw new IllegalArgumentException("Transação não autorizada.");
		}
		
		Transacao transacao = new Transacao();
		transacao.setRemetente(remetente);
		transacao.setDestinatario(destinatario);
		transacao.setValor(transacaoDto.valor());
		transacao.setDataHora(now());
		
		remetente.setSaldo(remetente.getSaldo().subtract(transacaoDto.valor()));
		destinatario.setSaldo(destinatario.getSaldo().add(transacaoDto.valor()));
		
		this.repository.save(transacao);
		this.usuarioService.saveUsuario(destinatario);
		this.usuarioService.saveUsuario(remetente);
	}
	
	public boolean transacaoAutorizada(Usuario remetente, BigDecimal valor) {
		ResponseEntity<Map> response = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
		if(!response.getBody().get("status").equals("success")) {
			return false;
		} else {
			return true;
		}
	}
}
