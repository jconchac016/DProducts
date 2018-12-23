import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase encargada de cargar desde un fichero xml bien formado (sigue el DTD) colecciones de productos
 * y clientes en el inventario
 *
 * @author : Juan Pablo Garc�a Plaza P�rez - Jose �ngel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2� GIIIS (Grupo A)
 */
public class CargadorInventario {

    private static final String XML_TAG_PRODUCTOS = "stock";    // Tag XML de la colecci�n de productos
    private static final String XML_TAG_CLIENTES = "clients";   // Tag XML de la colecci�n de productos
    private static final String XML_TAG_FAVORITOS = "favorites";// Tag XML de la colecci�n de productos

    // Instancia que representa el documento XML de entrada
    private Document datosEntrada;

    /*
     * Colecci�n de productos parseados. Son indexados por nombre para asociarlos a los clientes
     * que lo han incluido como favorito
     */
    private Map<String, Producto> productos;

    // Colecci�n de clientes parseados. Son indexados por nombre para asociarlos a sus productos favoritos
    private Map<String, Cliente> clientes;

    // Bandera que indica si ocurri� alg�n error de configuraci�n inicial
    private boolean estadoIlegal;

    /**
     * Constructor parametrizado de la clase. Crea un documento XML a partir de un fichero de entrada
     *
     * @param ficheroDatos Contiene la informaci�n acerca del fichero de datos de entrada
     */
    public CargadorInventario(File ficheroDatos) {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        productos = new HashMap<>();
        clientes = new HashMap<>();
        estadoIlegal = false;                                   // Estado legal inicial
        try {
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            datosEntrada = docBuilder.parse(ficheroDatos);      // Inicializaci�n del fichero XML de entrada
            datosEntrada.getDocumentElement().normalize();      // Inicializaci�n de la ra�z del DOM
        } catch (ParserConfigurationException e) {
            reportarError("ERROR al construir un parseador XML\n" + e.getMessage());
            estadoIlegal = true;
        } catch (SAXException e) {
            reportarError("ERROR parsando el documento XML\n" + e.getMessage());
            estadoIlegal = true;
        } catch (IOException e) {
            reportarError("ERROR al abrir el fichero de datos de entrda. Compruebe la ruta el archivo y sus permisos\n" +
                    e.getMessage());
            estadoIlegal = true;
        }
    }

    /**
     * Carga todos los datos le�dos del fichero (productos, clientes y productos favoritos)
     * de entrada en la instancia de inventario
     *
     * @return Verdadero si el documento XML es v�lido y todos los datos fueron cargardos correctamente.
     * Falso en caso de que el documento XML no se pueda validar o alg�n dato estaba mal formado
     * @throws IllegalStateException Si el cargador no fue correctamente incializado
     */
    public boolean cargarDatos() {
        if (estadoIlegal)                                       // Comprueba que el cargador est� bien inicializado
            throw new IllegalStateException("Ocurri� un problema al inicializar el cargador del inventario");

        int cargaCorrecta = 0;                                   // Bandera que indica si ocurri� alg�n error en la carga
        boolean insercionCorrecta = false;                       // Bandera para indicar si ocurri� alg�n error en la inserci�n

        // *****    LECTURA DE DATOS    *****

        cargaCorrecta += cargarProductos();                      // Intenta cargar los productos en el inventario
        cargaCorrecta += cargarClientes();                       // Intenta cargar los clientes en el inventario
        cargaCorrecta += cargarProductosFavoritos();             // Intenta relacionar los favoritos con sus clientes

        if (cargaCorrecta < 0) {                                 // Comprueba que no haya ocurrido ning�n error hasta ahora
            reportarError("ERROR. Algo fue mal en la carga de datos");
            return false;
        }

        // *****    CARGA DE DATOS      *****

        Inventario inventario = Inventario.recuperarInstancia();
        // Carga de los productos en el inventario
        for (Producto producto : productos.values()) {
            insercionCorrecta = inventario.agregarProducto(producto);
        }
        if (!insercionCorrecta) {
            reportarError("ERROR. No se pudieron a�adir todos los productos al inventario");
            return false;                                      // Alg�n producto no se pudo a�adir al inventario
        }

        // Carga de los clientes en el inventario
        for (Cliente cliente : clientes.values()) {
            insercionCorrecta = inventario.agregarCliente(cliente);
        }
        if (!insercionCorrecta) {
            reportarError("ERROR. No se pudieron a�adir todos los clientes al inventario");
            return false;                                      // Alg�n cliente no se pudo a�adir al inventario
        }

        return true;                                           // Todos los datos se cargaron correctamente
    }

    /**
     * Parsea la colecci�n de productos contenida en el documento XML de datos de entrada
     *
     * @return 0 si todos los productos pudieron ser correctamente parseados. -1 en otro caso
     * @throws IllegalStateException Si el cargador no fue correctamente incializado
     */
    private int cargarProductos() {
        if (estadoIlegal)                                       // Comprueba que el cargador est� bien inicializado
            throw new IllegalStateException("Ocurri� un problema al inicializar el cargador del inventario");

        // Bandera para indicar su ocurri� alg�n error en la carga de productos
        boolean cargaCorrecta = true;
        // Colecci�n de productos en formato XML
        NodeList stock = datosEntrada.getElementsByTagName(XML_TAG_PRODUCTOS);

        Element productoXML;

        Producto producto;
        TIPOS_PRODUCTO categoria;

        // TODO - Implementar la carga de productos

        return cargaCorrecta ? 0 : -1;
    }

    /**
     * Parsea la colecci�n de clientes contenida en el documento XML de datos de entrada
     *
     * @return 0 si todos los clientes fueron parseados correctamente. -1 en otro caso
     */
    private int cargarClientes() {
        // TODO - implement CargadorInventario.cargarClientes
        return -1;
    }

    /**
     * Realiza las relaciones entre clientes y sus productos favoritos indicadas en el documento XML de entrada
     *
     * @return 0 si todas las relaciones tienen referencias correctas y el alias
     * no se repite en el mismo cliente. -1 en otro caso
     */
    private int cargarProductosFavoritos() {
        // TODO - implement CargadorInventario.cargarProductosFavoritos
        return -1;
    }

    /**
     * Reporta un error al usuario por consola
     *
     * @param mensaje Mensaje de error
     */
    private void reportarError(String mensaje) {
        System.out.println(mensaje);
    }

}