package api;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * POJO que representa la tabla "imagenes".
 *
 * Columnas: nombreImagen | nombreUsuario | imagen (Base64)
 */
@XmlRootElement
public class Imagen {

    private String nombreImagen;
    private String nombreUsuario;   // clave foránea → usuarios
    /**
     * El campo imagen es LONGBLOB en la BD.
     * Se transfiere como Base64 para compatibilidad con JSON.
     */
    private String imagen;          // Base64

    // Constructor vacío obligatorio para JAXB
    public Imagen() {}

    public Imagen(String nombreImagen, String nombreUsuario, String imagen) {
        this.nombreImagen  = nombreImagen;
        this.nombreUsuario = nombreUsuario;
        this.imagen        = imagen;
    }

    // ---------- Getters y Setters ----------

    public String getNombreImagen()  { return nombreImagen; }
    public void   setNombreImagen(String v)  { this.nombreImagen = v; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void   setNombreUsuario(String v) { this.nombreUsuario = v; }

    public String getImagen()        { return imagen; }
    public void   setImagen(String v)        { this.imagen = v; }
}
