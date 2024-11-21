package br.com.payment.dtos;

import java.math.BigDecimal;

import br.com.payment.enums.TipoUsuario;

public record UsuarioDto (String nome, BigDecimal saldo, String email, String senha, TipoUsuario tipoUsuario, String documento){

}
