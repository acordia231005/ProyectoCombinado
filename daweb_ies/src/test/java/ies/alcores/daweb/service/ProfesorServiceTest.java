package ies.alcores.daweb.service;

import ies.alcores.daweb.model.Profesor;
import ies.alcores.daweb.repository.ProfesorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ProfesorServiceTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorService profesorService;

    @Test
    void findAll_ok() {
        // Given
        Profesor p1 = new Profesor(1L, "Juan", "Pérez López", "BD");
        Profesor p2 = new Profesor(2L, "Ana", "Gómez Ruiz", "Programación");

        when(profesorRepository.findAll()).thenReturn(List.of(p1, p2));

        // When
        List<Profesor> result = profesorService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(profesorRepository).findAll();
    }

    @Test
    void findById_ok() {
        // Given
        Profesor profesor = new Profesor(1L, "Juan", "Pérez López", "BD");

        when(profesorRepository.findById(1L)).thenReturn(Optional.of(profesor));

        // When
        Optional<Profesor> result = profesorService.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Juan", result.get().getNombre());
        verify(profesorRepository).findById(1L);
    }

    @Test
    void findById_notFound() {
        // Given
        when(profesorRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Optional<Profesor> result = profesorService.findById(1L);

        // Then
        assertFalse(result.isPresent());
        verify(profesorRepository).findById(1L);
    }
}