package com.mycompany.parcial2d_semaforos;

public class MIEMBRO {
    private String id;
    private String nombre;
    private String puesto;
    private String estado;
    private boolean esResponsable;
    private boolean libre;

    public MIEMBRO(String id, String nombre, String puesto, String estado, boolean esResponsable) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.estado = estado;
        this.esResponsable = esResponsable;
        this.libre = "Libre".equalsIgnoreCase(estado);
    }

    public void liberarMiembro() {
        this.estado = "Libre";
        this.libre = true;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
        this.estado = libre ? "Libre" : "Ocupado";
    }

    public boolean isLibre() { 
        return libre; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public String getId() { 
        return id; 
    }
    
    public String getPuesto() { 
        return puesto; 
    }
    
    public String getEstado() { 
        return estado; 
    }
    
    public boolean esResponsable() { 
        return esResponsable; 
    }
}