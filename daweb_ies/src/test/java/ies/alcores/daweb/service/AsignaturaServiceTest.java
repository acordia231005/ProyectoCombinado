package ies.alcores.daweb.service;

import ies.alcores.daweb.model.Asignatura;
import ies.alcores.daweb.model.Profesor;
import ies.alcores.daweb.repository.AsignaturaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class AsignaturaServiceTest {

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @InjectMocks
    private AsignaturaService asignaturaService;

    @Test
    void findAll_ok(){
        //Given
        Profesor profe1 = new Profesor(1,"Profe_TEST", "Martín Martín", "Informática PES");
        Asignatura asig1 = new Asignatura(1, "BD_TEST", 192, profe1);
        Asignatura asig2 = new Asignatura(2, "DAWEB_TEST", 168, profe1);

        //When
        when(this.asignaturaRepository.findAll()).thenReturn(List.of(asig1, asig2));
        List<Asignatura> result = this.asignaturaService.findAll();

        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(this.asignaturaRepository, times(1)).findAll();
    }

    @Test
    void findOne_ok(){
        //Given
        Profesor profe1 = new Profesor(1,"Profe_TEST", "Martín Martín", "Informática PES");
        Asignatura asig1 = new Asignatura(1, "BD_TEST", 192, profe1);

        //When
        when(this.asignaturaRepository.findById(1L)).thenReturn(Optional.of(asig1));
        Optional<Asignatura> result = this.asignaturaService.findById(1L);

        //Then
        assertTrue(result.isPresent());
        assertEquals("BD_TEST", result.get().getNombre());
        verify(this.asignaturaRepository, times(1)).findById(1L);
    }

    @Test
    void findOne_notFound(){
        //Given

        //When
        when(this.asignaturaRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Asignatura> result = this.asignaturaService.findById(1L);

        //Then
        assertFalse(result.isPresent());
        verify(this.asignaturaRepository, times(1)).findById(1L);
    }
}
