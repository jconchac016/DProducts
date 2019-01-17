import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * CLASE DE TESTEO de la clase {@link ComportamientoComentarioEstandar}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos y protegidos de la clase.
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */
public class ComportamientoComentarioEstandarTest {

    // Fixture de clientes estandar de prueba
    private static ClienteEstandar clienteUno;
    // Fixture de productos de prueba
    private static Producto producto;
    // Fixture de comportamientos de clientes estandar
    private static ComportamientoComentarioEstandar comentarioEstandar = new ComportamientoComentarioEstandar();

    @Before
    public void setUp() {
        clienteUno = new ClienteEstandar("Pepe", 57, "Caceres");
        clienteUno.setComportamientoComentario(comentarioEstandar);
        producto = new ProductoOcio("Auriculares", 140, 29.95f, 70, FABRICANTES.AOC, PRIORIDAD_PRODUCTO.BAJA);
    }

    /**
     * Testo del método {@link ComportamientoComentarioEstandar#comentar(Producto, Cliente)}
     */
    @Test
    public void comentar() {
        assertTrue(clienteUno.getComportamientoComentario().comentar(producto, clienteUno));
    }

    /**
     * Testo del método {@link ComportamientoComentarioEstandar#calcularPuntuacion(Producto)}
     */
    @Test
    public void calcularPuntuacion() {
        assertEquals(clienteUno.getComportamientoComentario().calcularPuntuacion(producto), (producto.getNombre().length() % 5) + 1);
    }

    /**
     * Testo del método {@link ComportamientoComentarioEstandar#obtenerTexto(Producto)}
     */
    @Test
    public void obtenerTexto() {
        assertEquals(clienteUno.getComportamientoComentario().obtenerTexto(producto), "Good product");
    }

}
