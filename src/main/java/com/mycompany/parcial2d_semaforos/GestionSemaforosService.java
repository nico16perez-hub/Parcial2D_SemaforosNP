/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2d_semaforos;

/**
 *
 * @author Nicolino Uchiha
 */
import java.util.ArrayList;
import java.util.List;

public class GestionSemaforosService {
    private List<Semaforo> semaforos;
    private List<Denuncia> denuncias;
    private List<Orden_Composicion> ordenes;
    private List<Equipo_Control> equipos;

    public GestionSemaforosService() {
        this.semaforos = new ArrayList<>();
        this.denuncias = new ArrayList<>();
        this.ordenes = new ArrayList<>();
        this.equipos = new ArrayList<>();
    }

    public void asignarOrden(Denuncia denuncia, Orden_Composicion orden) {
        if (denuncia.tieneOrdenAsignada()) {
            throw new OrdenYaAsignadaException(
                "La denuncia " + denuncia.getCodD() + " ya tiene una orden asignada");
        }
        denuncia.setOrden(orden);
        ordenes.add(orden);
    }

    public int obtenerCantidadDenunciasSemaforo(Semaforo semaforo) {
        return semaforo.contarDenuncias();
    }

    public void registrarSemaforo(Semaforo semaforo) {
        semaforos.add(semaforo);
    }

    public void registrarDenuncia(Denuncia denuncia) {
        denuncias.add(denuncia);
    }

    public void registrarEquipo(Equipo_Control equipo) {
        equipos.add(equipo);
    }

    public List<Semaforo> getSemaforos() {
        return semaforos;
    }

    public List<Denuncia> getDenuncias() {
        return denuncias;
    }

    public List<Orden_Composicion> getOrdenes() {
        return ordenes;
    }

    public List<Equipo_Control> getEquipos() {
        return equipos;
    }
}