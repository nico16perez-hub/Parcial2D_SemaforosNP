package com.mycompany.parcial2d_semaforos;

import java.util.Date;

public class Orden_Composicion {
    private String nroOrden;
    private Date fechaReparacionProgramada;
    private Date fechaReparacionEfectiva;
    private String detalle;
    private Denuncia denuncia;
    private boolean completada;

    public Orden_Composicion(String nroOrden, Date fechaProgramada, String detalle) {
        this.nroOrden = nroOrden;
        this.fechaReparacionProgramada = fechaProgramada;
        this.detalle = detalle;
        this.completada = false;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }

    public void actualizarReparacion(Date fechaEfectiva) {
        this.fechaReparacionEfectiva = fechaEfectiva;
        this.completada = true;
    }

    public String imprimirOrden() {
        return "Orden N°: " + nroOrden + "\n" +
               "Detalle: " + detalle + "\n" +
               "Fecha Programada: " + fechaReparacionProgramada + "\n" +
               "Fecha Efectiva: " + fechaReparacionEfectiva + "\n" +
               "Estado: " + (completada ? "Completada" : "Pendiente");
    }

    public void enviarNotificacionDenunciante() {
        if (denuncia != null && denuncia.getDenunciante() != null) {
            denuncia.getDenunciante().notificarReparacion(this);
        }
    }

    public boolean isCompletada() {
        return completada;
    }

    public Date getFechaReparacionEfectiva() {
        return fechaReparacionEfectiva;
    }

    public String getNroOrden() {
        return nroOrden;
    }
}