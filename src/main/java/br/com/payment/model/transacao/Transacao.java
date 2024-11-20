package br.com.payment.model.transacao;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

import br.com.payment.model.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transacoes")
@Table(name = "transacoes")
public class Transacao {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "remetente.id")
	private Usuario remetente;

	@ManyToOne
	@JoinColumn(name = "destinatario.id")	
	private Usuario destinatario;

	private BigDecimal valor;

	private ZonedDateTime dataHora;

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		Transacao other = (Transacao) obj;
		return Objects.equals(id, other.id);
	}
}
