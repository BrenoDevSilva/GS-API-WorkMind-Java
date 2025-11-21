package br.com.fiap.workmind.skillquest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_GS_SQ_TRILHA")
@Getter @Setter @NoArgsConstructor
public class TrilhaDeAprendizagem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_GS_SQ_TRILHA")
    @SequenceGenerator(name = "SQ_GS_SQ_TRILHA", sequenceName = "SQ_GS_SQ_TRILHA", allocationSize = 1)
    private Long id;

    @NotBlank(message = "O nome da trilha é obrigatório.")
    @Size(max = 150)
    private String nome;

    @NotBlank(message = "A descrição da trilha é obrigatória.")
    private String descricao;

    @NotNull(message = "O nível da trilha é obrigatório.")
    @Enumerated(EnumType.STRING)
    private NivelTrilha nivel;

    @Min(value = 1, message = "A carga horária deve ser de pelo menos 1 hora.")
    @NotNull(message = "A carga horária é obrigatória.")
    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    @NotBlank(message = "O foco principal (competência) é obrigatório.")
    @Size(max = 100)
    @Column(name = "foco_principal")
    private String focoPrincipal;

    @Min(value = 1, message = "A recompensa XP deve ser positiva.")
    @NotNull(message = "A recompensa XP é obrigatória.")
    @Column(name = "recompensa_xp")
    private Integer recompensaXP;
}