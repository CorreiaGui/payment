package br.com.payment.dtos;

import java.math.BigDecimal;

public record TransacaoDto(Long remetenteId, Long destinatarioId, BigDecimal valor) {

}
