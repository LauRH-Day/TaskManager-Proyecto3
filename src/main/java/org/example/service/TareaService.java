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
    private final List<Tarea> tareas = new ArrayList<>();

    private final ValidacionService validacionService = new ValidacionService();


    public void registrarTarea(int id, String descripcion) {
        log.info("Intentando registrar tarea: ID {}, Desc: {}", id, descripcion);
        validaciones(id, descripcion);

        Tarea nueva = new Tarea(id, descripcion);
        tareas.add(nueva);
        log.info("Tarea registrada con éxito");
    }

    public Tarea getTareaById(int id) {
        log.info("Buscando tarea con ID: {}", id);
        return tareas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Tarea con ID {} no encontrada", id);
                    return new TareaNoEncontradaException("La tarea no existe");
                });
    }

    public void marcarComoCompletada(int id) {
        Tarea tarea = getTareaById(id);
        if (tarea.isCompletada()) {
            log.warn("La tarea {} ya estaba completada", id);
            throw new OperacionInvalidaException("La tarea ya fue completada previamente");
        }
        tarea.setCompletada(true);
        log.info("Tarea {} marcada como completada", id);
    }

    public void eliminarTarea(int id) {
        Tarea tarea = getTareaById(id);
        tareas.remove(tarea);
        log.info("Tarea {} eliminada del sistema", id);
    }

    public List<Tarea> listarTodas() {
        log.debug("Listando todas las tareas. Total: {}", tareas.size());
        return new ArrayList<>(tareas);
    }

    private void validaciones(int id, String descripcion) {
        validacionService.validarId(id);
        validacionService.validarDescripcion(descripcion);
    }
}