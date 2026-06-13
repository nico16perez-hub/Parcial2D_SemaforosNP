import com.mycompany.parcial2d_semaforos.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GestionSemaforosTest {

    private GestionSemaforosService service;
    private Semaforo semaforoDefault;
    private Persona personaDefault;
    private Equipo_Control equipoDefault;

    @BeforeAll
    public static void setupAll() {
        System.out.println("=== INICIO DE SUITE DE PRUEBAS ===");
    }

    @BeforeEach
    public void setup() {

        service = new GestionSemaforosService();

        semaforoDefault = new Semaforo(
                1,
                "Descompuesto",
                "Av. San Martín y Córdoba",
                "Smart LED");

        personaDefault = new Persona(
                "María González",
                "maria@email.com");

        equipoDefault = new Equipo_Control(
                "EQ001",
                "Electricidad",
                "Libre");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Test finalizado correctamente");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("=== FIN DE SUITE DE PRUEBAS ===");
    }

    @Test
    public void testComposicionSemaforoTresLuces() {

        List<Luz> luces = semaforoDefault.getLuces();

        assertEquals(
                3,
                luces.size(),
                "El semáforo debe tener exactamente 3 luces");

        Luz luz0Interna = luces.get(0);
        Luz luz0Obtenida = semaforoDefault.getLuz(0);

        assertSame(
                luz0Interna,
                luz0Obtenida,
                "Debe ser la misma instancia");
    }

    @Test
    @Timeout(value = 400, unit = TimeUnit.MILLISECONDS)
    public void testAsignarOrdenADenunciaConOrdenExistente() {

        Denuncia denuncia = new Denuncia(
                "D001",
                new Date(),
                "Av. San Martín",
                "Córdoba",
                "No funciona luz roja",
                "Alta");

        Orden_Composicion orden1 =
                new Orden_Composicion(
                        "OC001",
                        new Date(),
                        "Primera reparación");

        denuncia.setOrden(orden1);

        Orden_Composicion orden2 =
                new Orden_Composicion(
                        "OC002",
                        new Date(),
                        "Segunda reparación");

        assertThrows(
                OrdenYaAsignadaException.class,
                () -> service.asignarOrden(denuncia, orden2));
    }

    @Test
    public void testFinalizarReparacionLiberaEquipoYMiembros() {

        Orden_Composicion orden =
                new Orden_Composicion(
                        "OC003",
                        new Date(),
                        "Cambio de luces");

        equipoDefault.asignarOrden(orden);

        assertEquals(
                "Ocupado",
                equipoDefault.getEstado());

        orden.actualizarReparacion(new Date());

        equipoDefault.liberarEquipo();

        assertEquals(
                "Libre",
                equipoDefault.getEstado());

        for (Miembro miembro : equipoDefault.getMiembros()) {

            assertTrue(
                    miembro.isLibre(),
                    "Todos los miembros deben quedar libres");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "Alta",
        "Media",
        "Baja"
    })
    public void testPrioridadesValidas(String prioridad) {

        Denuncia denuncia =
                new Denuncia(
                        "D010",
                        new Date(),
                        "A",
                        "B",
                        "Problema",
                        prioridad);

        assertTrue(
                denuncia.esPrioridadValida());
    }

    @Test
    public void testContarDenunciasPorSemaforo() {

        for (int i = 1; i <= 3; i++) {

            Denuncia denuncia =
                    new Denuncia(
                            "D00" + i,
                            new Date(),
                            "Av. San Martín",
                            "Córdoba",
                            "Problema " + i,
                            "Alta");

            denuncia.setSemaforo(semaforoDefault);

            semaforoDefault.addDenuncia(denuncia);
        }

        assertEquals(
                3,
                service.obtenerCantidadDenunciasSemaforo(semaforoDefault));
    }
     }