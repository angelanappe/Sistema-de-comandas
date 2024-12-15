package cl.praxis.ExamenFinal.model.services;

import cl.praxis.ExamenFinal.model.entities.Mesa;
import cl.praxis.ExamenFinal.model.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaServiceImpl implements MesaService {

    private final MesaRepository repository;

    public MesaServiceImpl(MesaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Mesa> findAll() {
        return repository.findAll();
    }
}
