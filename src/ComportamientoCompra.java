import java.util.Set;

/**
 * Interfaz que modela el comportamiento de compra en función del tipo de cliente que lo realiza
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public interface ComportamientoCompra {

    /**
     * Realiza el pedido solicitado por el cliente
     *
     * @return Booleano indicando el éxito del pedido
     */
    boolean realizarPedido(Cliente cliente);

    /**
     * Devuelve el importe del pedido a realizar, considerando la variacion de descuentos
     *
     * @param cliente El cliente que realiza el pedido
     * @return Precio del pedido
     */
    float calcularPrecio(Cliente cliente, Set<Producto> pedido);

    /**
     * Devuelve el conjunto de productos que se seleccionan para el pedido
     *
     * @param cliente El cliente que realiza el pedido
     * @return El conjunto de productos del pedido
     */
    Set<Producto> prepararPedido(Cliente cliente);

}