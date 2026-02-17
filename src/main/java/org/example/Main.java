package org.example;

import org.example.exception.TareaYaEliminadaException;
import org.example.service.TareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {


        TareaService tareaService = new TareaService();

        tareaService.registrarTarea(2,"Se debe de dar seguimiento a las solicitudes recibidas por el cliente");
    try {
            tareaService.eliminarTarea(2);
            tareaService.eliminarTarea(2);
        } catch (TareaYaEliminadaException e) {
            log.info("Error en la aplicacion");
            log.debug("FALLO POR: {}", e.getMessage());
        }
    }

}

