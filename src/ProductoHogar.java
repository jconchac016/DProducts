import java.util.GregorianCalendar;

public class ProductoHogar extends ProductoComentable implements Gustable, Descontable {

    private PARTES_CASA parteCasa;

    /**
     * @param nombre
     * @param cantidad
     * @param precio
     * @param stockMinimo
     * @param fabricante
     * @param prioridad
     * @param fechaLanzamiento
     * @param esReacondicionado
     * @param parteCasa
     */
    public ProductoHogar(String nombre, int cantidad, float precio, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad, GregorianCalendar fechaLanzamiento, boolean esReacondicionado, PARTES_CASA parteCasa) {
        super(nombre, cantidad, stockMinimo, fabricante, prioridad, fechaLanzamiento, esReacondicionado);
        // TODO - implement ProductoHogar.ProductoHogar
    }

    public String toString() {
        // TODO - implement ProductoHogar.toString
        return null;
    }

    /**
     * @param obj
     */
    public boolean equals(Object obj) {
        // TODO - implement ProductoHogar.equals
        return false;
    }

    public int hashCode() {
        // TODO - implement ProductoHogar.hashCode
        return 0;
    }

    @Override
    public void calcularPrecioDescontado() {

    }

    @Override
    public float getDescuento() {
        return 0;
    }

    @Override
    public void setDescuento() {

    }

    @Override
    public int getLikes() {
        return 0;
    }

    @Override
    public void like() {

    }

    @Override
    public void unlike() {

    }
}