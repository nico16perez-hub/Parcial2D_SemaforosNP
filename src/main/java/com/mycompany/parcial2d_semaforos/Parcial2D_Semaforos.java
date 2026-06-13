/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.parcial2d_semaforos;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Nicolino Uchiha
 */
public class Parcial2D_Semaforos {

     public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("   SISTEMA DE GESTIÓN DE SEMÁFOROS - Parcial 2D");
        System.out.println("==============================================\n");
        
       
        System.out.println("1️ CREANDO SEMÁFOROS...");
        Semaforo semaforo1 = new Semaforo(1, "Descompuesto", "Av. San Martín y Córdoba", "Led");
        Semaforo semaforo2 = new Semaforo(2, "Activo", "Av. Libertador y 9 de Julio", "Faros");
        Semaforo semaforo3 = new Semaforo(3, "Descompuesto", "Calle Rivadavia y Sarmiento", "Led");
        
        System.out.println("✓ Semáforo 1 creado - Estado: " + semaforo1.getEstado());
        System.out.println("✓ Semáforo 2 creado - Estado: " + semaforo2.getEstado());
        System.out.println("✓ Semáforo 3 creado - Estado: " + semaforo3.getEstado());
        
        
        System.out.println("\n   Verificando composición de luces:");
        System.out.println("   - Semáforo 1 tiene " + semaforo1.getLuces().size() + " luces");
        System.out.println("   - Semáforo 2 tiene " + semaforo2.getLuces().size() + " luces");
        System.out.println("   - Semáforo 3 tiene " + semaforo3.getLuces().size() + " luces");
        
     
        System.out.println("\n  REGISTRANDO PERSONAS DENUNCIANTES...");
        Persona persona1 = new Persona("María González", "maria.gonzalez@email.com");
        Persona persona2 = new Persona("Carlos Rodríguez", "carlos.rodriguez@email.com");
        
        System.out.println("✓ Persona registrada: " + persona1.getNombre());
        System.out.println("✓ Persona registrada: " + persona2.getNombre());
       
        System.out.println("\n REALIZANDO DENUNCIAS DE SEMÁFOROS...");
        
    
        System.out.println("\n--- ESCENARIO A: Denuncia de semáforo descompuesto ---");
        Denuncia denuncia1 = persona1.registrarDenuncia(
            semaforo1, 
            "No funciona la luz roja", 
            "Alta", 
            "Av. San Martín", 
            "Córdoba"
        );
        System.out.println(" Denuncia registrada - Código: " + denuncia1.getCodD());
        System.out.println("  Denunciante: " + persona1.getNombre());
        System.out.println("  Prioridad: " + denuncia1.getPrioridadReparacion());
        System.out.println("  Total denuncias del semáforo 1: " + semaforo1.contarDenuncias());
        

        Denuncia denuncia2 = persona1.registrarDenuncia(
            semaforo3, 
            "Parpadeo constante en todas las luces", 
            "Media", 
            "Calle Rivadavia", 
            "Sarmiento"
        );
        System.out.println("\n Segunda denuncia registrada - Código: " + denuncia2.getCodD());
        System.out.println("  Total denuncias de " + persona1.getNombre() + ": 2");
        
   
        Denuncia denuncia3 = persona2.registrarDenuncia(
            semaforo1, 
            "Semáforo completamente apagado", 
            "Alta", 
            "Av. San Martín", 
            "Córdoba"
        );
        System.out.println("\n Tercera denuncia registrada - Código: " + denuncia3.getCodD());
        System.out.println("  Total denuncias del semáforo 1: " + semaforo1.contarDenuncias());
        

