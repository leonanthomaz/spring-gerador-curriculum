package app.vercel.leonanthomaz.springcurriculum.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_formacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Formacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String instituicao;
    private String curso;
    private String nivel;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;
    private String grauObtido;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;
}
