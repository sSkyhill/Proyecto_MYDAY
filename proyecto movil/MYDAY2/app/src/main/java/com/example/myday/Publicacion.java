package com.example.myday;

public class Publicacion{

    private String nombreUsuario;
    private String imagenBase64;
    private String comentario;
    private String fechaImagen;

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getImagenBase64() { return imagenBase64; }
    public void setImagenBase64(String imagenBase64) { this.imagenBase64 = imagenBase64; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public String getFechaImagen() { return fechaImagen; }
    public void setFechaImagen(String fechaImagen) { this.fechaImagen = fechaImagen; }
}