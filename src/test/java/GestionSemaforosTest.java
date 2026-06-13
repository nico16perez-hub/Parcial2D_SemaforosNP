


import com.mycompany.parcial2d_semaforos.Denuncia;
import com.mycompany.parcial2d_semaforos.Equipo_Control;
import com.mycompany.parcial2d_semaforos.GestionSemaforosService;
import com.mycompany.parcial2d_semaforos.Luz;
import com.mycompany.parcial2d_semaforos.Miembro;
import com.mycompany.parcial2d_semaforos.Orden_Composicion;
import com.mycompany.parcial2d_semaforos.OrdenYaAsignadaException;
import com.mycompany.parcial2d_semaforos.Persona;
import com.mycompany.parcial2d_semaforos.Semaforo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.params.provider.CsvSource;

public class GestionSemaforosTest {

    private static GestionSemaforosService service;
    private static Semaforo semaforoDefault;
    private static Persona personaDefault;
    private static Equipo_Control equipoDefault;
    
    private Semaforo semaforoTest;
    private Persona personaTest;


    @BeforeAll
    public static void setupAll() {
        System.out.println("=== INICIANDO SUITE DE PRUEBAS ===");
        service = new GestionSemaforosService();
        semaforoDefault = new Semaforo(1, "Descompuesto", "Av. San Martín y Córdoba", "Led");
        personaDefault = new Persona("María González", "maria.gonzalez@email.com");
        equipoDefault = new Equipo_Control("EQ-001", "Eléctrica", "Libre");
    }


    @BeforeEach
    public void setup() {
        System.out.println("Preparando test...");
        semaforoTest = new Semaforo(2, "Activo", "Calle Test", "Faros");
        personaTest = new Persona("Juan Pérez", "juan.perez@email.com");
    }


    @AfterEach
    public void teardown() {
        System.out.println(" Test completado - Limpiando recursos temporales");
    }

 
    @AfterAll
    public static void teardownAll() {
        System.out.println("=== FINALIZANDO SUITE DE PRUEBAS ===");
        System.out.println("Total de operaciones registradas en el sistema");
    }

  
    @Test
    public void testComposicionSemaforoTresLuces() {
        List<Luz> luces = semaforoDefault.getLuces();
        assertEquals(3, luces.size(), "El semáforo debe tener exactamente 3 luces");
        
        Luz luz0Obtenida = semaforoDefault.getLuz(0);
        Luz luz0Interna = luces.get(0);
        assertSame(luz0Interna, luz0Obtenida, "La luz en posición 0 debe ser la misma instancia");
    }

  
    @Test
    @Timeout(value = 400, unit = TimeUnit.MILLISECONDS)
    public void testAsignarOrdenADenunciaConOrdenExistente() {
        Denuncia denuncia = new Denuncia("D001", new Date(), 
            "Av. San Martín", "Córdoba", "No funciona luz roja", "Alta");
        denuncia.setDenunciante(personaDefault);
        denuncia.setSemaforo(semaforoDefault);
        
        Orden_Composicion orden1 = new Orden_Composicion(
            "OC001", new Date(), "Reparación completa");
        denuncia.setOrden(orden1);
        
        Orden_Composicion orden2 = new Orden_Composicion(
            "OC002", new Date(), "Reparación adicional");
        
        assertThrows(OrdenYaAsignadaException.class, () -> {
            service.asignarOrden(denuncia, orden2);
        }, "Debe lanzar OrdenYaAsignadaException cuando ya existe una orden asignada");
    }

  
    @Test
    public void testFinalizarReparacionLiberaEquipoYMiembros() {
        Denuncia denuncia = new Denuncia("D002", new Date(),
            "Av. Libertador", "9 de Julio", "Parpadeo constante", "Media");
        denuncia.setDenunciante(personaDefault);
        denuncia.setSemaforo(semaforoDefault);
        
        Orden_Composicion orden = new Orden_Composicion(
            "OC003", new Date(), "Cambio de luces");
        denuncia.setOrden(orden);
        
        equipoDefault.asignarOrden(orden);
        assertEquals("Ocupado", equipoDefault.getEstado());
        
        Date fechaEfectiva = new Date();
        orden.actualizarReparacion(fechaEfectiva);
        equipoDefault.liberarEquipo();
        
        assertEquals("Libre", equipoDefault.getEstado(), 
            "El equipo debe estar en estado Libre después de la reparación");
        
        List<Miembro> miembros = equipoDefault.getMiembros();
        assertEquals(4, miembros.size(), "El equipo debe tener 4 miembros");
        
        for (Miembro miembro : miembros) {
            assertTrue(miembro.isLibre(), 
                "El miembro " + miembro.getNombre() + " debe estar libre (isLibre() = true)");
        }
    }

  
    @ParameterizedTest
    @CsvSource({
        "Alta,   Prioridad máxima - requiere atención inmediata",
        "Media,  Prioridad intermedia - reparación en 24-48hs",
        "Baja,   Prioridad mínima - reparación programada"
    })
    public void testPrioridadesValidas(String prioridad, String descripcion) {
        Denuncia denuncia = new Denuncia("D003", new Date(),
            "Calle Test", "Calle Demo", "Test de prioridad: " + descripcion, prioridad);
        
        assertTrue(denuncia.esPrioridadValida(), 
            "La prioridad '" + prioridad + "' debe ser válida (esPrioridadValida() = true)");
    }

 
    @Test
    public void testContarDenunciasPorSemaforo() {
        Denuncia denuncia1 = new Denuncia("D004", new Date(),
            "Av. San Martín", "Córdoba", "No funciona", "Alta");
        denuncia1.setSemaforo(semaforoDefault);
        denuncia1.setDenunciante(personaDefault);
        semaforoDefault.addDenuncia(denuncia1);
        
        Denuncia denuncia2 = new Denuncia("D005", new Date(),
            "Av. San Martín", "Córdoba", "Parpadea", "Media");
        denuncia2.setSemaforo(semaforoDefault);
        denuncia2.setDenunciante(personaDefault);
        semaforoDefault.addDenuncia(denuncia2);
        
        Denuncia denuncia3 = new Denuncia("D006", new Date(),
            "Av. San Martín", "Córdoba", "Apagado", "Baja");
        denuncia3.setSemaforo(semaforoDefault);
        denuncia3.setDenunciante(personaDefault);
        semaforoDefault.addDenuncia(denuncia3);
        
        int totalDenuncias = service.obtenerCantidadDenunciasSemaforo(semaforoDefault);
        
        assertEquals(3, totalDenuncias, 
            "El semáforo debe tener exactamente 3 denuncias registradas en el historial");
    }
}