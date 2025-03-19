package br.com.napoleao.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.napoleao.projeto.dto.AuthenticationDTO;
import br.com.napoleao.projeto.dto.UsuarioDTO;
import br.com.napoleao.projeto.service.AuthService;
import br.com.napoleao.projeto.service.UsuarioService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Validated @RequestBody AuthenticationDTO authDto) {
        logger.info("Tentativa de login para o usuário: {}", authDto.getUsername());
        return ResponseEntity.ok(authService.login(authDto));
    }

    @PostMapping(value = "/novoUsuario")
    public ResponseEntity<?> inserirNovoUsuario(@Validated @RequestBody UsuarioDTO novoUsuario) {
        logger.info("Criando novo usuário: {}", novoUsuario.getLogin());
        usuarioService.inserirNovoUsuario(novoUsuario);
        return ResponseEntity.ok(Map.of("message", "Usuário criado com sucesso"));
    }

    @GetMapping(value = "/verificarCadastro/{uuid}")
    public ResponseEntity<?> verificarCadastro(@PathVariable("uuid") String uuid) {
        String resultado = usuarioService.verificarCadastro(uuid);
        return ResponseEntity.ok(Map.of("message", resultado));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        logger.error("Erro no controller: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Erro interno no servidor", "message", e.getMessage()));
    }
}