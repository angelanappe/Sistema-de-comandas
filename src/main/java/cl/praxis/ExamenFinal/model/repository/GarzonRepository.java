package cl.praxis.ExamenFinal.model.repository;

import cl.praxis.ExamenFinal.model.entities.Comanda;
import cl.praxis.ExamenFinal.model.entities.Garzon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GarzonRepository extends JpaRepository<Garzon, Integer> {

}
