package ejem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBD {

    
    private static final String SERVIDOR  = "localhost";
    private static final String PUERTO    = "3306";
    private static final String BD        = "mday_bd";   
    private static final String USUARIO   = "root";
    private static final String PASSWORD  = "";                 
    // -------------------------------------------------------

    private static ConexionBD instancia = null;
    private Connection conexion = null;

    
    private ConexionBD() {}

    
    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    /**
     * Abre la conexión con la base de datos si no estaba ya abierta.
     * @return true si la conexión se estableció correctamente.
     */
    public boolean abrirConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = String.format(
                    "jdbc:mysql://%s:%s/%s",
                    SERVIDOR, PUERTO, BD
                );
                conexion = DriverManager.getConnection(url, USUARIO, PASSWORD);
                System.out.println("[BD] Conexión abierta con " + BD);
            }
            return true;
        } catch (SQLException e) {
            System.err.println("[BD] Error al conectar: " + e.getLocalizedMessage());
            System.err.println("[BD] SQLState: "    + e.getSQLState());
            System.err.println("[BD] Código error: " + e.getErrorCode());
            return false;
        } catch (ClassNotFoundException e) {
            System.err.println("[BD] Driver no encontrado: " + e.getMessage());
            return false;
        }
    }

    /**
     * Devuelve el objeto Connection para ejecutar sentencias SQL.
     * Llama a abrirConexion() si es necesario.
     */
    public Connection getConexion() {
        abrirConexion();
        return conexion;
    }

    /** Cierra la conexión con la base de datos. */
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("[BD] Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("[BD] Error al cerrar: " + e.getLocalizedMessage());
        }
    }
}
