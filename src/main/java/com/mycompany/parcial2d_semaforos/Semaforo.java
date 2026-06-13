package com.mycompany.parcial2d_semaforos;

import java.util.ArrayList;
import java.util.List;

public class Semaforo {
    private int nro;
    private String estado;
    private String ubicacion;
    private String tipoCorriente;
    private List<Luz> luces;
    private List<Denuncia> historicoDenuncias;

    public Semaforo(int nro, String estado, String ubicacion, String tipoCorriente) {
        this.nro = nro;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.tipoCorriente = tipoCorriente;
        this.luces = new ArrayList<>();
        this.historicoDenuncias = new ArrayList<>();
        this.crearLuces();
    }

    public List<Denuncia> getHistoricoDenuncias() {
        return historicoDenuncias;
    }

    public void crearLuces() {
        luces.add(new Luz("LS001", "Philips", "LED", "Rojo"));
        luces.add(new Luz("LS002", "Philips", "LED", "Amarillo"));
        luces.add(new Luz("LS003", "Philips", "LED", "Verde"));
    }

    public void addDenuncia(Denuncia denuncia) {
        this.historicoDenuncias.add(denuncia);
    }

    public List<Luz> getLuces() {
        return luces;
    }

    public Luz getLuz(int posicion) {
        if (posicion >= 0 && posicion < luces.size()) {
            return luces.get(posicion);
        }
        return null;
    }

    public int contarDenuncias() {
        return historicoDenuncias.size();
    }

    public boolean estaDescompuesto() {
        return "Descompuesto".equalsIgnoreCase(this.estado);
    }

    public int getNro() {
        return nro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getTipoCorriente() {
        return tipoCorriente;
    }
}   