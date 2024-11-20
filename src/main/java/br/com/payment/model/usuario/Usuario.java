package br.com.payment.model.usuario;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.util.Objects.hash;

import java.math.BigDecimal;
import java.util.Objects;

import br.com.payment.enums.TipoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(unique = true)
	private String cpfCnpj;

	@Column(unique = true)
	private String email;

	@Enumerated(STRING)
	private TipoUsuario tipo;
	
	private String nome;

	private BigDecimal saldo;
	
	private String senha;

	@Override
	public int hashCode() {
		return hash(cpfCnpj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Usuario other = (Usuario) obj;
		return Objects.equals(cpfCnpj, other.cpfCnpj);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [cpfCnpj=");
		builder.append(cpfCnpj);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", senha=");
		builder.append(senha);
		builder.append(", email=");
		builder.append(email);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append("]");
		return builder.toString();
	}
}
