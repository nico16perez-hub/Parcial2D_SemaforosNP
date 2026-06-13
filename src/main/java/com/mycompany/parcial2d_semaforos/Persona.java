package com.mycompany.parcial2d_semaforos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Persona {
    private String nombre;
    private String email;
    private List<Denuncia> denuncias;

    public Persona(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.denuncias = new ArrayList<>();
    }

    public Denuncia registrarDenuncia(Semaforo semaforo, String problema, String prioridad, String calleX, String calleY) {
        Denuncia denunciaExistente = buscarDenunciaExistente(semaforo);
        if (denunciaExistente != null) {
            System.out.println("Ya existe una denuncia para este semáforo. Fecha: " + denunciaExistente.getFechaDenuncia());
            return denunciaExistente;
        }
        
        String codD = "D" + System.currentTimeMillis();
        Denuncia nuevaDenuncia = new Denuncia(codD, new Date(), calleX, calleY, problema, prioridad);
        nuevaDenuncia.setDenunciante(this);
        nuevaDenuncia.setSemaforo(semaforo);
        this.denuncias.add(nuevaDenuncia);
        semaforo.addDenuncia(nuevaDenuncia);
        return nuevaDenuncia;
    }

    private Denuncia buscarDenunciaExistente(Semaforo semaforo) {
        for (Denuncia denuncia : denuncias) {
            if (denuncia.getSemaforo() == semaforo) {
                return denuncia;
            }
        }
        return null;
    }

    public List<Denuncia> denunciasDelDenunciante() {
        return denuncias;
    }

    public void notificarReparacion(Orden_Composicion orden) {
        System.out.println("Notificación a " + nombre + " (" + email + "): Su semáforo fue reparado el " + orden.getFechaReparacionEfectiva());
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}