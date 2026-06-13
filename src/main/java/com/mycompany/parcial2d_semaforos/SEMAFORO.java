/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2d_semaforos;


import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class SEMAFORO {
    private int nro;
    private String estado;
    private String ubicacion;
    private String tipoCorriente;
    private List<LUZ> luces;
    private List<DENUNCIA> historicoDenuncias;

    public SEMAFORO(int nro, String estado, String ubicacion, String tipoCorriente) {
        this.nro = nro;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.tipoCorriente = tipoCorriente;
        this.luces = new ArrayList<>();
        this.historicoDenuncias = new ArrayList<>();
        this.crearLuces();
    }

    public void crearLuces() {
        luces.add(new LUZ("LS001", "Philips", "LED", "Rojo"));
        luces.add(new LUZ("LS002", "Philips", "LED", "Amarillo"));
        luces.add(new LUZ("LS003", "Philips", "LED", "Verde"));
    }

    public void addDenuncia(DENUNCIA denuncia) {
        this.historicoDenuncias.add(denuncia);
    }

    public List<LUZ> getLuces() { return luces; }
    public LUZ getLuz(int posicion) { return luces.get(posicion); }
    
    public int contarDenuncias() {
        return historicoDenuncias.size();
    }

    public boolean estaDescompuesto() {
        return "Descompuesto".equalsIgnoreCase(this.estado);
    }

    public int getNro() { return nro; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}