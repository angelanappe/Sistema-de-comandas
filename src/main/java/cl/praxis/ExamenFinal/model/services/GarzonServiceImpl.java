package cl.praxis.ExamenFinal.model.services;

import cl.praxis.ExamenFinal.model.entities.Garzon;
import cl.praxis.ExamenFinal.model.repository.GarzonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarzonServiceImpl implements GarzonService {

    private final GarzonRepository repository;

    public GarzonServiceImpl(GarzonRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Garzon> findAll() {
        return repository.findAll();
    }

}
