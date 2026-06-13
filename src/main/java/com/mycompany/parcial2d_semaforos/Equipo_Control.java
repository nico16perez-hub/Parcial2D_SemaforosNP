package com.mycompany.parcial2d_semaforos;

import java.util.ArrayList;
import java.util.List;

public class Equipo_Control {
    private String codigo;
    private String especialidad;
    private String estado;
    private List<Miembro> miembros;
    private List<Orden_Composicion> ordenesAsignadas;

    public Equipo_Control(String codigo, String especialidad, String estado) {
        this.codigo = codigo;
        this.especialidad = especialidad;
        this.estado = estado;
        this.miembros = new ArrayList<>();
        this.ordenesAsignadas = new ArrayList<>();
        this.crearMiembros();
    }

    public void crearMiembros() {
        for (int i = 1; i <= 4; i++) {
            boolean esResponsable = (i == 1);
            miembros.add(new Miembro("M" + i, "Miembro " + i, 
                           esResponsable ? "Responsable" : "Técnico", 
                           this.estado, esResponsable));
        }
    }

    public void asignarOrden(Orden_Composicion orden) {
        this.estado = "Ocupado";
        this.ordenesAsignadas.add(orden);
        for (Miembro m : miembros) {
            m.setLibre(false);
        }
    }

    public void liberarEquipo() {
        this.estado = "Libre";
        for (Miembro m : miembros) {
            m.liberarMiembro();
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public int miembrosPorEquipo() {
        return miembros.size();
    }

    public List<Orden_Composicion> getOrdenesAsignadas() {
        return ordenesAsignadas;
    }
}