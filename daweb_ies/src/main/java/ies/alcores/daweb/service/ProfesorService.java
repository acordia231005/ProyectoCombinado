package ies.alcores.daweb.service;

import ies.alcores.daweb.model.Profesor;
import ies.alcores.daweb.repository.ProfesorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> findAll(){
        return this.profesorRepository.findAll();
    }

    public Optional<Profesor> findById(final long id){
        return this.profesorRepository.findById(id);
    }
}
