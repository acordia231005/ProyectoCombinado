package ies.alcores.daweb.controller;

import ies.alcores.daweb.model.Asignatura;
import ies.alcores.daweb.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/asignatura")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping
    public ResponseEntity<List<Asignatura>> all(){
        return ResponseEntity.ok(this.asignaturaService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Asignatura> findOne(@PathVariable("id") final long id){
        return this.asignaturaService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
