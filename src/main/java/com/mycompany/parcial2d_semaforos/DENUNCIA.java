package com.mycompany.parcial2d_semaforos;

import java.util.Date;

public class Denuncia {
    private String codD;
    private Date fechaDenuncia;
    private String calleX;
    private String calleY;
    private String problema;
    private String prioridadReparacion;
    private Persona denunciante; 
    private Semaforo semaforo;
    private Orden_Composicion ordenAsignada;

    public Denuncia(String codD, Date fechaDenuncia, String calleX, String calleY, 
                   String problema, String prioridadReparacion) {
        this.codD = codD;
        this.fechaDenuncia = fechaDenuncia;
        this.calleX = calleX;
        this.calleY = calleY;
        this.problema = problema;
        this.prioridadReparacion = prioridadReparacion;
    }


    
    public void setDenunciante(Persona denunciante) {
        this.denunciante = denunciante;
    }

    public Persona getDenunciante() {
        return denunciante;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public Semaforo getSemaforo() {
        return semaforo;
    }

    public void setOrden(Orden_Composicion orden) {
        if (this.ordenAsignada != null) {
            throw new OrdenYaAsignadaException("La denuncia ya tiene una orden asignada");
        }
        this.ordenAsignada = orden;
    }

    public Orden_Composicion getOrdenAsignada() {
        return ordenAsignada;
    }

    public boolean esPrioridadValida() {
        return "Alta".equals(prioridadReparacion) || 
               "Media".equals(prioridadReparacion) || 
               "Baja".equals(prioridadReparacion);
    }

    public boolean tieneOrdenAsignada() {
        return ordenAsignada != null;
    }

    public String getCodD() {
        return codD;
    }

    public Date getFechaDenuncia() {
        return fechaDenuncia;
    }

    public String getPrioridadReparacion() {
        return prioridadReparacion;
    }
}