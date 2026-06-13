


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


import com.mycompany.parcial2d_semaforos.DENUNCIA;
import com.mycompany.parcial2d_semaforos.EQUIPO_CONTROL;
import com.mycompany.parcial2d_semaforos.LUZ;
import com.mycompany.parcial2d_semaforos.MIEMBRO;
import com.mycompany.parcial2d_semaforos.ORDEN_COMPOSICION;
import com.mycompany.parcial2d_semaforos.OrdenYaAsignadaException;
import com.mycompany.parcial2d_semaforos.PERSONA;
import com.mycompany.parcial2d_semaforos.SEMAFORO;

public class GestionSemaforosTest {

    private SEMAFORO semaforoDefault;
    private PERSONA personaDefault;
    private EQUIPO_CONTROL equipoDefault;

  
    @BeforeEach
    public void setup() {
        semaforoDefault = new SEMAFORO(1, "Descompuesto", "Av. San Martín", "Led");
        personaDefault = new PERSONA("María", "maria@email.com");
        equipoDefault = new EQUIPO_CONTROL("EQ001", "Eléctrica", "Libre");
    }

    @AfterEach
    public void teardown() {
        System.out.println(" Finalizada prueba individual");
    }

    @Test
    public void testComposicionSemaforoTresLuces() {
        List<LUZ> luces = semaforoDefault.getLuces();
        assertEquals(3, luces.size(), "El semáforo debe tener exactamente 3 luces");
        
        LUZ luz0Obtenida = semaforoDefault.getLuz(0);
        LUZ luz0Interna = luces.get(0);
        assertSame(luz0Interna, luz0Obtenida, "La luz en posición 0 debe ser la misma instancia");
    }


    @Test
    @Timeout(value = 400, unit = TimeUnit.MILLISECONDS)
    public void testAsignarOrdenADenunciaConOrdenExistente() {
        DENUNCIA denuncia = personaDefault.registrarDenuncia(semaforoDefault, "No funciona", "Alta", "Calle1", "Calle2");
        
        ORDEN_COMPOSICION orden1 = new ORDEN_COMPOSICION("OC001", new Date(), "Reparación");
        denuncia.setOrden(orden1);
        
        ORDEN_COMPOSICION orden2 = new ORDEN_COMPOSICION("OC002", new Date(), "Extra");
        
        assertThrows(OrdenYaAsignadaException.class, () -> {
            denuncia.setOrden(orden2);
        }, "Debe lanzar excepción al intentar asignar una segunda orden");
    }


    @Test
    public void testFinalizarReparacionLiberaEquipoYMiembros() {
        DENUNCIA denuncia = personaDefault.registrarDenuncia(semaforoDefault, "Parpadeo", "Media", "Calle1", "Calle2");
        
        ORDEN_COMPOSICION orden = new ORDEN_COMPOSICION("OC003", new Date(), "Cambio");
        denuncia.setOrden(orden);
        
        equipoDefault.asignarOrden(orden);
        assertEquals("Ocupado", equipoDefault.getEstado());
        
        orden.actualizarReparacion(new Date());
        equipoDefault.liberarEquipo();
        
        assertEquals("Libre", equipoDefault.getEstado(), "El equipo debe quedar Libre");
        
        List<MIEMBRO> miembros = equipoDefault.getMiembros();
        assertEquals(4, miembros.size(), "El equipo debe tener 4 miembros");
        
        for (MIEMBRO miembro : miembros) {
            assertTrue(miembro.isLibre(), "El miembro " + miembro.getNombre() + " debe estar libre");
        }
    }

  
    @ParameterizedTest
    @ValueSource(strings = {"Alta", "Media", "Baja"})
    public void testPrioridadesValidas(String prioridad) {
        DENUNCIA denuncia = new DENUNCIA("D003", new Date(), "Calle1", "Calle2", "Test", prioridad);
        assertTrue(denuncia.esPrioridadValida(), "La prioridad '" + prioridad + "' debe ser válida");
    }

   
    @Test
    public void testContarDenunciasPorSemaforo() {
        personaDefault.registrarDenuncia(semaforoDefault, "No funciona", "Alta", "Calle1", "Calle2");
        personaDefault.registrarDenuncia(semaforoDefault, "Parpadea", "Media", "Calle1", "Calle2");
        personaDefault.registrarDenuncia(semaforoDefault, "Apagado", "Baja", "Calle1", "Calle2");
        
        int totalDenuncias = semaforoDefault.contarDenuncias();
        assertEquals(3, totalDenuncias, "El semáforo debe tener exactamente 3 denuncias registradas");
    }
}