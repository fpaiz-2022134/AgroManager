package org.paiz.view;

import org.paiz.controller.GestorEquipos;
import org.paiz.interfaces.IAuditable;
import org.paiz.interfaces.IEjecutable;
import org.paiz.interfaces.IMedible;
import org.paiz.model.*;

import java.util.List;
import java.util.Scanner;

/**
 * Clase InterfazConsola (vista en patrón MVC)
 * Maneja toda la interacción con el usuario
 * @author Franco Paiz
 * * @version 1.0
 */
public class InterfazConsola {
    private GestorEquipos gestor;
    private Scanner lector;

    /**
     * Constructor que recibe el gestor (controlador)
     * @param gestor el controlador del sistema
     */
    public InterfazConsola(GestorEquipos gestor) {
        this.gestor = gestor;
        this.lector = new Scanner(System.in);
    }

    /**
     * Despliega el menú principal
     */
    public void desplegarMenu() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║   Sistema de gestión agro-tecnológica         ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println("1. Ver todos los equipos");
        System.out.println("2. Buscar equipo por código");
        System.out.println("3. Buscar equipo por nombre");
        System.out.println("4. Ordenar por consumo eléctrico");
        System.out.println("5. Ver estadísticas");
        System.out.println("6. Ver detalles de un equipo");
        System.out.println("7. Probar capacidades de un equipo");
        System.out.println("8. Salir");
        System.out.println("════════════════════════════════════════════════");
        System.out.print("¿Qué querés hacer? ");
    }

    /**
     * Inicia el ciclo principal de la interfaz
     * Evita while(true) usando condición explícita
     */
    public void iniciar() {
        boolean continuar = true;
        int opcion = 0;

        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║        Bienvenido al sistema                  ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println("Dale, iniciando el sistema...");
        gestor.inicializarPlataforma();
        System.out.println("Listo! Tenemos " +
                gestor.enumerarTodosEquipos().size() + " equipos en el sistema.");

        while (opcion != 8) {
            desplegarMenu();

            if (lector.hasNextInt()) {
                opcion = lector.nextInt();
                lector.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        enumerarEquipos();
                        break;
                    case 2:
                        localizarPorCodigo();
                        break;
                    case 3:
                        localizarPorNombre();
                        break;
                    case 4:
                        organizarPorConsumo();
                        break;
                    case 5:
                        mostrarEstadisticas();
                        break;
                    case 6:
                        presentarDetalles();
                        break;
                    case 7:
                        probarCapacidades();
                        break;
                    case 8:
                        System.out.println("\nGracias por usar el sistema, que te vaya bien!");
                        continuar = false;
                        break;
                    default:
                        System.out.println("\nUy! Esa opción no existe, probá de nuevo.");
                }
            } else {
                System.out.println("\nEh! Tenés que meter un número, no otra cosa.");
                lector.nextLine(); // Limpiar entrada inválida
            }

            if (continuar) {
                System.out.print("\nDale Enter para seguir...");
                lector.nextLine();
            }
        }

        lector.close();
    }

    /**
     * Lista todos los equipos usando polimorfismo
     */
    private void enumerarEquipos() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║           Todos los equipos                       ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");

        List<Equipo> equipos = gestor.enumerarTodosEquipos();

        if (equipos.isEmpty()) {
            System.out.println("Mirá, no hay equipos registrados todavía.");
            return;
        }

        System.out.println(String.format("%-10s %-35s %-20s %-15s %-15s",
                "Código", "Nombre", "Marca", "Consumo (W)", "Tipo"));
        System.out.println("─".repeat(100));

        // Polimorfismo: cada equipo responde según su implementación
        for (Equipo equipo : equipos) {
            System.out.println(String.format("%-10s %-35s %-20s %-15.2f %-15s",
                    equipo.getCodigo(),
                    equipo.getDenominacion(),
                    equipo.getMarca(),
                    equipo.getConsumoWatts(),
                    equipo.obtenerCategoria()));
        }

        System.out.println("─".repeat(100));
        System.out.println("Total: " + equipos.size() + " equipos");
    }

    /**
     * Busca un equipo por código
     */
    private void localizarPorCodigo() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║        Buscar por código                      ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");

        System.out.print("Metele el código del equipo: ");
        String codigo = lector.nextLine().trim();

        Equipo equipo = gestor.localizarEquipoPorCodigo(codigo);

        if (equipo != null) {
            System.out.println("\nLo encontramos!\n");
            System.out.println(equipo.generarResumen());
        } else {
            System.out.println("\nNel, ese código no existe: " + codigo);
        }
    }

    /**
     * Busca equipos por nombre
     */
    private void localizarPorNombre() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║        Buscar por nombre                      ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");

        System.out.print("¿Cómo se llama o qué parte del nombre sabés? ");
        String nombre = lector.nextLine().trim();

        List<Equipo> equipos = gestor.localizarEquipoPorDenominacion(nombre);

        if (equipos.isEmpty()) {
            System.out.println("\nNo hay pisto, ningún equipo coincide con: " + nombre);
            return;
        }

        System.out.println("\nBieeeen! Encontramos " + equipos.size() + " equipo(s):\n");

        for (Equipo equipo : equipos) {
            System.out.println("─".repeat(80));
            System.out.println(equipo.generarResumen());
        }
    }

    /**
     * Organiza y muestra equipos por consumo eléctrico
     * Demuestra el uso de Comparable
     */
    private void organizarPorConsumo() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║    Ordenados por consumo eléctrico            ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");

        List<Equipo> equiposOrdenados = gestor.organizarEquiposPorConsumo();

        if (equiposOrdenados.isEmpty()) {
            System.out.println("No hay equipos para ordenar, mano.");
            return;
        }

        System.out.println(String.format("%-5s %-10s %-40s %-15s",
                "#", "Código", "Nombre", "Consumo (W)"));
        System.out.println("─".repeat(80));

        int posicion = 1;
        for (Equipo equipo : equiposOrdenados) {
            System.out.println(String.format("%-5d %-10s %-40s %-15.2f",
                    posicion++,
                    equipo.getCodigo(),
                    equipo.getDenominacion(),
                    equipo.getConsumoWatts()));
        }

        System.out.println("─".repeat(80));
        System.out.println("\nAhí están, del que gasta menos al que gasta más.");
    }

    /**
     * Muestra estadísticas generales del sistema
     */
    private void mostrarEstadisticas() {
        System.out.println("\n" + gestor.generarReporteEstadistico());
    }

    /**
     * Presenta detalles completos de un equipo
     */
    private void presentarDetalles() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║        Detalles completos                     ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");

        System.out.print("¿Cuál código querés ver? ");
        String codigo = lector.nextLine().trim();

        Equipo equipo = gestor.localizarEquipoPorCodigo(codigo);

        if (equipo == null) {
            System.out.println("\nAhh chis, no hay ningún equipo con ese código.");
            return;
        }

        System.out.println("\n" + equipo.generarResumen());

        // Mostrar capacidades usando polimorfismo
        System.out.println("\n--- Lo que puede hacer ---");

        if (equipo instanceof IMedible) {
            System.out.println("✓ Puede medir datos");
        }

        if (equipo instanceof IEjecutable) {
            System.out.println("✓ Puede hacer acciones");
        }

        if (equipo instanceof IAuditable) {
            System.out.println("✓ Lleva bitácora");
        }
    }

    /**
     * Prueba las capacidades de un equipo
     * Demuestra polimorfismo con las interfaces
     */
    private void probarCapacidades() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║        Probar qué puede hacer                 ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");

        System.out.print("Dale, ¿cuál código? ");
        String codigo = lector.nextLine().trim();

        Equipo equipo = gestor.localizarEquipoPorCodigo(codigo);

        if (equipo == null) {
            System.out.println("\n JAJA ese código no existe.");
            return;
        }

        System.out.println("\nVamos a probar: " + equipo.getDenominacion());
        System.out.println("─".repeat(80));

        boolean tieneCapacidades = false;

        // Polimorfismo: verificar capacidades y ejecutarlas
        if (equipo instanceof IMedible) {
            tieneCapacidades = true;
            System.out.println("\n[Puede medir]");
            IMedible medible = (IMedible) equipo;
            String datos = medible.capturarDatos();
            System.out.println(datos);
        }

        if (equipo instanceof IEjecutable) {
            tieneCapacidades = true;
            System.out.println("\n[Puede ejecutar acciones]");

            // Mostrar comandos disponibles según el tipo de equipo
            System.out.println("\nComandos disponibles para este equipo:");

            if (equipo instanceof CompuertaHidraulica) {
                System.out.println("  - ABRIR: Abre la compuerta");
                System.out.println("  - CERRAR: Cierra la compuerta");
                System.out.println("  - PARCIAL: Posición parcial");
            } else if (equipo instanceof ModuloNutrientes) {
                System.out.println("  - APLICAR: Inicia la aplicación de nutrientes");
                System.out.println("  - DETENER: Detiene la aplicación");
                System.out.println("  - PROGRAMAR: Programa nueva dosificación");
            } else if (equipo instanceof RociadorInteligente) {
                System.out.println("  - ACTIVAR: Enciende el rociador");
                System.out.println("  - DESACTIVAR: Apaga el rociador");
                System.out.println("  - AJUSTAR: Ajusta parámetros de rociado");
            } else if (equipo instanceof UnidadBombeo) {
                System.out.println("  - ENCENDER: Enciende la bomba");
                System.out.println("  - APAGAR: Apaga la bomba");
                System.out.println("  - VELOCIDAD: Ajusta velocidad de bombeo");
            } else if (equipo instanceof AeronaveHidratacion) {
                System.out.println("  - DESPEGAR: Inicia el vuelo");
                System.out.println("  - ATERRIZAR: Aterriza la aeronave");
                System.out.println("  - ROCIAR: Inicia el rociado aéreo");
            } else if (equipo instanceof AeronaveVigilancia) {
                System.out.println("  - INICIAR_VUELO: Inicia vuelo de monitoreo");
                System.out.println("  - CAPTURAR_IMAGEN: Captura imagen");
                System.out.println("  - REGRESAR_BASE: Regresa a la base");
            } else {
                System.out.println("  - Comandos genéricos disponibles");
            }

            System.out.print("\n¿Qué comando le mandamos? ");
            String comando = lector.nextLine().trim();

            IEjecutable ejecutable = (IEjecutable) equipo;
            boolean resultado = ejecutable.realizarOperacion(comando);

            if (resultado) {
                System.out.println("✓ Si Jalá! Se ejecutó bien la operación.");
            } else {
                System.out.println("✗ Nel pa, no se pudo hacer eso.");
            }
        }

        if (equipo instanceof IAuditable) {
            tieneCapacidades = true;
            System.out.println("\n[Tiene bitácora]");
            IAuditable auditable = (IAuditable) equipo;
            List<String> bitacora = auditable.consultarBitacora();

            System.out.println("\nÚltimas movidas en la bitácora:");
            int limite = Math.min(5, bitacora.size());
            for (int i = bitacora.size() - limite; i < bitacora.size(); i++) {
                System.out.println("  " + bitacora.get(i));
            }
            System.out.println("\nTotal de registros: " + bitacora.size());
        }

        if (!tieneCapacidades) {
            System.out.println("\nEste equipo es básico, no hace nada especial.");
        }
    }
}