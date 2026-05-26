package api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

@Path("/publicaciones")
public class PublicacionResource {

    // -----------------------------
    // GET TODAS LAS PUBLICACIONES
    // -----------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodas() {

        ArrayList<Publicacion> lista = new ArrayList<>();

        ConexionBD bd = ConexionBD.getInstancia();

        try (Statement st = bd.getConexion().createStatement()) {

            ResultSet rs = st.executeQuery(
                    "SELECT nombreUsuario, fechaImagen, imagen, comentario FROM publicaciones ORDER BY fechaImagen DESC");

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }

            return Response.ok(lista).build();

        } catch (SQLException e) {

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // -----------------------------
    // INSERTAR PUBLICACION
    // -----------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response crear(Publicacion p) {
        ConexionBD bd = ConexionBD.getInstancia();

        String sql = "INSERT INTO publicaciones (nombreUsuario,fechaImagen,imagen,comentario) VALUES(?,?,?,?)";

        try (
                PreparedStatement ps = bd.getConexion().prepareStatement(sql)) {

            ps.setString(
                    1,
                    p.getNombreUsuario());

            ps.setString(
                    2,
                    p.getFechaImagen());

            if (p.getImagenBase64() != null &&
                    !p.getImagenBase64().isEmpty()) {
                ps.setBytes(
                        3,
                        Base64.getDecoder()
                                .decode( p.getImagenBase64().replaceAll("\\s+", "")));
            } else {
                ps.setNull(
                        3,
                        Types.BLOB);
            }
            ps.setString(4,p.getComentario());

            ps.executeUpdate();

            return Response
                    .status(Response.Status.CREATED)
                    .entity("Publicación creada")
                    .build();

        } catch (SQLException e) {

            return Response
                    .status(
                            Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // -----------------------------
    // MAPPER
    // -----------------------------
    private Publicacion mapearFila(ResultSet rs)
            throws SQLException {

        Publicacion p = new Publicacion();

        p.setNombreUsuario(
                rs.getString("nombreUsuario"));
        p.setComentario(
                rs.getString("comentario"));

        p.setFechaImagen(
                rs.getString("fechaImagen"));

        byte[] foto = rs.getBytes("imagen");

        if (foto != null) {
            p.setImagenBase64(
                    Base64.getEncoder()
                            .encodeToString(foto));
        }

        return p;
    }

}