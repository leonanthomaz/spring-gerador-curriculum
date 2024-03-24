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
 * Representa a certificação de um usuário.
 */
@Entity
@Table(name = "tb_certificacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Certificacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome da certificação. */
    private String nome;
    /** Nome da instituição da certificação. */
    private String instituicao;
    /** Data de conclusão da certificação. */
    private LocalDate dataConclusao;
    /**
     * O usuário associado a esta certificação.
     * Muitas certificações pertence a um único usuário.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;
}
