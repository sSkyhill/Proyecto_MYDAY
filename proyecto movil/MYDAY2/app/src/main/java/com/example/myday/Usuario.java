package com.example.myday;

public class Usuario {
    private String nombreUsuario;

    private String email;
    private String contrasena;

    public Usuario(String nombreUsuario,String email,String contrasena){
        this.nombreUsuario = nombreUsuario;

        this.email = email;
        this.contrasena = contrasena;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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



    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }


}
