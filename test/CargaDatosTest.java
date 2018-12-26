import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * CLASE DE TESTEO de la carga de datos (Clases {@link CargadorInventario}, {@link ManejadorSAXParser})
 * <p>
 * Comprueba, con un documento de entrada más pequeño, que se parseen y carguen en el inventario todos los datos
 * correctamente
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CargaDatosTest {

    private static final String FICHERO_DATOS_PRUEBA = "init_test.xml";

    // Fixture de cargadores de prueba
    private static CargadorInventario cargadorInventario;

    // Fixture de productos de prueba
    private static Producto producto1;
    private static Producto producto2;
    private static Producto producto3;

    // Fixture de clientes de prueba
    private static Cliente cliente1;
    private static Cliente cliente2;

    // Colecciones de objetos leidos
    private static Map<String, Producto> productosLeidos;
    private static HashMap<String, Cliente> clientesLeidos;

    @BeforeClass
    public static void setUp() throws ExcepcionCargadorEntrada {
        cargadorInventario = new CargadorInventario(new File(FICHERO_DATOS_PRUEBA));

        producto1 = new ProductoOcio("Nintendo_Switch", 4, 300, 2, FABRICANTES.NINTENDO,
                PRIORIDAD_PRODUCTO.BAJA);
        producto2 = new ProductoOcio("Xbox_One", 5, 220, 2, FABRICANTES.MICROSOFT,
                PRIORIDAD_PRODUCTO.BAJA);
        producto3 = new ProductoOcio("Samsung_Galaxy_Tablet", 50, 180, 8, FABRICANTES.SAMSUNG,
                PRIORIDAD_PRODUCTO.MEDIA);
        cliente1 = new ClienteEstandar("Laia_Palau", 39, "Barcelona");
        cliente2 = new ClienteEstandar("Carolina_Marin", 25, "Huelva");
        productosLeidos = new HashMap<>();
        clientesLeidos = new HashMap<>();
    }

    /**
     * Testeo de la lectura de datos del fichero de datos de entrada. Comprueba que los productos y clientes instanciados
     * a partir de los datos leídos (identificados por su nombre) se correspondan con los de prueba
     */
    @Test
    public void aLecturaDatos() {
        try {
            cargadorInventario.lecturaDatos();                          // Lee los datos de entrada de prueba
        } catch (ExcepcionCargadorEntrada ignored) {
        }

        // Comprobación de productos leídos
        Iterator<Producto> itProductos = cargadorInventario.getManejadorSAXParser().getIteradorProductosParseados();
        Producto producto;
        while (itProductos.hasNext()) {
            producto = itProductos.next();
            productosLeidos.put(producto.getNombre(), producto);
        }
        assertTrue(productosLeidos.containsKey(producto1.getNombre()));
        assertTrue(productosLeidos.containsKey(producto2.getNombre()));
        assertTrue(productosLeidos.containsKey(producto3.getNombre()));

        // Comprobación de clientes leídos
        Iterator<Cliente> itClientes = cargadorInventario.getManejadorSAXParser().getIteradorClientesParseados();
        Cliente cliente;
        while (itClientes.hasNext()) {
            cliente = itClientes.next();
            clientesLeidos.put(cliente.getNombre(), cliente);
        }
        assertTrue(clientesLeidos.containsKey(cliente1.getNombre()));
        assertTrue(clientesLeidos.containsKey(cliente2.getNombre()));
    }

    /**
     *
     */
    @Test
    public void cargarDatos() {
        try {
            cargadorInventario.cargarDatos();                           // Carga los datos leidos en inventario
        } catch (ExcepcionCargadorEntrada ignored) {
        }

        Inventario inventario = Inventario.recuperarInstancia();

        // Comprobación de productos cargados
        for (Producto producto : productosLeidos.values()) {
            assertNotNull(inventario.recuperarProducto(producto.getIdentificador()));
        }

        // Comprobación de clientes cargados. Debe ser posible eliminarlo si existe
        for (Cliente cliente : clientesLeidos.values()) {
            assertTrue(inventario.eliminarCliente(cliente.getIdentificador()));
        }

        // Comprobación de producto favoritos. El cliente 'Rafa_Nadal' ha guardado bajo el alias 'rafanint' el produto 'Nintendo_Switch'
        Producto nintendoSwitch = productosLeidos.get(producto1.getNombre());
        Cliente rafaNadal = clientesLeidos.get(cliente1.getNombre());
        assertTrue(rafaNadal.existeAliasFavorito("laianint"));
        assertTrue(rafaNadal.existeProductoFavorito(nintendoSwitch));
    }

}