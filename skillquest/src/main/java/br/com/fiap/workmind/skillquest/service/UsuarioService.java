package br.com.fiap.workmind.skillquest.service;

import br.com.fiap.workmind.skillquest.exception.ResourceNotFoundException;
import br.com.fiap.workmind.skillquest.model.TrilhaDeAprendizagem;
import br.com.fiap.workmind.skillquest.model.Usuario;
import br.com.fiap.workmind.skillquest.repository.TrilhaDeAprendizagemRepository;
import br.com.fiap.workmind.skillquest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TrilhaDeAprendizagemRepository trilhaRepository;

    // --- CRUD Básico ---

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // METODO NOVO (Obrigatório para o Controller de Login)
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com email: " + email));
    }

    // --- Regras de Negócio Específicas (WorkMind SkillQuest) ---

    // 1. Ranking de Gamificação
    public List<Usuario> buscarRankingPorXP() {
        return usuarioRepository.findAllByOrderByPontosXPDesc();
    }

    // 2. MOCK DA IA: Simula a sugestão de uma missão baseada no perfil
    public TrilhaDeAprendizagem sugerirMissao(Long usuarioId) {
        Usuario usuario = buscarUsuarioPorId(usuarioId);

        // Lógica simulada: Pega todas as trilhas e retorna a primeira que encontrar
        // que tenha a ver com a área de atuação (filtro simples) ou uma aleatória.
        // Em produção, isso chamaria a API C# via RestTemplate.
        List<TrilhaDeAprendizagem> trilhas = trilhaRepository.findAll();

        if (trilhas.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma trilha disponível no catálogo para sugerir.");
        }

        // Simulação: Retorna a primeira trilha da lista como "Recomendação da IA"
        return trilhas.get(0);
    }
}