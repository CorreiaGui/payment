package br.com.payment.repositories.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.payment.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findUserByCpfCnpj(String cpfCnpj);
	
	Optional<Usuario> findUserById(Long id);
}
