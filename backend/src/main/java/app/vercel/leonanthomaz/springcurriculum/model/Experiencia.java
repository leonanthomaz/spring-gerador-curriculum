package app.vercel.leonanthomaz.springcurriculum.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Representa a experiência profissional de um usuário.
 */
@Entity
@Table(name = "tb_experiencia")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Experiencia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** O cargo ocupado durante a experiência. */
    private String cargo;

    /** O nome da empresa onde a experiência ocorreu. */
    private String empresa;

    /** A data de início da experiência. */
    private LocalDate dataInicio;

    /** A data de término da experiência. */
    private LocalDate dataTermino;

    /** Uma descrição da experiência profissional. */
    private String descricao;

    /**
     * O usuário associado a esta experiência.
     * Uma experiência pertence a um único usuário.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

}
