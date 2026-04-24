package ejem1;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * POJO que representa la tabla "perfiles".
 *
 * Columnas: nombreUsuario | links | biografia
 */
@XmlRootElement
public class Perfil {

    private String nombreUsuario;   // clave primaria y foránea → usuarios
    private String links;           // puede ser JSON de links, URL separadas por coma, etc.
    private String biografia;

    // Constructor vacío obligatorio para JAXB
    public Perfil() {}

    public Perfil(String nombreUsuario, String links, String biografia) {
        this.nombreUsuario = nombreUsuario;
        this.links         = links;
        this.biografia     = biografia;
    }

    // ---------- Getters y Setters ----------

    public String getNombreUsuario() { return nombreUsuario; }
    public void   setNombreUsuario(String v) { this.nombreUsuario = v; }

    public String getLinks()         { return links; }
    public void   setLinks(String v)         { this.links = v; }

    public String getBiografia()     { return biografia; }
    public void   setBiografia(String v)     { this.biografia = v; }
}
