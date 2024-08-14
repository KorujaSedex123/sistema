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

		// TODO - Enviar Email para verificar conta

		emailService.enviarEmailText(usuario.getEmail(), "Novo Usuario Cadastrado",
				"Voce esta recebendo um email de cadastro, o numero para validacao e " + verificador.getUuid());
	}

	public String verificarCadastro(String uuid) {

		UsuarioVerificadorEntity usuarioVerificacao = usuarioVerificadorRepository.findByUuid(UUID.fromString(uuid))
				.get();
		if (usuarioVerificacao != null) {
			if (usuarioVerificacao.getDataExpiracao().compareTo(Instant.now()) >= 0) {
				UsuarioEntity u = usuarioVerificacao.getUsuario();
				u.setSituacao(TipoSituacaoUsuario.ATIVO);

				usuarioRepository.save(u);

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

}
