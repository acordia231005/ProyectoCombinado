package ies.alcores.daweb.service;

import ies.alcores.daweb.model.Asignatura;
import ies.alcores.daweb.repository.AsignaturaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AsignaturaService {

    @Autowired
    private final AsignaturaRepository asignaturaRepository;

    public List<Asignatura> findAll(){
        return this.asignaturaRepository.findAll();
    }

    public Optional<Asignatura> findById(final long id){
        return this.asignaturaRepository.findById(id);
    }
}
