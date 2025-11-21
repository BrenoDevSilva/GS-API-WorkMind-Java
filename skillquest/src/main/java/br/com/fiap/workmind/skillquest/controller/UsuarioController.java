package br.com.fiap.workmind.skillquest.controller;

// Imports existentes
import br.com.fiap.workmind.skillquest.model.TrilhaDeAprendizagem;
import br.com.fiap.workmind.skillquest.model.Usuario;
import br.com.fiap.workmind.skillquest.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// --- NOVOS IMPORTS PARA SEGURANÇA ---
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import br.com.fiap.workmind.skillquest.model.LoginRequest;
import br.com.fiap.workmind.skillquest.model.LoginResponse;
import br.com.fiap.workmind.skillquest.security.TokenService;
// --- FIM DOS NOVOS IMPORTS ---

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // --- NOVAS INJEÇÕES DE DEPENDÊNCIA ---
    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    // --- FIM DAS NOVAS INJEÇÕES ---


    // --- NOVO ENDPOINT DE LOGIN ---
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest credenciais) {
        // Autentica o usuário no Spring Security
        var usernamePassword = new UsernamePasswordAuthenticationToken(credenciais.email(), credenciais.senha());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        // Se autenticado, busca o usuário completo para gerar o token
        Usuario usuarioAutenticado = usuarioService.buscarUsuarioPorEmail(credenciais.email());

        // Gera o Token
        String token = tokenService.generateToken(usuarioAutenticado);

        return ResponseEntity.ok(new LoginResponse(token));
    }
    // --- FIM DO ENDPOINT DE LOGIN ---


    // --- MÉTODO CRIAR USUÁRIO ATUALIZADO (com Criptografia) ---
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid Usuario usuario) {
        // Criptografa a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
    // --- FIM DA ATUALIZAÇÃO ---


    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.buscarTodosUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
        usuarioService.buscarUsuarioPorId(id); // Valida se o ID existe
        usuario.setId(id); // Garante que estamos atualizando o ID correto

        // Opcional: Criptografar a senha se ela for alterada no PUT
        // Se a senha vier nula ou vazia no JSON, talvez você queira manter a antiga.
        // Para simplificar, vamos criptografar o que vier (ou manter a lógica do Datarium)
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        } else {
            // Se a senha veio vazia, busca a senha antiga no banco para não apagá-la
            Usuario usuarioExistente = usuarioService.buscarUsuarioPorId(id);
            usuario.setSenha(usuarioExistente.getSenha());
        }

        Usuario usuarioAtualizado = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // --- Endpoints Específicos (WorkMind SkillQuest) ---

    @GetMapping("/ranking")
    public ResponseEntity<List<Usuario>> verRanking() {
        return ResponseEntity.ok(usuarioService.buscarRankingPorXP());
    }

    @GetMapping("/{id}/sugerir-missao")
    public ResponseEntity<TrilhaDeAprendizagem> sugerirMissao(@PathVariable Long id) {
        // Este é o endpoint que simula a IA
        TrilhaDeAprendizagem sugestao = usuarioService.sugerirMissao(id);
        return ResponseEntity.ok(sugestao);
    }
}