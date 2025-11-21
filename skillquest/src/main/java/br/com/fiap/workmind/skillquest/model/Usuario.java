package br.com.fiap.workmind.skillquest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_GS_SQ_USUARIO")
@Getter @Setter @NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_GS_SQ_USUARIO")
    @SequenceGenerator(name = "SQ_GS_SQ_USUARIO", sequenceName = "SQ_GS_SQ_USUARIO", allocationSize = 1)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nome;

    @Email(message = "O email deve ser válido.")
    @NotBlank(message = "O email é obrigatório.")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    @NotBlank(message = "A área de atuação é obrigatória.")
    @Size(max = 100)
    @Column(name = "area_atuacao")
    private String areaAtuacao;

    @NotNull(message = "O nível de carreira é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_carreira")
    private NivelCarreira nivelCarreira;

    @Min(value = 0, message = "Os pontos de XP não podem ser negativos.")
    @Column(name = "pontos_xp")
    private Integer pontosXP = 0;

    @NotNull(message = "O status de bem-estar é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(name = "status_bem_estar")
    private StatusBemEstar statusBemEstar;
}