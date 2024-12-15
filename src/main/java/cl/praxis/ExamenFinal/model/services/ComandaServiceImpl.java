package cl.praxis.ExamenFinal.model.services;

import cl.praxis.ExamenFinal.model.entities.Comanda;
import cl.praxis.ExamenFinal.model.repository.ComandaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComandaServiceImpl implements ComandaService {

    private final ComandaRepository repository;

    public ComandaServiceImpl(ComandaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comanda> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Comanda> findAllByGarzonRut(String rut) {
        return repository.findByGarzonRut(rut);
    }

    @Override
    public Optional<Comanda> findById(int id) {
        return repository.findById(id);
    }

}
