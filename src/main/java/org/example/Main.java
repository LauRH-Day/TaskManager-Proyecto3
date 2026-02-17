package org.example;

import org.example.exception.TareaYaEliminadaException;
import org.example.service.TareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.service.TareaService;
import org.example.exception.TareaNoEncontradaException;
import org.example.exception.OperacionInvalidaException;

import java.util.Scanner;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        TareaService gestor = new TareaService();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        System.out.println("========================================");
        System.out.println("                BIENVENIDO              ");
        System.out.println("========================================");

        do {
            System.out.println("\n----------- MENÚ DE TAREAS -----------");
            System.out.println(" 1. ➕ Agregar nueva tarea");
            System.out.println(" 2. 📋 Listar todas las tareas");
            System.out.println(" 3. ✅ Marcar tarea como completada");
            System.out.println(" 4. 🗑️  Eliminar una tarea");
            System.out.println(" 5. 🚪 Salir");
            System.out.println("--------------------------------------");
            System.out.print("👉 Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> {
                        System.out.println("\n[NUEVA TAREA]");
                        System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Descripción: "); String desc = sc.nextLine();
                        gestor.registrarTarea(id, desc);
                        System.out.println("✨ Tarea agregada exitosamente.");
                    }
                    case 2 -> {
                        System.out.println("\n[LISTADO ACTUAL]");
                        gestor.listarTodas().forEach(t -> System.out.println(" - " + t));
                    }
                    case 3 -> {
                        System.out.println("\n[COMPLETAR TAREA]");
                        System.out.print("ID a completar: "); int id = Integer.parseInt(sc.nextLine());
                        gestor.marcarCompletada(id);
                        System.out.println("✔️ Tarea actualizada.");
                    }
                    case 4 -> {
                        System.out.println("\n[ELIMINAR TAREA]");
                        System.out.print("ID a eliminar: "); int id = Integer.parseInt(sc.nextLine());
                        gestor.eliminarTarea(id);
                        System.out.println("❌ Tarea removida.");
                    }
                    case 5 -> log.info("El usuario ha decidido salir.");
                    default -> System.out.println("⚠️ Opción no válida. Intente del 1 al 5.");
                }
            } catch (NumberFormatException e) {
                log.warn("Entrada no numérica.");
                System.out.println("❗ Error: Por favor, ingrese un número.");
            } catch (TareaNoEncontradaException | OperacionInvalidaException e) {
                log.error("Error controlado: {}", e.getMessage());
                System.out.println("❗ Error: " + e.getMessage());
            } catch (Exception e) {
                log.error("Error crítico: ", e);
                System.out.println("❗ Error inesperado.");
            } finally {
                System.out.println("... proceso terminado.");
            }
        } while (opcion != 5);

        System.out.println("\n========================================");
        System.out.println("     Saliendo del sistema... ¡Adiós!");
        System.out.println("========================================");
        sc.close();
    }
}