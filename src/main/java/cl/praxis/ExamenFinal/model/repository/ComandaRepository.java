package cl.praxis.ExamenFinal.model.repository;

import cl.praxis.ExamenFinal.model.entities.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComandaRepository extends JpaRepository<Comanda, Integer> {
    List<Comanda> findByGarzonRut(String rut);
}
