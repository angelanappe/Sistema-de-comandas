package cl.praxis.ExamenFinal.model.services;

import cl.praxis.ExamenFinal.model.entities.Comuna;
import cl.praxis.ExamenFinal.model.repository.ComunaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunaServiceImpl implements ComunaService {

    private final ComunaRepository repository;

    public ComunaServiceImpl(ComunaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comuna> findAll() {
        return repository.findAll();
    }
}
