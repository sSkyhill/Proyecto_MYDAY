package api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Recurso REST para la tabla "imagenes".
 *
 * URL base: http://localhost:8080/api-proyecto/rest/imagenes
 *
 * Operaciones:
 *   GET    /imagenes                          → lista todas las imágenes
 *   GET    /imagenes/{nombreImagen}           → una imagen concreta
 *   GET    /imagenes/usuario/{nombreUsuario}  → imágenes de un usuario
 *   POST   /imagenes                          → sube una nueva imagen
 *   PUT    /imagenes/{nombreImagen}           → actualiza una imagen
 *   DELETE /imagenes/{nombreImagen}           → elimina una imagen
 */
@Path("/imagenes")
public class ImagenResource {

    // ------------------------------------------------------------------
    // GET /imagenes  →  lista completa
    // ------------------------------------------------------------------
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getTodas() {
        ArrayList<Imagen> lista = new ArrayList<>();
        ConexionBD bd = ConexionBD.getInstancia();

        try (Statement st = bd.getConexion().createStatement()) {
            ResultSet rs = st.executeQuery(
                "SELECT nombreImagen, nombreUsuario, imagen FROM imagenes"
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
    // GET /imagenes/{nombreImagen}  →  una imagen
    // ------------------------------------------------------------------
    @GET
    @Path("{nombreImagen}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getUna(@PathParam("nombreImagen") String nombreImagen) {
        ConexionBD bd = ConexionBD.getInstancia();
        String sql = "SELECT nombreImagen, nombreUsuario, imagen "
                   + "FROM imagenes WHERE nombreImagen = ?";

        try (PreparedStatement ps = bd.getConexion().prepareStatement(sql)) {
            ps.setString(1, nombreImagen);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Response.ok(mapearFila(rs)).build();
            } else {
                return Response.status(Status.NOT_FOUND)
                               .entity("Imagen no encontrada: " + nombreImagen)
                               .type(MediaType.TEXT_PLAIN).build();
            }
        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                           .entity("Error BD: " + e.getLocalizedMessage())
                           .type(MediaType.TEXT_PLAIN).build();
        }
    }

    // ------------------------------------------------------------------
    // GET /imagenes/usuario/{nombreUsuario}  →  imágenes de un usuario
    // ------------------------------------------------------------------
    @GET
    @Path("usuario/{nombreUsuario}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getPorUsuario(@PathParam("nombreUsuario") String nombreUsuario) {
        ArrayList<Imagen> lista = new ArrayList<>();
        ConexionBD bd = ConexionBD.getInstancia();
        String sql = "SELECT nombreImagen, nombreUsuario, imagen "
                   + "FROM imagenes WHERE nombreUsuario = ?";

        try (PreparedStatement ps = bd.getConexion().prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();

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
    // POST /imagenes  →  crear imagen
    // Body JSON ejemplo:
    // {
    //   "nombreImagen": "foto1",
    //   "nombreUsuario": "juan",
    //   "imagen": "<Base64 de la imagen>"
    // }
    // ------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response crear(Imagen img) {
        if (img.getNombreImagen() == null || img.getNombreImagen().isEmpty()) {
            return Response.status(Status.BAD_REQUEST)
                           .entity("El campo 'nombreImagen' es obligatorio.")
                           .type(MediaType.TEXT_PLAIN).build();
        }

        ConexionBD bd = ConexionBD.getInstancia();
        String sql = "INSERT INTO imagenes (nombreImagen, nombreUsuario, imagen) "
                   + "VALUES (?, ?, ?)";

        try (PreparedStatement ps = bd.getConexion().prepareStatement(sql)) {
            ps.setString(1, img.getNombreImagen());
            ps.setString(2, img.getNombreUsuario());

            if (img.getImagen() != null && !img.getImagen().isEmpty()) {
                ps.setBytes(3, Base64.getDecoder().decode(img.getImagen()));
            } else {
                ps.setNull(3, Types.BLOB);
            }

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return Response.status(Status.CREATED).entity(img).build();
            } else {
                return Response.status(Status.INTERNAL_SERVER_ERROR)
                               .entity("No se pudo insertar la imagen.")
                               .type(MediaType.TEXT_PLAIN).build();
            }
        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                           .entity("Error BD: " + e.getLocalizedMessage())
                           .type(MediaType.TEXT_PLAIN).build();
        }
    }

    // ------------------------------------------------------------------
    // PUT /imagenes/{nombreImagen}  →  actualizar imagen
    // ------------------------------------------------------------------
    @PUT
    @Path("{nombreImagen}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response actualizar(@PathParam("nombreImagen") String nombreImagen,
                               Imagen img) {
        ConexionBD bd = ConexionBD.getInstancia();
        String sql = "UPDATE imagenes SET nombreUsuario=?, imagen=? "
                   + "WHERE nombreImagen=?";

        try (PreparedStatement ps = bd.getConexion().prepareStatement(sql)) {
            ps.setString(1, img.getNombreUsuario());
            if (img.getImagen() != null && !img.getImagen().isEmpty()) {
                ps.setBytes(2, Base64.getDecoder().decode(img.getImagen()));
            } else {
                ps.setNull(2, Types.BLOB);
            }
            ps.setString(3, nombreImagen);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                img.setNombreImagen(nombreImagen);
                return Response.ok(img).build();
            } else {
                return Response.status(Status.NOT_FOUND)
                               .entity("Imagen no encontrada: " + nombreImagen)
                               .type(MediaType.TEXT_PLAIN).build();
            }
        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                           .entity("Error BD: " + e.getLocalizedMessage())
                           .type(MediaType.TEXT_PLAIN).build();
        }
    }

    // ------------------------------------------------------------------
    // DELETE /imagenes/{nombreImagen}  →  eliminar imagen
    // ------------------------------------------------------------------
    @DELETE
    @Path("{nombreImagen}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response eliminar(@PathParam("nombreImagen") String nombreImagen) {
        ConexionBD bd = ConexionBD.getInstancia();
        String sql = "DELETE FROM imagenes WHERE nombreImagen = ?";

        try (PreparedStatement ps = bd.getConexion().prepareStatement(sql)) {
            ps.setString(1, nombreImagen);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                return Response.ok("Imagen '" + nombreImagen + "' eliminada.").build();
            } else {
                return Response.status(Status.NOT_FOUND)
                               .entity("Imagen no encontrada: " + nombreImagen).build();
            }
        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                           .entity("Error BD: " + e.getLocalizedMessage()).build();
        }
    }

    // ------------------------------------------------------------------
    // Método auxiliar
    // ------------------------------------------------------------------
    private Imagen mapearFila(ResultSet rs) throws SQLException {
        Imagen img = new Imagen();
        img.setNombreImagen(rs.getString("nombreImagen"));
        img.setNombreUsuario(rs.getString("nombreUsuario"));

        byte[] bytes = rs.getBytes("imagen");
        if (bytes != null) {
            img.setImagen(Base64.getEncoder().encodeToString(bytes));
        }
        return img;
    }
}
