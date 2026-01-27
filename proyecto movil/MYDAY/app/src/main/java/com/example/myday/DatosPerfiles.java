package com.example.myday;

public class DatosPerfiles {
    private String nombreUsuario;
    private int fotoPerfil;
    private int fotoMuro;
    public DatosPerfiles(String nombreUsuario,int fotoPerfil,int fotoMuro){
        this.fotoPerfil = fotoPerfil;
        this.fotoMuro = fotoMuro;
        this.nombreUsuario = nombreUsuario;
    }
    public void setFotoPerfil(int fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
    public int getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoMuro(int fotoMuro) {
        this.fotoMuro = fotoMuro;
    }

    public int getFotoMuro() {
        return fotoMuro;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }


}
