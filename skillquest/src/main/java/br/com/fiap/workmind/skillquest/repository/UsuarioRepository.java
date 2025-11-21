package br.com.fiap.workmind.skillquest.repository;

import br.com.fiap.workmind.skillquest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Metodo customizado para buscar o Ranking ordenado por XP
    List<Usuario> findAllByOrderByPontosXPDesc();

    // METODO NOVO (Obrigatório para o Spring Security)
    // Permite ao AuthenticationService buscar o usuário pelo email
    Optional<Usuario> findByEmail(String email);
}