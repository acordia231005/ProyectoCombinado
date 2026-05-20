package ies.alcores.daweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Matricula {

    @Id
    private long idMat;
    private int curso;
    private double notaMedia;

    @ManyToOne
    @JoinColumn(name = "id_alum")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_asig")
    private Asignatura asignatura;

}
