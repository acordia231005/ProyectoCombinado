package ies.alcores.daweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Alumno {

    @Id
    private final long id;
    private final String nombre;
    private final String apellidos;
    private final String ipasen;

}
