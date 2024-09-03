package br.com.napoleao.projeto.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.napoleao.projeto.dto.UsuarioDTO;
import br.com.napoleao.projeto.entity.UsuarioEntity;
import br.com.napoleao.projeto.entity.UsuarioVerificadorEntity;
import br.com.napoleao.projeto.entity.enums.TipoSituacaoUsuario;
import br.com.napoleao.projeto.repository.UsuarioRepository;
import br.com.napoleao.projeto.repository.UsuarioVerificadorRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioVerificadorRepository usuarioVerificadorRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	public List<UsuarioDTO> listAll() {
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(UsuarioDTO::new).toList();
	}

	public void insert(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuarioRepository.save(usuarioEntity);
	}

	public void insertNovoUsuario(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuarioEntity.setSituacao(TipoSituacaoUsuario.PENDENTE);
		usuarioEntity.setId(null);

		usuarioRepository.save(usuarioEntity);

		UsuarioVerificadorEntity verificador = new UsuarioVerificadorEntity();

		verificador.setUsuario(usuarioEntity);
		verificador.setUuid(UUID.randomUUID());
		verificador.setDataExpiracao(Instant.now().plusMillis(900000));

		usuarioVerificadorRepository.save(verificador);

		// TODO deixar a url mais dinamica para trocar entre producao e desenvolvimento

		String assunto = "<!DOCTYPE html>" + "<html lang='pt-BR'>" + "<head>" + "<meta charset='UTF-8'>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "<title>Ativação de Conta</title>" + "</head>" + "<body>" + "<p>Olá,</p>"
				+ "<p>Você está recebendo este email porque se cadastrou em nosso serviço. Para ativar sua conta, por favor, clique no link abaixo:</p>"
				+ "<p><a href='http://localhost:3000/auth/confirmuser?uuid=" + verificador.getUuid()
				+ "'>Clique aqui para ativar sua conta</a></p>"
				+ "<p>Se você não se cadastrou em nosso serviço, por favor, ignore este email.</p>" + "<p>Obrigado,<br>"
				+ letraMais(usuario.getNome()) + "</p>" + "</body>" + "</html>";

		emailService.enviarEmailText(usuario.getEmail(), "Novo Usuario Cadastrado", assunto);
	}

	public String verificarCadastro(String uuid) {

		UsuarioVerificadorEntity usuarioVerificacao = usuarioVerificadorRepository.findByUuid(UUID.fromString(uuid))
				.get();
		if (usuarioVerificacao != null) {
			if (usuarioVerificacao.getDataExpiracao().compareTo(Instant.now()) >= 0) {
				UsuarioEntity u = usuarioVerificacao.getUsuario();
				u.setSituacao(TipoSituacaoUsuario.ATIVO);

				usuarioRepository.save(u);

				// TODO deixar a url mais dinamica para trocar entre producao e desenvolvimento

				String assunto = "<!DOCTYPE html>" + "<html lang='pt-BR'>" + "<head>" + "<meta charset='UTF-8'>"
						+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
						+ "<title>Conta Ativada</title>" + "</head>" + "<body>" + "<p>Olá,</p>"
						+ "<p>Sua conta foi ativada com sucesso! Agora você pode acessar todos os recursos do nosso serviço.</p>"
						+ "<p>Se você não ativou sua conta, por favor, entre em contato com nosso suporte.</p>"
						+ "<p>Obrigado,<br>" + letraMais(usuarioVerificacao.getUsuario().getNome()) + "</p>" + "</body>"
						+ "</html>";

				emailService.enviarEmailText(usuarioVerificacao.getUsuario().getEmail(), "Novo Usuario Cadastrado",
						assunto);

				return "Usuario Verificado";
			} else {
				usuarioVerificadorRepository.delete(usuarioVerificacao);
				return "Tempo de verificacao expirado";
			}

		} else {
			return "Usuario nao verificado";
		}

	}

	public UsuarioDTO update(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return new UsuarioDTO(usuarioRepository.save(usuarioEntity));
	}

	public void delete(Long id) {
		UsuarioEntity usuario = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuario);
	}

	public UsuarioDTO buscarPorId(Long id) {
		return new UsuarioDTO(usuarioRepository.findById(id).get());
	}

	public String letraMais(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}
		String saida = input.substring(0, 1).toUpperCase() + input.substring(1);
		return saida;
	}

}
