/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2d_semaforos;


import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class EQUIPO_CONTROL {
    private String codigo;
    private String especialidad;
    private String estado;
    private List<MIEMBRO> miembros;
    private List<ORDEN_COMPOSICION> ordenesAsignadas;

    public EQUIPO_CONTROL(String codigo, String especialidad, String estado) {
        this.codigo = codigo;
        this.especialidad = especialidad;
        this.estado = estado;
        this.miembros = new ArrayList<>();
        this.ordenesAsignadas = new ArrayList<>();
        this.crearMiembros();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<ORDEN_COMPOSICION> getOrdenesAsignadas() {
        return ordenesAsignadas;
    }

    public void setOrdenesAsignadas(List<ORDEN_COMPOSICION> ordenesAsignadas) {
        this.ordenesAsignadas = ordenesAsignadas;
    }

    public void crearMiembros() {
        for (int i = 1; i <= 4; i++) {
            boolean esResponsable = (i == 1);
            miembros.add(new MIEMBRO("M" + i, "Miembro " + i, esResponsable ? "Responsable" : "Técnico", this.estado, esResponsable));
        }
    }

    public void asignarOrden(ORDEN_COMPOSICION orden) {
        this.estado = "Ocupado";
        this.ordenesAsignadas.add(orden);
        for (MIEMBRO m : miembros) m.setLibre(false);
    }

    public void liberarEquipo() {
        this.estado = "Libre";
        for (MIEMBRO m : miembros) {
            m.liberarMiembro();
        }
    }

    public String getEstado() { return estado; }
    public List<MIEMBRO> getMiembros() { return miembros; }
    public int miembrosPorEquipo() { return miembros.size(); }
}