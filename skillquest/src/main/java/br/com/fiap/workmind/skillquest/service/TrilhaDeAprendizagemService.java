package br.com.fiap.workmind.skillquest.service;

import br.com.fiap.workmind.skillquest.exception.ResourceNotFoundException;
import br.com.fiap.workmind.skillquest.model.TrilhaDeAprendizagem;
import br.com.fiap.workmind.skillquest.repository.TrilhaDeAprendizagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrilhaDeAprendizagemService {

    @Autowired
    private TrilhaDeAprendizagemRepository trilhaRepository;

    public TrilhaDeAprendizagem salvarTrilha(TrilhaDeAprendizagem trilha) {
        return trilhaRepository.save(trilha);
    }

    public List<TrilhaDeAprendizagem> buscarTodasTrilhas() {
        return trilhaRepository.findAll();
    }

    public TrilhaDeAprendizagem buscarTrilhaPorId(Long id) {
        return trilhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trilha não encontrada com ID: " + id));
    }

    public List<TrilhaDeAprendizagem> buscarPorFoco(String focoPrincipal) {
        return trilhaRepository.findByFocoPrincipalContainingIgnoreCase(focoPrincipal);
    }

    public void deletarTrilha(Long id) {
        if (!trilhaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Trilha não encontrada com ID: " + id);
        }
        trilhaRepository.deleteById(id);
    }
}