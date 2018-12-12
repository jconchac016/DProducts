/**
 * Clase que da soporte a comentarios sobre productos. Los comentarios contienen el nombre completo de su autor,
 * una breve reseña sobre el producto y una calificiación en el rango [1,5]
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class Comentario {

    private Cliente autor;                                                  // Cliente que publica el comentario
    private String texto;                                                   // Cuerpo del comentario
    private int puntuacion;                                                 // Calificación que le dio el usuario. Es un valor en el rango [1,5]

    /**
     * Constructor parametrizado de la clase. Genera un comentario a partir de un autor, un cuerpo del comentario y una puntuación.
     * La puntuación debe estar entre 1 y 5 (ambos inclusive) y el cuerpo del comentario no puede estar vacío
     *
     * @param autor      Nombre completo del cliente que publica el comentario
     * @param texto      Cuerpo del comentario
     * @param puntuacion Calificación del producto. Es un valor en el rango [1,5]
     * @throws IllegalArgumentException Si cualquierda de los parámetros son inválidos
     */
    public Comentario(Cliente autor, String texto, int puntuacion) {
        if (!esCorrecto(texto, puntuacion))
            throw new IllegalArgumentException("ERROR al publicar un comentario." +
                    " Compruebe que el cuerpo del comentario contenga texto" +
                    " y que la puntuación esté en el rango [1,5]");

        this.autor = autor;
        this.texto = texto;
        this.puntuacion = puntuacion;
    }

    /**
     * Método accesor del atributo 'autor'
     *
     * @return Autor del comentario
     */
    public Cliente getAutor() {
        return autor;
    }

    /**
     * Método accesor del atributo 'texto'
     *
     * @return Texto del comentario
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Método accesor del atributo 'puntuacion'
     *
     * @return Puntuación del comentario
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Método mutador del atributo 'autor'
     *
     * @param autor Nuevo autor del comentario
     */
    public void setAutor(Cliente autor) {
        this.autor = autor;
    }

    /**
     * Método mutador del atributo 'texto'
     *
     * @param texto Nuevo texto del comentario. Debe ser correcto
     * @return Si el cambio fue aceptado
     */
    public boolean setTexto(String texto) {
        boolean esCorrecto = esCorrecto(texto, getPuntuacion());

        if (esCorrecto)
            this.texto = texto;

        return esCorrecto;
    }

    /**
     * Método mutador del atributo 'puntuacion'
     *
     * @param puntuacion Nueva puntuación del comentario. Debe ser correcto
     * @return Si el cambio fue aceptado
     */
    public boolean setPuntuacion(int puntuacion) {
        boolean esCorrecto = esCorrecto(getTexto(), puntuacion);

        if (esCorrecto)
            this.puntuacion = puntuacion;

        return esCorrecto;
    }

    /**
     * @param texto      Cuerpo del comentario. No puede estar vacío
     * @param puntuacion Valoración del comentario. Debe ser un valor en el rango [1,5]
     * @return Si es valido el comentario
     */
    private boolean esCorrecto(String texto, int puntuacion) {
        return texto.replaceAll("\\s+", "").length() != 0 &&
                puntuacion >= 1 && puntuacion <= 5;
    }

    /**
     * Devuelve una cadena formateada con todos los detalles del comentario
     *
     * @return Cadena con el contenido del comentario formateado
     */
    @Override
    public String toString() {
        return "Autor : " + getAutor().getNombre() +
                "\nCalificación " + "*****".substring(0, getPuntuacion()) +
                "\n\tReseña :\n" + getTexto();
    }

}