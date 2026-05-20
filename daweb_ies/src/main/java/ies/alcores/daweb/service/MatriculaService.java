package ies.alcores.daweb.service;

import ies.alcores.daweb.model.Matricula;
import ies.alcores.daweb.repository.MatriculaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    public List<Matricula> findAll(){
        return this.matriculaRepository.findAll();
    }

    public Optional<Matricula> findById(final long id){
        return this.matriculaRepository.findById(id);
    }
}
