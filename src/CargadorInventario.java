import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Clase encargada de cargar desde un fichero xml bien formado y validado colecciones de productos y clientes
 * en el inventario
 *
 * @author : Juan Pablo Garc�a Plaza P�rez - Jose �ngel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2� GIIIS (Grupo A)
 */

public class CargadorInventario {

    private File ficheroDatos;                                  // Ruta al fichero XML de datos de entrada
    private SAXParser parser;                                   // Parseador SAX de documentos XML
    private ManejadorSAXParser manejadorSAXParser;              // Manejador de los eventos del SAXParser
    private boolean estadoIlegal;                               // Bandera que indica si ocurri� alg�n error de configuraci�n
    private boolean lecturaSucia;                               // Bandera que indica si ocurri� alg�n error en la lectura

    /**
     * Constructor parametrizado de la clase. Inicializa el constructor de documentos, dej�ndolo en estado listo
     * para la carga de datos
     *
     * @param ficheroDatos Contiene la informaci�n acerca del fichero de datos de entrada
     */
    public CargadorInventario(File ficheroDatos) {
        SAXParserFactory SAXBuilderFactory = SAXParserFactory.newInstance();
        manejadorSAXParser = new ManejadorSAXParser();
        this.ficheroDatos = ficheroDatos;
        estadoIlegal = false;                                   // Estado legal inicial
        lecturaSucia = true;                                    // No se permite la carga hasta que no se lean los datos
        try {
            parser = SAXBuilderFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            reportarError("ERROR al construir el parseador XML\n" + e.getMessage());
            estadoIlegal = true;
        }
    }

    /**
     * Lee todos los datos del fichero XML (productos, clientes y productos favoritos) de entrada
     *
     * Modifica la bandera de lectura sucia tal que es :  Verdadero si el documento XML es v�lido y todos los datos
     * fueron le�dos correctamente. Falso en caso de que el documento XML no se pueda validar o alg�n dato estaba mal formado
     * @throws IllegalStateException Si el cargador no fue correctamente incializado
     */
    public void lecturaDatos() {
        if (estadoIlegal)                                       // Comprueba que el cargador est� bien inicializado
            throw new IllegalStateException("Ocurri� un problema al inicializar el cargador del inventario");

        try {
            parser.parse(ficheroDatos, manejadorSAXParser);     // Parsea el documento XML
        } catch (SAXException e) {
            reportarError("ERROR parsando el documento XML\n" + e.getMessage());
            lecturaSucia = true;
        } catch (IOException e) {
            reportarError("ERROR al abrir el fichero de datos de entrda. Compruebe la ruta el archivo y sus permisos\n" +
                    e.getMessage());
            lecturaSucia = true;
        }

        if (manejadorSAXParser.getEstado() < 0) {               // Comprueba que no haya ocurrido ning�n error en la carga
            reportarError("ERROR. Algo fue mal en la carga de datos");
            lecturaSucia = true;
            return false;
        }

        lecturaSucia = manejadorSAXParser.getEstado() < 0;     // Actualiza la bandera de lectura
    }

    private boolean cargarDatos() {
        if (estadoIlegal)                                       // Comprueba que el cargador est� bien inicializado
            throw new IllegalStateException("Ocurri� un problema al inicializar el cargador del inventario");

        boolean insercionCorrecta = true;                       // Bandera que indica si hubo error en la carga

        Inventario inventario = Inventario.recuperarInstancia();
        // Carga de los productos en el inventario
        Iterator<Producto> itProductos = manejadorSAXParser.getIteradorProductosParseados();
        while (itProductos.hasNext() && insercionCorrecta) {
            insercionCorrecta = inventario.agregarProducto(itProductos.next());
        }

        if (!insercionCorrecta) {
            reportarError("ERROR. No se pudieron a�adir todos los productos al inventario");
            return false;                                        // Alg�n producto no se pudo a�adir al inventario
        }

        // Carga de los clientes en el inventario
        Iterator<Cliente> itClientes = manejadorSAXParser.getIteradorClientesParseados();
        while (itClientes.hasNext() && insercionCorrecta) {
            insercionCorrecta = inventario.agregarCliente(itClientes.next());
        }

        if (!insercionCorrecta) {
            reportarError("ERROR. No se pudieron a�adir todos los clientes al inventario");
            return false;                                        // Alg�n cliente no se pudo a�adir al inventario
        }

        // Relaciona los clientes con sus productos favoritos
        Iterator<Object[]> itProductosFav = manejadorSAXParser.getIteradorProductosFavParseados();
        Object[] datosRelacion;                                  // Datos del cliente, producto favorito y alias
        while (itProductosFav.hasNext() && insercionCorrecta) {
            datosRelacion = itProductosFav.next();

            Producto producto = (Producto) datosRelacion[0];
            Cliente cliente = (Cliente) datosRelacion[1];
            String alias = (String) datosRelacion[2];

            // Intenta a�adir el producto favorito a su cliente con el alias dado
            insercionCorrecta = cliente.agregarFavorito(producto, alias);
        }

        if (!insercionCorrecta) {
            reportarError("ERROR. No se pudieron relacionar todos los productos favoritos con sus clientes");
            return false;                                        // Alg�n relacion fall�
        }

        return true;                                             // Todos los datos se cargaron correctamente
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