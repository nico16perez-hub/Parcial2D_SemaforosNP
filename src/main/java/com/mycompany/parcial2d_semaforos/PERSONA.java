
package com.mycompany.parcial2d_semaforos;


import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class PERSONA {
    private String nombre;
    private String email;
    private List<DENUNCIA> denuncias;

    public PERSONA(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.denuncias = new ArrayList<>();
    }

    public DENUNCIA registrarDenuncia(SEMAFORO semaforo, String problema, String prioridad, String calleX, String calleY) {
        String codD = "D" + System.currentTimeMillis();
        DENUNCIA nuevaDenuncia = new DENUNCIA(codD, new Date(), calleX, calleY, problema, prioridad);
        nuevaDenuncia.setDenunciante(this);
        nuevaDenuncia.setSemaforo(semaforo);
        this.denuncias.add(nuevaDenuncia);
        semaforo.addDenuncia(nuevaDenuncia);
        return nuevaDenuncia;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
}