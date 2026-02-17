package org.example.service;

import org.example.exception.OperacionInvalidaException;
import org.example.exception.TareaNoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TareaServiceTest {

    private TareaService tareaService;

    @BeforeEach
    void setUp() {
        // Inicializamos el servicio antes de cada test para tener una lista limpia
        tareaService = new TareaService();
    }

    @Test
    @DisplayName("Prueba de Creación: Debería agregar una tarea con éxito")
    void testCrearTarea() {
        // Ejecución
        tareaService.registrarTarea(1, "Estudiar para el examen");

        // Verificación
        assertEquals(1, tareaService.listarTodas().size(), "La lista debería tener 1 tarea");
        assertEquals("Estudiar para el examen", tareaService.getTareaById(1).getDescripcion());
    }

    @Test
    @DisplayName("Prueba de Eliminación: Debería borrar una tarea existente")
    void testEliminarTarea() throws TareaNoEncontradaException {
        // Preparación
        tareaService.registrarTarea(10, "Tarea para borrar");

        // Ejecución
        tareaService.eliminarTarea(10);

        // Verificación
        assertTrue(tareaService.listarTodas().isEmpty(), "La lista debería estar vacía tras eliminar");
    }

    @Test
    @DisplayName("Validación de Excepción: Lanzar error si la tarea a eliminar no existe")
    void testEliminarTareaInexistenteLanzaExcepcion() {
        // Verificamos que al intentar borrar el ID 999 se lance TareaNoEncontradaException
        assertThrows(TareaNoEncontradaException.class, () -> {
            tareaService.eliminarTarea(999);
        }, "Debería lanzar TareaNoEncontradaException");
    }

    @Test
    @DisplayName("Validación de Excepción: Lanzar error si el ID es negativo")
    void testRegistrarTareaIdInvalidoLanzaExcepcion() {
        // Verificamos que se lance la excepción NO VERIFICADA (OperacionInvalidaException)
        assertThrows(OperacionInvalidaException.class, () -> {
            tareaService.registrarTarea(-5, "Descripción válida");
        }, "Debería lanzar OperacionInvalidaException por ID negativo");
    }
}