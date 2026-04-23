package com.example.myday;

import android.util.Log;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;



public class ApiRest {

    //FUNCION PARA POST

    public void subirDeportistas(String nombre, String deporte) {
        new Thread(() -> {  //Creamos un nuevo hilo para que se ejecute en segundo plano
            try {
                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/deportistas"); //Para emulador 10.0.2.2 sino ipordenador 192.168.1.138
                HttpURLConnection con = (HttpURLConnection) url.openConnection();  //Abrir Conexion
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true); //"Voy a escribir el body"

                JSONObject json = new JSONObject(); //Creamos el Json del body
                json.put("nombre", nombre);
                json.put("deporte", deporte);
                System.out.println(json);
                try(OutputStream os = con.getOutputStream()) {  //Enviar body
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                }
                System.out.println("jj");
                int code = con.getResponseCode();
                Log.i("API", "Código respuesta: " + code);
            } catch (Exception e) {
                Log.e("Error", "Error al subir deportista");
            }

        }).start();
    }


    //FUNCION PARA GET QUE DEVUELVA UN USUARIO

    public Usuario obtenerDatosUsuario(String nombreUsuario) {
        try {
            URL url = new URL(
                    "http://localhost:8080/api-proyecto/rest/usuarios/" + nombreUsuario);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int code = conn.getResponseCode();
            System.out.println("Código HTTP: " + code);

            InputStream stream = conn.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8) );

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            { response.append(line.trim());
            }

            if (code == 200) {
                JSONObject obj = new JSONObject(response.toString());

                String usuario = obj.getString("nombreUsuario");
                String email = obj.getString("email");
                String foto = obj.getString("fotoPerfil");
                String contrasena = obj.getString("contrasena");

                return new Usuario(usuario,foto,email,contrasena);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // si hay error
    }


//FUNCION PARA GET QUE DEVUELVA UN ARRAY DE USUARIOS

    public List<Usuario> obtenerUsuarios() {

        List<Usuario> usuarios = new ArrayList<>();

        try {
            URL url = new URL(
                    "http://localhost:8080/api-proyecto/rest/usuarios"
            );

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int code = conn.getResponseCode();
            System.out.println("Código HTTP: " + code);

            if (code == 200) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
                );

                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line.trim());
                }

                JSONArray array = new JSONArray(response.toString());

                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);

                    String username = obj.getString("nombreUsuario");
                    String fotoperfil = obj.getString("fotoPerfil");
                    String email = obj.getString("email");
                    String contrasena = obj.getString("contrasena");


                    usuarios.add(new Usuario(username,fotoperfil,email,contrasena));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios; // lista vacía si hay error
    }


}