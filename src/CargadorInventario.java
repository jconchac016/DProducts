import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

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

    private SAXParser parser;                                   // Parseador SAX de documentos XML
    private File ficheroDatos;                                  // Ruta al fichero XML de datos de entrada
    private boolean estadoIlegal;                               // Bandera que indica si ocurri� alg�n error de configuraci�n

    /**
     * Constructor parametrizado de la clase. Inicializa el constructor de documentos, dej�ndolo en estado listo
     * para la carga de datos
     *
     * @param ficheroDatos Contiene la informaci�n acerca del fichero de datos de entrada
     */
    public CargadorInventario(File ficheroDatos) {
        SAXParserFactory SAXBuilderFactory = SAXParserFactory.newInstance();
        this.ficheroDatos = ficheroDatos;
        estadoIlegal = false;                                   // Estado legal inicial
        try {
            parser = SAXBuilderFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            reportarError("ERROR al construir el parseador XML\n" + e.getMessage());
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
        if (estadoIlegal)                                        // Comprueba que el cargador est� bien inicializado
            throw new IllegalStateException("Ocurri� un problema al inicializar el cargador del inventario");

        int cargaCorrecta = 0;                                   // Bandera que indica si ocurri� alg�n error en la carga
        boolean insercionCorrecta = false;                       // Bandera para indicar si ocurri� alg�n error en la inserci�n

        // *****    LECTURA DE DATOS    *****

        ManejadorSAXParser manejadorSAXParser = new ManejadorSAXParser();

        try {
            parser.parse(ficheroDatos, manejadorSAXParser);
        } catch (SAXException e) {
            reportarError("ERROR parsando el documento XML\n" + e.getMessage());
        } catch (IOException e) {
            reportarError("ERROR al abrir el fichero de datos de entrda. Compruebe la ruta el archivo y sus permisos\n" +
                    e.getMessage());
        }

//        if (cargaCorrecta < 0) {                                 // Comprueba que no haya ocurrido ning�n error hasta ahora
//            reportarError("ERROR. Algo fue mal en la carga de datos");
//            return false;
//        }
//
//        // *****    CARGA DE DATOS      *****
//
//        Inventario inventario = Inventario.recuperarInstancia();
//        // Carga de los productos en el inventario
//        for (Producto producto : productos.values()) {
//            insercionCorrecta = inventario.agregarProducto(producto);
//        }
//        if (!insercionCorrecta) {
//            reportarError("ERROR. No se pudieron a�adir todos los productos al inventario");
//            return false;                                      // Alg�n producto no se pudo a�adir al inventario
//        }
//
//        // Carga de los clientes en el inventario
//        for (Cliente cliente : clientes.values()) {
//            insercionCorrecta = inventario.agregarCliente(cliente);
//        }
//        if (!insercionCorrecta) {
//            reportarError("ERROR. No se pudieron a�adir todos los clientes al inventario");
//            return false;                                      // Alg�n cliente no se pudo a�adir al inventario
//        }
//
        return true;                                           // Todos los datos se cargaron correctamente
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