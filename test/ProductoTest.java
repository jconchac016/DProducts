import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase Producto.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class ProductoTest {

    // Fixture de objetos Producto de prueba
    private static Producto producto;

    @Before
    public void setUp() {
        producto = new Producto("Nombre", 30, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA,
                new GregorianCalendar(2011, 3, 26), true);
    }

    /**
     * Testeo del método accesor del atribtuo 'nombre'
     */
    @Test
    public void getNombre() {
        assertEquals("Nombre", producto.getNombre());
    }

    /**
     * Testeo del método accesor del atribtuo 'identificador'
     */
    @Test
    public void getIdentificador() {
        assertNotNull(producto.getIdentificador());
    }

    /**
     * Testeo del método accesor del atribtuo 'cantidad'
     */
    @Test
    public void getCantidad() {
        assertEquals(30, producto.getCantidad());
    }

    /**
     * Testeo del método accesor del atribtuo 'stockMínimo'
     */
    @Test
    public void getStockMinimo() {
        assertEquals(25, producto.getStockMinimo());
    }

    /**
     * Testeo del método accesor del atribtuo 'prioridad'
     */
    @Test
    public void getPrioridad() {
        assertEquals(PRIORIDAD_PRODUCTO.MEDIA, producto.getPrioridad());
    }

    /**
     * Testeo del método accesor del atribtuo 'fabricante'
     */
    @Test
    public void getFrabricante() {
        assertEquals(FABRICANTES.ACER, producto.getFrabricante());
    }

    /**
     * Testeo del método accesor del atribtuo 'fechaLanzamiento'
     */
    @Test
    public void getFechaLanzamiento() {
        assertEquals(new GregorianCalendar(2011, 3, 26), producto.getFechaLanzamiento());
    }

    /**
     * Testeo del método accesor del atribtuo 'esReacondicionado'
     */
    @Test
    public void getEsReacondicionado() {
        assertTrue(producto.getEsReacondicionado());
    }

    /**
     * Testeo del método 'pedir()'. Comprueba que solo se puedan pedir cantidades positivas distintas de 0
     * y que haya stock suficiente para cubrirlo. También comprueba que se reponga el stock si su cantidad actual
     * es menor que el stock mínimo
     */
    @Test
    public void pedir() {
        assertFalse(producto.pedir(-1));
        assertFalse(producto.pedir(9999));
        assertTrue(producto.pedir(5));
        assertEquals(25, producto.getCantidad());
        assertTrue(producto.pedir(1));
        assertEquals(99, producto.getCantidad());
    }

    /**
     * Testeo del método 'comentar()'. Comprueba que un mismo autor solo pueda publicar un comentario sobre un producto
     */
    @Test
    public void comentar() {
        Comentario comentario = new Comentario(new Cliente("Nombre", 18, "Localidad"),
                "Test", 5);

        assertTrue(producto.comentar(comentario));
        assertFalse(producto.comentar(comentario));
    }

    /**
     * Testeo del método 'haySuficienteStock()'. Comprueba que solo se acepten cantidades válidas en la venta de productos
     */
    @Test
    public void haySuficienteStock() {
        assertFalse(producto.haySuficienteStock(50));
        assertFalse(producto.haySuficienteStock(-1));
        assertTrue(producto.haySuficienteStock(25));
    }

    /**
     * Testeo del método 'detalles()'. Comprueba que devuleva una cadena formateada con la información del producto
     */
    @Test
    public void detalles() {
        assertEquals("PRODUCTO\tNombre-ACER\n" +
                "\tIdentificador : X5P12\n" +
                "\tCantidad en stock : 30\n" +
                "\tCantidad en stock mínima : 25\n" +
                "\tPrioridad de reabastecimiento : MEDIA\n" +
                "\tFecha de lanzamiento : 2011/04/26\n" +
                "\tEstado : Reacondicionado\n" +
                "\tComentarios : \n", producto.detalles());
    }
}