package com.example.myday;

public class Usuario {
    private String nombreUsuario;
    private String fotoPerfil;

    private String email;
    private String contrasena;

    public Usuario(String nombreUsuario,String fotoPerfil,String email,String contrasena){
        this.nombreUsuario = nombreUsuario;
        this.fotoPerfil = fotoPerfil;
        this.email = email;
        this.contrasena = contrasena;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setFotoperfil(String fotoperfil) {
        this.fotoPerfil = fotoperfil;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getFotoperfil() {
        return fotoPerfil;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }


}
