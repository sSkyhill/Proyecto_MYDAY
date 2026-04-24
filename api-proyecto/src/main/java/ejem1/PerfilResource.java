package ejem1;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.sql.*;
import java.util.ArrayList;

/**
 * Recurso REST para la tabla "perfiles".
 *
 * URL base: http://localhost:8080/api-proyecto/rest/perfiles
 *
 * Operaciones:
 *   GET    /perfiles                         → lista todos los perfiles
 *   GET    /perfiles/{nombreUsuario}         → perfil de un usuario concreto
 *   POST   /perfiles                         → crea un nuevo perfil
 *   PUT    /perfiles/{nombreUsuario}         → actualiza un perfil
 *   DELETE /perfiles/{nombreUsuario}         → elimina un perfil
 */
@Path("/perfiles")
public class PerfilResource {

    // ------------------------------------------------------------------
    // GET /perfiles  →  lista completa
    // ------------------------------------------------------------------
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getTodos() {
        ArrayList<Perfil> lista = new ArrayList<>();
        ConexionBD bd = ConexionBD.getInstancia();

        try (Statement st = bd.getConexion().createStatement()) {
            ResultSet rs = st.executeQuery(
                "SELECT nombreUsuario, links, biografia FROM perfiles"
            );
            while (rs.next()) {
                lista.add(mapearFila(rs));
            }
            return Response.ok(lista).build();

        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                           .entity("Error BD: " + e.getLocalizedMessage())
                           .type(MediaType.TEXT_PLAIN).build();
        }
    }

    // ------------------------------------------------------------------
    // GET /perfiles/{nombreUsuario}  →  un perfil
    // ------------------------------------------------------------------
    @GET
    @Path("{nombreUsuario}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getUno(@PathParam("nombreUsuario") String nombreUsuario) {
        ConexionBD bd = ConexionBD.getInstancia();
        String sql = "SELECT nombreUsuario, links, biografia "
                   + "FROM perfiles WHERE nombreUsuario = ?";

        try (PreparedStatement ps = bd.getConexion().prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Response.ok(mapearFila(rs)).build();
            } else {
                return Response.status(Status.NOT_FOUND)
                               .entity("Perfil no encontrado: " + nombreUsuario)
                               .type(MediaType.TEXT_PLAIN).build();
            }
        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                           .entity("Error BD: " + e.getLocalizedMessage())
                           .type(MediaType.TEXT_PLAIN).build();
        }
    }

    // ------------------------------------------------------------------
    // POST /perfiles  →  crear perfil
    // Body JSON ejemplo:
    // {
    //   "nombreUsuario": "juan",
    //   "links": "https://github.com/juan, https://twitter.com/juan",
    //   "biografia": "Hola, soy Juan!"
    // }
    // ------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response crear(Perfil p) {
        if (p.getNombreUsuario() == null || p.getNombreUsuario().isEmpty()) {
            return Response.status(Status.BAD_REQUEST)
                           .entity("El campo 'nombreUsuario' es obligatorio.")
                           .type(MediaType.TEXT_PLAIN).build();
        }

        ConexionBD bd = ConexionBD.getInstancia();
        String sql = "INSERT INTO perfiles (nombreUsuario, links, biografia) "
                   + "VALUES (?, ?, ?)";

        try (PreparedStatement ps = bd.getConexion().prepareStatement(sql)) {
            ps.setString(1, p.getNombreUsuario());
            ps.setString(2, p.getLinks());
            ps.setString(3, p.getBiografia());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return Response.status(Status.CREATED).entity(p).build();
            } else {
                return Response.status(Status.INTERNAL_SERVER_ERROR)
                               .entity("No se pudo insertar el perfil.")
                               .type(MediaType.TEXT_PLAIN).build();
            }
        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                           .entity("Error BD: " + e.getLocalizedMessage())
                           .type(MediaType.TEXT_PLAIN).build();
        }
    }

    // ------------------------------------------------------------------
    // PUT /perfiles/{nombreUsuario}  →  actualizar perfil
    // ------------------------------------------------------------------
    @PUT
    @Path("{nombreUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response actualizar(@PathParam("nombreUsuario") String nombreUsuario,
                               Perfil p) {
        ConexionBD bd = ConexionBD.getInstancia();
        String sql = "UPDATE perfiles SET links=?, biografia=? "
                   + "WHERE nombreUsuario=?";

        try (PreparedStatement ps = bd.getConexion().prepareStatement(sql)) {
            ps.setString(1, p.getLinks());
            ps.setString(2, p.getBiografia());
            ps.setString(3, nombreUsuario);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                p.setNombreUsuario(nombreUsuario);
                return Response.ok(p).build();
            } else {
                return Response.status(Status.NOT_FOUND)
                               .entity("Perfil no encontrado: " + nombreUsuario)
                               .type(MediaType.TEXT_PLAIN).build();
            }
        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                           .entity("Error BD: " + e.getLocalizedMessage())
                           .type(MediaType.TEXT_PLAIN).build();
        }
    }

    // ------------------------------------------------------------------
    // DELETE /perfiles/{nombreUsuario}  →  eliminar perfil
    // ------------------------------------------------------------------
    @DELETE
    @Path("{nombreUsuario}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response eliminar(@PathParam("nombreUsuario") String nombreUsuario) {
        ConexionBD bd = ConexionBD.getInstancia();
        String sql = "DELETE FROM perfiles WHERE nombreUsuario = ?";

        try (PreparedStatement ps = bd.getConexion().prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                return Response.ok("Perfil '" + nombreUsuario + "' eliminado.").build();
            } else {
                return Response.status(Status.NOT_FOUND)
                               .entity("Perfil no encontrado: " + nombreUsuario).build();
            }
        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                           .entity("Error BD: " + e.getLocalizedMessage()).build();
        }
    }

    // ------------------------------------------------------------------
    // Método auxiliar
    // ------------------------------------------------------------------
    private Perfil mapearFila(ResultSet rs) throws SQLException {
        Perfil p = new Perfil();
        p.setNombreUsuario(rs.getString("nombreUsuario"));
        p.setLinks(rs.getString("links"));
        p.setBiografia(rs.getString("biografia"));
        return p;
    }
}
