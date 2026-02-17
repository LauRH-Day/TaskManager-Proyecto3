package org.example.service;

import org.example.exception.OperacionInvalidaException;

public class ValidacionService{

    public void validarId(int id) {
        if (id <= 0) throw new OperacionInvalidaException("El ID debe ser un número positivo");
    }

    public void validarDescripcion(String desc) {
        if (desc == null || desc.trim().isEmpty()) {
            throw new OperacionInvalidaException("La descripción no puede estar vacía");
        }
    }
}
