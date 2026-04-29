package api;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * POJO que representa la tabla "usuarios".
 *
 * Columnas: nombreUsuario | fotoperfil (Base64) | email | contrasena
 *
 * @XmlRootElement permite que JAX-RS serialice/deserialice
 *                 esta clase a XML o JSON de forma automática.
 */
@XmlRootElement
public class Usuario {

    private String nombreUsuario;
    
    private String email;
    private String contrasena;

    // Constructor vacío obligatorio para JAXB
    public Usuario() {}

    public Usuario(String nombreUsuario,
                   String email, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.email         = email;
        this.contrasena    = contrasena;
    }

    // ---------- Getters y Setters ----------

    public String getNombreUsuario()  { return nombreUsuario; }
    public void   setNombreUsuario(String v) { this.nombreUsuario = v; }

    public String getEmail()          { return email; }
    public void   setEmail(String v)         { this.email = v; }

    public String getContrasena()     { return contrasena; }
    public void   setContrasena(String v)    { this.contrasena = v; }
}
