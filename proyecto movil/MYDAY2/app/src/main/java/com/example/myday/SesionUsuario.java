package com.example.myday;

public class SesionUsuario {
    private static String usuarioLogeado;

    public static void setUsuario(String u) {
        usuarioLogeado = u;
    }

    public static String getUsuario() {
        return usuarioLogeado;
    }
}
