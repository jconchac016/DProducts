public class ClienteEstandar extends Cliente {

    private ComportamientoCompra comportamientoCompra;
    private ComportamientoComentario comportamientoComentario;

    /**
     * @param nombre
     * @param edad
     * @param localidad
     */
    public ClienteEstandar(String nombre, int edad, String localidad) {
        super(nombre, edad, localidad);
        // TODO - implement ClienteEstandar.ClienteEstandar
    }

    @Override
    public boolean realizarPedido() {
        return false;
    }

    @Override
    public boolean comentarProducto(String alias, String texto, int puntuacion) {
        return false;
    }

}