package org.paiz.model;

import org.paiz.interfaces.IAuditable;
import org.paiz.interfaces.IEjecutable;
import org.paiz.interfaces.IMedible;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase AeronaveVigilancia
 * Representa un dron de monitoreo con cámaras multiespectrales
 * @author Franco Paiz
 * @version 1.0
 */
public class AeronaveVigilancia extends Equipo
        implements IEjecutable, IMedible, IAuditable {

    private List<String> sensoresOpticos;
    private String definicion;
    private double autonomiaMinutos;
    private List<String> bitacora;

    public AeronaveVigilancia(String codigo, String denominacion, String marca,
                              double consumo, String zona, double autonomia,
                              String definicion) {
        super(codigo, denominacion, marca, consumo, zona);
        this.autonomiaMinutos = autonomia;
        this.definicion = definicion;
        this.sensoresOpticos = new ArrayList<>();
        this.bitacora = new ArrayList<>();
        inicializarSensores();
        agregarRegistro("Aeronave de vigilancia inicializada");
    }

    private void inicializarSensores() {
        sensoresOpticos.add("RGB");
        sensoresOpticos.add("Infrarrojo");
        sensoresOpticos.add("Multiespectral");
    }

    @Override
    public boolean realizarOperacion(String instruccion) {
        boolean exitoso = false;

        if (instruccion.equalsIgnoreCase("INICIAR_VUELO")) {
            agregarRegistro("Vuelo de monitoreo iniciado");
            exitoso = true;
        } else if (instruccion.equalsIgnoreCase("CAPTURAR_IMAGEN")) {
            agregarRegistro("Imagen capturada - Resolución: " + definicion);
            exitoso = true;
        } else if (instruccion.equalsIgnoreCase("REGRESAR_BASE")) {
            agregarRegistro("Regreso a base iniciado");
            exitoso = true;
        }

        return exitoso;
    }

    @Override
    public String capturarDatos() {
        StringBuilder datos = new StringBuilder();
        datos.append("Datos de vigilancia:\n");
        datos.append(String.format("  - Resolución: %s\n", definicion));
        datos.append(String.format("  - Autonomía restante: %.1f min\n",
                autonomiaMinutos * (0.5 + Math.random() * 0.5)));
        datos.append("  - Sensores activos: ");
        for (int i = 0; i < sensoresOpticos.size(); i++) {
            datos.append(sensoresOpticos.get(i));
            if (i < sensoresOpticos.size() - 1) datos.append(", ");
        }
        agregarRegistro("Datos de vigilancia capturados");
        return datos.toString();
    }

    @Override
    public void agregarRegistro(String entrada) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        bitacora.add(timestamp + " - " + entrada);
    }

    @Override
    public List<String> consultarBitacora() {
        return new ArrayList<>(bitacora);
    }

    @Override
    public String obtenerCategoria() {
        return "Dron de Monitoreo";
    }

    @Override
    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== AERONAVE DE VIGILANCIA ===\n");
        resumen.append(String.format("Código: %s\n", codigo));
        resumen.append(String.format("Denominación: %s\n", denominacion));
        resumen.append(String.format("Marca: %s\n", marca));
        resumen.append(String.format("Consumo: %.2f W\n", consumoWatts));
        resumen.append(String.format("Zona: %s\n", zona));
        resumen.append(String.format("Autonomía: %.1f min\n", autonomiaMinutos));
        resumen.append(String.format("Resolución: %s\n", definicion));
        resumen.append("Sensores ópticos:\n");
        for (String sensor : sensoresOpticos) {
            resumen.append(String.format("  - %s\n", sensor));
        }
        resumen.append(String.format("Funcionamiento: %s\n",
                enFuncionamiento ? "Operativo" : "Inactivo"));
        resumen.append(String.format("Capacidades: Ejecución, Medición, Auditoría\n"));
        return resumen.toString();
    }

    public void incorporarSensor(String sensor) {
        if (!sensoresOpticos.contains(sensor)) {
            sensoresOpticos.add(sensor);
            agregarRegistro("Nuevo sensor incorporado: " + sensor);
        }
    }

    public double getAutonomiaMinutos() {
        return autonomiaMinutos;
    }

    public String getDefinicion() {
        return definicion;
    }
}