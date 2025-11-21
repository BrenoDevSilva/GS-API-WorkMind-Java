package br.com.fiap.workmind.skillquest.controller;

import br.com.fiap.workmind.skillquest.model.TrilhaDeAprendizagem;
import br.com.fiap.workmind.skillquest.service.TrilhaDeAprendizagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trilhas")
public class TrilhaDeAprendizagemController {

    @Autowired
    private TrilhaDeAprendizagemService trilhaService;

    @PostMapping
    public ResponseEntity<TrilhaDeAprendizagem> criarTrilha(@RequestBody @Valid TrilhaDeAprendizagem trilha) {
        TrilhaDeAprendizagem novaTrilha = trilhaService.salvarTrilha(trilha);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTrilha);
    }

    @GetMapping
    public ResponseEntity<List<TrilhaDeAprendizagem>> listarTrilhas() {
        return ResponseEntity.ok(trilhaService.buscarTodasTrilhas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaDeAprendizagem> buscarTrilha(@PathVariable Long id) {
        return ResponseEntity.ok(trilhaService.buscarTrilhaPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TrilhaDeAprendizagem>> buscarPorFoco(@RequestParam String foco) {
        return ResponseEntity.ok(trilhaService.buscarPorFoco(foco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrilhaDeAprendizagem> atualizarTrilha(@PathVariable Long id, @RequestBody @Valid TrilhaDeAprendizagem trilha) {
        trilha.setId(id);
        trilhaService.buscarTrilhaPorId(id); // Valida existência
        TrilhaDeAprendizagem atualizada = trilhaService.salvarTrilha(trilha);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTrilha(@PathVariable Long id) {
        trilhaService.deletarTrilha(id);
        return ResponseEntity.noContent().build();
    }
}