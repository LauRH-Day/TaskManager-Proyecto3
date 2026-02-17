package org.example.service;

import org.example.exception.OperacionInvalidaException;
import org.example.exception.TareaNoEncontradaException;
import org.example.model.Tarea;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;


public class TareaService {
    private static final Logger log = LoggerFactory.getLogger(TareaService.class);
    private final List<Tarea> listaTareas = new ArrayList<>();
    private final ValidacionService validacionService = new ValidacionService();

    // 1. REGISTRAR TAREA
    public void registrarTarea(int id, String descripcion) {
        log.info("Intentando registrar tarea: ID {} - {}", id, descripcion);

        // Uso de excepciones NO VERIFICADAS (dentro de validaciones)
        validaciones(id, descripcion);

        Tarea nuevaTarea = new Tarea(id, descripcion);
        listaTareas.add(nuevaTarea);

        log.info("Tarea registrada con éxito en el sistema");
    }

    // 2. ELIMINAR TAREA
    public void eliminarTarea(int id) throws TareaNoEncontradaException {
        log.info("Petición para eliminar tarea ID: {}", id);

        Tarea tarea = getTareaById(id); // Reutilizamos el buscador
        listaTareas.remove(tarea);

        log.info("Tarea con ID {} eliminada satisfactoriamente", id);
    }

    // 3. BUSCAR POR ID
    public Tarea getTareaById(int id) throws TareaNoEncontradaException {
        log.debug("Buscando tarea con ID {} en la lista", id);

        for (Tarea t : listaTareas) {
            if (t.getId() == id) {
                return t;
            }
        }

        // Si no la encuentra, lanza la excepción verificada y la registra en el log
        log.error("Error: Tarea con ID {} no existe en la base de datos", id);
        throw new TareaNoEncontradaException("La tarea con ID " + id + " no fue encontrada.");
    }

    // 4. MARCAR COMO COMPLETADA
    public void marcarCompletada(int id) throws TareaNoEncontradaException {
        Tarea tarea = getTareaById(id);

        if (tarea.isCompletada()) {
            log.warn("La tarea {} ya estaba completada. Operación redundante.", id);
            throw new OperacionInvalidaException("La tarea ya se encuentra en estado completado.");
        }

        tarea.setCompletada(true);
        log.info("Estado de tarea {} cambiado a: COMPLETADA", id);
    }

    // 5. LISTAR TODAS
    public List<Tarea> listarTodas() {
        log.debug("Consultando todas las tareas. Total actual: {}", listaTareas.size());
        return new ArrayList<>(listaTareas); //
    }

    // MÉTODO DE VALIDACIONES
    private void validaciones(int id, String descripcion) {
        validacionService.validarId(id);
        validacionService.validarDescripcion(descripcion);
    }
}