package app.vercel.leonanthomaz.springcurriculum.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa um usuário do sistema.
 */
@Entity
@Table(name = "tb_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** O nome do usuário. */
    private String nome;

    /** O e-mail do usuário. */
    private String email;

    /** O número de telefone do usuário. */
    private String telefone;

    /** O endereço do usuário. */
    private String endereco;

    /** A data de nascimento do usuário. */
    private LocalDate dataNascimento;

    /** O gênero do usuário. */
    private String genero;

    /** A data de cadastro do usuário no sistema. */
    private LocalDateTime dataCadastro;

    /** A data do último acesso do usuário ao sistema. */
    private LocalDateTime ultimoAcesso;

    /** A lista de experiências profissionais do usuário. */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Experiencia> experiencias;

    /** A lista de formações acadêmicas do usuário. */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Formacao> formacoesAcademicas;

    /** A lista de habilidades do usuário. */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Habilidade> habilidades;

    /** A lista de certificações do usuário. */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Certificacao> certificacoes;

    /** A lista de idiomas que o usuário domina. */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Idioma> idiomas;

}