        System.out.println("\n  GENERANDO ÓRDENES DE COMPOSICIÓN...");
        
     
        System.out.println("\n--- ESCENARIO B: Generación de orden de reparación ---");
        Orden_Composicion orden1 = new Orden_Composicion(
            "OC-001", 
            new Date(), 
            "Reparación completa del sistema Led - Luz roja"
        );
        denuncia1.setOrden(orden1);
        System.out.println(" Orden creada: " + orden1.getNroOrden());
        System.out.println("  Detalle: " + orden1.getNroOrden());
        System.out.println("  Fecha programada: " + orden1.getNroOrden());
        
        
        System.out.println("\n  CREANDO EQUIPOS DE CONTROL...");
        Equipo_Control equipo1 = new Equipo_Control("EQ-001", "Eléctrica", "Libre");
        Equipo_Control equipo2 = new Equipo_Control("EQ-002", "Mecánica", "Libre");
        
        System.out.println(" Equipo creado: " + equipo1.getCodigo() + " - Especialidad: " + equipo1.getEstado());
        System.out.println(" Equipo creado: " + equipo2.getCodigo() + " - Especialidad: " + equipo2.getEstado());
        System.out.println("  Miembros por equipo: " + equipo1.miembrosPorEquipo());
        
      
        System.out.println("\n ASIGNANDO EQUIPO DE CONTROL A LA ORDEN...");
        equipo1.asignarOrden(orden1);
        System.out.println(" Equipo " + equipo1.getCodigo() + " asignado a orden " + orden1.getNroOrden());
        System.out.println("  Estado del equipo: " + equipo1.getEstado());
        
    
        List<Miembro> miembros = equipo1.getMiembros();
        System.out.print("  Estado de miembros: ");
        for (Miembro miembro : miembros) {
            System.out.print((miembro.isLibre() ? "Libre" : "Ocupado") + " ");
        }
        System.out.println();
        
 
        System.out.println("\n COMPLETANDO REPARACIÓN DEL SEMÁFORO...");
        Date fechaReparacion = new Date();
        orden1.actualizarReparacion(fechaReparacion);
        equipo1.liberarEquipo();
        
        System.out.println(" Reparacion completada");
        System.out.println("  Fecha efectiva: " + fechaReparacion);
        System.out.println("  Estado del equipo: " + equipo1.getEstado());
        
       
        System.out.print("  Estado de miembros después de liberar: ");
        for (Miembro miembro : miembros) {
            System.out.print((miembro.isLibre() ? "Libre " : "Ocupado ") + " ");
        }
        System.out.println();
        
     
        System.out.println("\n  MÉTRICAS Y ESTADÍSTICAS DEL SISTEMA...");
        
   
        System.out.println("\n Semáforos descompuestos:");
        System.out.println("   - Semáforo 1: " + (semaforo1.estaDescompuesto() ? "Descompuesto ⚠️" : "Activo ✓"));
        System.out.println("   - Semáforo 2: " + (semaforo2.estaDescompuesto() ? "Descompuesto ⚠️" : "Activo ✓"));
        System.out.println("   - Semáforo 3: " + (semaforo3.estaDescompuesto() ? "Descompuesto ⚠️" : "Activo ✓"));
        
        
        System.out.println("\n Total de denuncias por semáforo:");
        System.out.println("   - Semáforo 1: " + semaforo1.contarDenuncias() + " denuncias");
        System.out.println("   - Semáforo 2: " + semaforo2.contarDenuncias() + " denuncias");
        System.out.println("   - Semáforo 3: " + semaforo3.contarDenuncias() + " denuncias");
        

   
        System.out.println("\n Equipos disponibles:");
        System.out.println("   - Equipo 1: " + equipo1.getEstado());
        System.out.println("   - Equipo 2: " + equipo2.getEstado());
        
 
        System.out.println("\n PRUEBA DE ROBUSTEZ - Intento de asignar segunda orden...");
        try {
            Orden_Composicion orden2 = new Orden_Composicion(
                "OC-002", 
                new Date(), 
                "Orden adicional"
            );
            denuncia1.setOrden(orden2); 
            System.out.println("✗ ERROR: No se lanzó la excepción esperada");
        } catch (OrdenYaAsignadaException e) {
            System.out.println("✓ Excepción lanzada correctamente: " + e.getMessage());
        }
        
        System.out.println("\n==============================================");
        System.out.println("   PRUEBAS COMPLETADAS EXITOSAMENTE");
        System.out.println("==============================================");
    }
}
