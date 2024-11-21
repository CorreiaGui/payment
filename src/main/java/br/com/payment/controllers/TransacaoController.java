package br.com.payment.controllers;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.payment.dtos.TransacaoDto;
import br.com.payment.model.transacao.Transacao;
import br.com.payment.services.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	@Autowired
	private TransacaoService service;
	
	@PostMapping
	public ResponseEntity<Transacao> createTransacao(@RequestBody TransacaoDto transacaoDto) throws Exception{
		Transacao newTransacao = this.service.criarTransacao(transacaoDto);
		return new ResponseEntity<>(newTransacao, OK);
	}
}
