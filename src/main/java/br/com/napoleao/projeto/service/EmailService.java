package br.com.napoleao.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String remetente;

	public String enviarEmailText(String destinatario, String assunto, String mensagem) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(remetente);
			mailMessage.setTo(destinatario);
			mailMessage.setSubject(assunto);
			mailMessage.setText(mensagem);
			javaMailSender.send(mailMessage);
			return "Email enviado";
		} catch (Exception e) {
			return "Erro ao tentar enviar email " + e.getMessage();
		}
	}

}
