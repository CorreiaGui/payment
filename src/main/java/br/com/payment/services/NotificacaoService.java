package br.com.payment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.payment.dtos.NotificacaoDto;
import br.com.payment.model.usuario.Usuario;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class NotificacaoService {

	@Autowired
	private RestTemplate restTemplate;

	public void enviarNotificacao(Usuario usuario, String mensagem) throws Exception {
		String email = usuario.getEmail();
		NotificacaoDto notificacao = new NotificacaoDto(email, mensagem);
		ResponseEntity<String> response = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificacao, String.class);
		if(!HttpStatus.OK.equals(response.getStatusCode())) {
			log.error("Erro no serviço de notificação");
			throw new Exception("Serviço notificação fora do ar");	
		}
	}
}
