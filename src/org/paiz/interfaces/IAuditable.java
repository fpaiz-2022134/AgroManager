package org.paiz.interfaces;

import java.util.List;

/**
 * Interfaz IAuditable
 * Define el contrato para dispositivos que mantienen bitácora de eventos
 * @author Franco Paiz
 * @version 1.0
 */
public interface IAuditable {
    /**
     * Agrega un registro a la bitácora del dispositivo
     * @param entrada texto del evento que se registrará
     */
    void agregarRegistro(String entrada);

    /**
     * Consulta el historial completo de registros
     * @return List con todos los registros almacenados
     */
    List<String> consultarBitacora();
}