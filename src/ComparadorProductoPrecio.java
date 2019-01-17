import java.util.Comparator;

/**
 * Clase que implementa la interfaz Comparator para ordenar productos por su precio en
 * orden descendente
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class ComparadorProductoPrecio implements Comparator<Producto> {

    /**
     * Compara dos objetos Prodcucto según su precio
     *
     * @param p1 Primer producto
     * @param p2 Segundo producto
     * @return 1 sii p1 {@literal <} p2
     * <p>
     * 0 sii p1 == p2
     * <p>
     * -1 sii p1 {@literal >} p2
     */
    public int compare(Producto p1, Producto p2) {
        if (p1.getPrecio() < p2.getPrecio())
            return 1;
        else if (p1.getPrecio() == p2.getPrecio())
            return 0;
        else
            return -1;
    }

}