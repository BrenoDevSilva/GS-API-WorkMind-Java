package br.com.fiap.workmind.skillquest.repository;

import br.com.fiap.workmind.skillquest.model.TrilhaDeAprendizagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrilhaDeAprendizagemRepository extends JpaRepository<TrilhaDeAprendizagem, Long> {
    // Método para filtrar trilhas por foco (ex: "Inteligencia Artificial")
    List<TrilhaDeAprendizagem> findByFocoPrincipalContainingIgnoreCase(String focoPrincipal);
}