public class ProductoOcio extends ProductoComentable implements Descontable {

    /**
     * @param nombre
     * @param cantidad
     * @param precio
     * @param stockMinimo
     * @param fabricante
     * @param prioridad
     */
    public ProductoOcio(String nombre, int cantidad, float precio, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad) {
        super(nombre, cantidad, precio, stockMinimo, fabricante, prioridad);
        // TODO - implement ProductoOcio.ProductoOcio
    }

    public String toString() {
        // TODO - implement ProductoOcio.toString
        return null;
    }

    /**
     * @param obj
     */
    public boolean equals(Object obj) {
        // TODO - implement ProductoOcio.equals
        return false;
    }

    public int hashCode() {
        // TODO - implement ProductoOcio.hashCode
        return 0;
    }

    @Override
    public float calcularPrecioDescontado() {
        // TODO - implement
        return 0f;
    }

    @Override
    public float getDescuento() {
        // TODO - implement
        return 0;
    }

    @Override
    public void setDescuento(float descuento) {
        // TODO - implement
    }
}