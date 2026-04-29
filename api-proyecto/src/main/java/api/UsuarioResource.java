package api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.sql.*;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

@Path("/usuarios")
public class UsuarioResource {

    // --------------------------------------------------
    // GET TODOS
    // --------------------------------------------------
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getTodos() {

        ArrayList<Usuario> lista = new ArrayList<>();
        ConexionBD bd = ConexionBD.getInstancia();

        try (Statement st = bd.getConexion().createStatement()) {

            ResultSet rs =
                st.executeQuery("SELECT nombreUsuario, email FROM usuarios");

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }

            return Response.ok(lista).build();

        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // --------------------------------------------------
    // GET UNO
    // --------------------------------------------------
    @GET
    @Path("{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUno(
            @PathParam("nombreUsuario") String nombreUsuario) {

        ConexionBD bd = ConexionBD.getInstancia();

        String sql =
            "SELECT nombreUsuario, email FROM usuarios WHERE nombreUsuario=?";

        try (PreparedStatement ps =
                 bd.getConexion().prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Response.ok(mapearFila(rs)).build();
            }

            return Response.status(Status.NOT_FOUND)
                    .entity("Usuario no encontrado")
                    .build();

        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // --------------------------------------------------
    // REGISTRO
    // --------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response crear(Usuario u) {

        ConexionBD bd = ConexionBD.getInstancia();

        if (u.getNombreUsuario() == null || u.getNombreUsuario().isBlank()) {
            return Response.status(Status.BAD_REQUEST)
                    .entity("Usuario obligatorio")
                    .build();
        }

        try {
            PreparedStatement checkUser =
                    bd.getConexion().prepareStatement(
                        "SELECT 1 FROM usuarios WHERE nombreUsuario=?");

            checkUser.setString(1, u.getNombreUsuario());

            if (checkUser.executeQuery().next()) {
                return Response.status(Status.CONFLICT)
                        .entity("Usuario ya existe")
                        .build();
            }

            PreparedStatement checkEmail =
                    bd.getConexion().prepareStatement(
                        "SELECT 1 FROM usuarios WHERE email=?");

            checkEmail.setString(1, u.getEmail());

            if (checkEmail.executeQuery().next()) {
                return Response.status(Status.CONFLICT)
                        .entity("Email ya existe")
                        .build();
            }

        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }

        String hash =
            BCrypt.hashpw(u.getContrasena(), BCrypt.gensalt());

        String sql =
            "INSERT INTO usuarios (nombreUsuario, email, contrasena) VALUES (?, ?, ?)";

        try (PreparedStatement ps =
                 bd.getConexion().prepareStatement(sql)) {

            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getEmail());
            ps.setString(3, hash);

            ps.executeUpdate();

            return Response.status(Status.CREATED)
                    .entity("Usuario creado")
                    .build();

        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // --------------------------------------------------
    // LOGIN
    // --------------------------------------------------
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(Usuario login) {

        ConexionBD bd = ConexionBD.getInstancia();

        String sql =
            "SELECT contrasena FROM usuarios WHERE nombreUsuario=?";

        try (PreparedStatement ps =
                 bd.getConexion().prepareStatement(sql)) {

            ps.setString(1, login.getNombreUsuario());

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return Response.status(Status.UNAUTHORIZED)
                        .entity("Usuario no existe")
                        .build();
            }

            String hash = rs.getString("contrasena");

            if (!BCrypt.checkpw(login.getContrasena(), hash)) {
                return Response.status(Status.UNAUTHORIZED)
                        .entity("Login incorrecto")
                        .build();
            }

            return Response.ok("OK").build();

        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // --------------------------------------------------
    // UPDATE
    // --------------------------------------------------
    @PUT
    @Path("{nombreUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(
            @PathParam("nombreUsuario") String nombreUsuario,
            Usuario u) {

        ConexionBD bd = ConexionBD.getInstancia();

        String hash =
            BCrypt.hashpw(u.getContrasena(), BCrypt.gensalt());

        String sql =
            "UPDATE usuarios SET email=?, contrasena=? WHERE nombreUsuario=?";

        try (PreparedStatement ps =
                 bd.getConexion().prepareStatement(sql)) {

            ps.setString(1, u.getEmail());
            ps.setString(2, hash);
            ps.setString(3, nombreUsuario);

            ps.executeUpdate();

            return Response.ok("Actualizado").build();

        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // --------------------------------------------------
    // DELETE
    // --------------------------------------------------
    @DELETE
    @Path("{nombreUsuario}")
    public Response eliminar(
            @PathParam("nombreUsuario") String nombreUsuario) {

        ConexionBD bd = ConexionBD.getInstancia();

        try (PreparedStatement ps =
                 bd.getConexion().prepareStatement(
                     "DELETE FROM usuarios WHERE nombreUsuario=?")) {

            ps.setString(1, nombreUsuario);

            ps.executeUpdate();

            return Response.ok("Eliminado").build();

        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // --------------------------------------------------
    // MAPPER
    // --------------------------------------------------
    private Usuario mapearFila(ResultSet rs) throws SQLException {

        Usuario u = new Usuario();

        u.setNombreUsuario(rs.getString("nombreUsuario"));
        u.setEmail(rs.getString("email"));

        return u;
    }
}