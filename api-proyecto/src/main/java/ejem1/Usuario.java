package ejem1;

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
    /**
     * La foto de perfil se almacena como LONGBLOB en la BD.
     * Se transfiere por la API como cadena Base64 para que sea
     * compatible con JSON y con cualquier tipo de cliente (móvil, web...).
     *
     * Ejemplo en JSON:  "fotoperfil": "iVBORw0KGgoAAAA..."
     */
    private String fotoPerfil;   // Base64
    private String email;
    private String contrasena;

    // Constructor vacío obligatorio para JAXB
    public Usuario() {}

    public Usuario(String nombreUsuario, String fotoPerfil,
                   String email, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.fotoPerfil    = fotoPerfil;
        this.email         = email;
        this.contrasena    = contrasena;
    }

    // ---------- Getters y Setters ----------

    public String getNombreUsuario()  { return nombreUsuario; }
    public void   setNombreUsuario(String v) { this.nombreUsuario = v; }

    public String getFotoPerfil()     { return fotoPerfil; }
    public void   setFotoPerfil(String v)    { this.fotoPerfil = v; }

    public String getEmail()          { return email; }
    public void   setEmail(String v)         { this.email = v; }

    public String getContrasena()     { return contrasena; }
    public void   setContrasena(String v)    { this.contrasena = v; }
}
