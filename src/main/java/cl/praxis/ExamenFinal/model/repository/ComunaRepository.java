package cl.praxis.ExamenFinal.model.repository;

import cl.praxis.ExamenFinal.model.entities.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComunaRepository extends JpaRepository<Comuna, Integer> {
}
