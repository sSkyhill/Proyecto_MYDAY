package api;

public class Publicacion {
    private String nombreUsuario;
    private String fechaImagen;
    private String imagenBase64;
    private String comentario;

    public void setFechaImagen(String fechaImagen) {
        this.fechaImagen = fechaImagen;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFechaImagen() {
        return fechaImagen;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public String getComentario() {
        return comentario;
    }

}
