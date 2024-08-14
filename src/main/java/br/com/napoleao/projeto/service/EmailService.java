package br.com.napoleao.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.Address;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String remetente;

	public String enviarEmailText(String destinatario, String assunto, String mensagem) {
		try {
			// Nao aceita html
//			SimpleMailMessage mailMessage = new SimpleMailMessage();
//			mailMessage.setFrom(remetente);
//			mailMessage.setTo(destinatario);
//			mailMessage.setSubject(assunto);
//			mailMessage.setText(mensagem);
//			javaMailSender.send(mailMessage);

			MimeMessage mailSend = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mailSend, true);

			helper.setFrom(remetente);
			helper.setTo(destinatario);
			helper.setSubject(assunto);
			helper.setText(mensagem, true);
			javaMailSender.send(mailSend);
			

			return "Email enviado";
		} catch (Exception e) {
			return "Erro ao tentar enviar email " + e.getMessage();
		}
	}

}
