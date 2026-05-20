package ies.alcores.daweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Asignatura {

    @Id
    private final long id;
    private final String nombre;
    private final int horas;

    @ManyToOne
    @JoinColumn(name = "imparte")
    private final Profesor profesor;

}
