package org.paiz.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase RepositorioEquipos (MODELO en patrón MVC)
 * Gestiona la colección polimórfica de equipos
 * @author Franco Paiz
 * @version 1.0
 */
public class RepositorioEquipos {
    // Lista polimórfica única que almacena todos los equipos
    private List<Equipo> equipos;

    /**
     * Constructor que inicializa la lista de equipos
     */
    public RepositorioEquipos() {
        this.equipos = new ArrayList<>();
    }

    /**
     * Incorpora un nuevo equipo al repositorio
     * @param equipo el equipo a agregar
     * @return boolean indicando si se agregó exitosamente
     */
    public boolean incorporarEquipo(Equipo equipo) {
        if (equipo != null && !equipos.contains(equipo)) {
            return equipos.add(equipo);
        }
        return false;
    }

    /**
     * Recupera todos los equipos del repositorio
     * @return List con todos los equipos
     */
    public List<Equipo> recuperarEquipos() {
        return new ArrayList<>(equipos);
    }

    /**
     * Localiza un equipo por su código
     * @param codigo el código a buscar
     * @return Equipo encontrado o null si no existe
     */
    public Equipo localizarPorCodigo(String codigo) {
        for (Equipo equipo : equipos) {
            if (equipo.getCodigo().equalsIgnoreCase(codigo)) {
                return equipo;
            }
        }
        return null;
    }

    /**
     * Localiza equipos por denominación (búsqueda parcial)
     * @param denominacion texto a buscar en el nombre
     * @return List con los equipos que coinciden
     */
    public List<Equipo> localizarPorDenominacion(String denominacion) {
        return equipos.stream()
                .filter(e -> e.getDenominacion().toLowerCase()
                        .contains(denominacion.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Organiza los equipos por consumo eléctrico (menor a mayor)
     * Utiliza el método compareTo implementado en Equipo
     * @return List ordenada de equipos
     */
    public List<Equipo> organizarPorConsumo() {
        List<Equipo> equiposOrdenados = new ArrayList<>(equipos);
        Collections.sort(equiposOrdenados);
        return equiposOrdenados;
    }

    /**
     * Cuenta la cantidad total de equipos
     * @return int con el número de equipos
     */
    public int contarEquipos() {
        return equipos.size();
    }

    /**
     * Carga los datos iniciales con al menos 10 equipos diversos
     * Cumple con el requisito de inicialización del sistema
     */
    public void cargarDatosIniciales() {
        // Sensores de suelo
        incorporarEquipo(new MonitorSuelo(
                "MS-001", "Monitor Suelo Parcela A", "SensorTech",
                45.0, "Parcela A", 30.0, "Humedad y pH"
        ));

        incorporarEquipo(new MonitorSuelo(
                "MS-002", "Monitor Suelo Invernadero", "AgriSense",
                50.0, "Invernadero 1", 25.0, "Humedad"
        ));

        // Detectores de humedad
        incorporarEquipo(new DetectorHumedad(
                "DH-001", "Detector Ambiental Norte", "ClimaTech",
                35.0, "Zona Norte", "0-100%", 2.5
        ));

        // Medidores térmicos
        incorporarEquipo(new MedidorTermico(
                "MT-001", "Termómetro Exterior", "TempSafe",
                25.0, "Área Externa", "-20°C a 50°C"
        ));

        // Central climática
        incorporarEquipo(new CentralClimatica(
                "CC-001", "Estación Meteorológica Central", "WeatherPro",
                120.0, "Torre Central", 15.5
        ));

        // Compuertas hidráulicas
        incorporarEquipo(new CompuertaHidraulica(
                "CH-001", "Válvula Principal Riego", "HydroFlow",
                80.0, "Sistema Principal", 5000.0
        ));

        // Módulo de nutrientes
        incorporarEquipo(new ModuloNutrientes(
                "MN-001", "Sistema Fertilización Automático", "NutriCrop",
                150.0, "Invernadero 2", 200.0
        ));

        // Rociadores inteligentes
        incorporarEquipo(new RociadorInteligente(
                "RI-001", "Aspersor Inteligente Zona Sur", "SmartIrrigation",
                95.0, "Zona Sur", 25.0
        ));

        // Unidades de bombeo
        incorporarEquipo(new UnidadBombeo(
                "UB-001", "Bomba Hidráulica Principal", "PumpMaster",
                750.0, "Sala de Máquinas", 15.0, 8000.0
        ));

        // Aeronaves
        incorporarEquipo(new AeronaveHidratacion(
                "AH-001", "Dron Riego Aéreo Delta", "AgroDrone",
                200.0, "Hangar", 45.0, 15.0
        ));

        incorporarEquipo(new AeronaveVigilancia(
                "AV-001", "Dron Monitoreo Multiespectral", "SkyVision",
                180.0, "Hangar", 60.0, "4K UHD"
        ));
    }
}