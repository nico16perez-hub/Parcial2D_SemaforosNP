
package com.mycompany.parcial2d_semaforos;

import java.util.Objects;


public class LUZ {
    private String numeroSerie;
    private String empresaFabricadora;
    private String tipo;
    private String color;

    public LUZ(String numeroSerie, String empresaFabricadora, String tipo, String color) {
        this.numeroSerie = numeroSerie;
        this.empresaFabricadora = empresaFabricadora;
        this.tipo = tipo;
        this.color = color;
    }

    public String getNumeroSerie() { return numeroSerie; }
    public String getEmpresaFabricadora() { return empresaFabricadora; }
    public String getTipo() { return tipo; }
    public String getColor() { return color; }
}
