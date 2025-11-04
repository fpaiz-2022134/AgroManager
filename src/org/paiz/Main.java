package org.paiz;

import org.paiz.controller.GestorEquipos;
import org.paiz.view.InterfazConsola;

/**
 * Clase Aplicacion (Main)
 * @author Franco Paiz
 * @version 1.0
 */
public class Main {
    /**
     * Método principal que inicia la aplicación
     * Demuestra el patrón MVC:
     * - Crea el Controlador (GestorEquipos)
     * - Crea la Vista (InterfazConsola)
     * - El Modelo (RepositorioEquipos) es creado por el Controlador
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║                                                ║");
        System.out.println("║     SISTEMA DE GESTIÓN AGRO-TECNOLÓGICA       ║");
        System.out.println("║                                                ║");
        System.out.println("║           CC2008 - POO 2025                   ║");
        System.out.println("║                                                ║");
        System.out.println("╚════════════════════════════════════════════════╝");

        try {
            // Patrón MVC: Inicialización
            // 1. Crear el Controlador
            GestorEquipos gestor = new GestorEquipos();

            // 2. Crear la Vista pasándole el Controlador
            InterfazConsola interfaz = new InterfazConsola(gestor);

            // 3. Iniciar la aplicación
            interfaz.iniciar();

        } catch (Exception e) {
            // Manejo de errores críticos (permitido en main)
            System.err.println("\n[Error Crítico] El sistema ha encontrado un error:");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}