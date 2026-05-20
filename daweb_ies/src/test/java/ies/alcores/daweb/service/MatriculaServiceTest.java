package ies.alcores.daweb.service;

import ies.alcores.daweb.model.Alumno;
import ies.alcores.daweb.model.Asignatura;
import ies.alcores.daweb.model.Matricula;
import ies.alcores.daweb.repository.MatriculaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class MatriculaServiceTest {

    @Mock
    private MatriculaRepository matriculaRepository;

    @InjectMocks
    private MatriculaService matriculaService;

    @Test
    void findAll_ok() {
        // Given
        Alumno alumno = new Alumno(1L, "Antonio", "García Ruiz", "ALU001");
        Asignatura asig = new Asignatura(1L, "BD", 192, null);

        Matricula m1 = new Matricula(1L, 2025, 7.5, alumno, asig);
        Matricula m2 = new Matricula(2L, 2025, 4,  alumno, asig);

        when(matriculaRepository.findAll()).thenReturn(List.of(m1, m2));

        // When
        List<Matricula> result = matriculaService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(matriculaRepository).findAll();
    }

    @Test
    void findById_ok() {
        // Given
        Alumno alumno = new Alumno(1L, "Antonio", "García Ruiz", "ALU001");
        Asignatura asig = new Asignatura(1L, "BD", 192, null);
        Matricula matricula = new Matricula(1L,2025, 7.5, alumno, asig);

        when(matriculaRepository.findById(1L)).thenReturn(Optional.of(matricula));

        // When
        Optional<Matricula> result = matriculaService.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdMat());
        verify(matriculaRepository).findById(1L);
    }

    @Test
    void findById_notFound() {
        // Given
        when(matriculaRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Optional<Matricula> result = matriculaService.findById(1L);

        // Then
        assertFalse(result.isPresent());
        verify(matriculaRepository).findById(1L);
    }
}