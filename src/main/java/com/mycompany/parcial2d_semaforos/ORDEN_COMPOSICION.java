/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2d_semaforos;


import java.util.Date;

public class ORDEN_COMPOSICION {
    private String nroOrden;
    private Date fechaReparacionProgramada;
    private Date fechaReparacionEfectiva;
    private String detalle;
    private DENUNCIA denuncia;
    private boolean completada;

    public ORDEN_COMPOSICION(String nroOrden, Date fechaProgramada, String detalle) {
        this.nroOrden = nroOrden;
        this.fechaReparacionProgramada = fechaProgramada;
        this.detalle = detalle;
        this.completada = false;
    }

    public void setDenuncia(DENUNCIA denuncia) { this.denuncia = denuncia; }

    public void actualizarReparacion(Date fechaEfectiva) {
        this.fechaReparacionEfectiva = fechaEfectiva;
        this.completada = true;
    }

    public boolean isCompletada() { return completada; }
    public String getNroOrden() { return nroOrden; }
}