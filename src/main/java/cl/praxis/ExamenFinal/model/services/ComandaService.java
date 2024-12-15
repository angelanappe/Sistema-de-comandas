package cl.praxis.ExamenFinal.model.services;

import cl.praxis.ExamenFinal.model.entities.Comanda;

import java.util.List;
import java.util.Optional;

public interface ComandaService {
    List<Comanda> findAll();
    List<Comanda> findAllByGarzonRut(String rut);
    Optional<Comanda> findById(int id);
}
