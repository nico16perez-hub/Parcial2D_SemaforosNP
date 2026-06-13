/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2d_semaforos;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import java.util.Date;

public class DENUNCIA {
    private String codD;
    private Date fechaDenuncia;
    private String calleX;
    private String calleY;
    private String problema;
    private String prioridadReparacion;
    private PERSONA denunciante;
    private SEMAFORO semaforo;
    private ORDEN_COMPOSICION ordenAsignada;

    public DENUNCIA(String codD, Date fechaDenuncia, String calleX, String calleY, String problema, String prioridadReparacion) {
        this.codD = codD;
        this.fechaDenuncia = fechaDenuncia;
        this.calleX = calleX;
        this.calleY = calleY;
        this.problema = problema;
        this.prioridadReparacion = prioridadReparacion;
    }

    public void setDenunciante(PERSONA persona) { this.denunciante = persona; }
    public void setSemaforo(SEMAFORO semaforo) { this.semaforo = semaforo; }

    public void setOrden(ORDEN_COMPOSICION orden) {
        if (this.ordenAsignada != null) {
            throw new OrdenYaAsignadaException("La denuncia ya tiene una orden asignada");
        }
        this.ordenAsignada = orden;
        orden.setDenuncia(this);
    }

    public boolean esPrioridadValida() {
        return "Alta".equals(prioridadReparacion) || "Media".equals(prioridadReparacion) || "Baja".equals(prioridadReparacion);
    }

    public boolean tieneOrdenAsignada() { return ordenAsignada != null; }
    public String getCodD() { return codD; }
    public String getPrioridadReparacion() { return prioridadReparacion; }
}