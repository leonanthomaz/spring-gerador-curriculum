package app.vercel.leonanthomaz.springcurriculum.repository;

import app.vercel.leonanthomaz.springcurriculum.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepository extends JpaRepository<Usuario, Long> {}