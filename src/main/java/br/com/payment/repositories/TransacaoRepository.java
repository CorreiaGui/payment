package br.com.payment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.payment.model.transacao.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

}
