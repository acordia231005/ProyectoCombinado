package ies.alcores.daweb.service;

import ies.alcores.daweb.model.Alumno;
import ies.alcores.daweb.repository.AlumnoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class AlumnoServiceTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoService alumnoService;

    @Test
    void findAll_ok() {
        // Given
        Alumno a1 = new Alumno(1L, "Antonio", "García Ruiz", "ALU001");
        Alumno a2 = new Alumno(2L, "María", "López Sánchez", "ALU002");

        when(alumnoRepository.findAll()).thenReturn(List.of(a1, a2));

        // When
        List<Alumno> result = alumnoService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(alumnoRepository).findAll();
    }

    @Test
    void findById_ok() {
        // Given
        Alumno alumno = new Alumno(1L, "Antonio", "García Ruiz", "ALU001");

        when(alumnoRepository.findById(1L)).thenReturn(Optional.of(alumno));

        // When
        Optional<Alumno> result = alumnoService.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Antonio", result.get().getNombre());
        verify(alumnoRepository).findById(1L);
    }

    @Test
    void findById_notFound() {
        // Given
        when(alumnoRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Optional<Alumno> result = alumnoService.findById(1L);

        // Then
        assertFalse(result.isPresent());
        verify(alumnoRepository).findById(1L);
    }
}
