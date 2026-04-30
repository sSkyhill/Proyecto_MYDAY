package com.example.myday;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class ApiRest {

    private static final String BASE_URL =
            "http://10.0.2.2:8080/api-proyecto/rest";

    // CREAR PUBLICACION
    public interface Callback {
        void onResult(boolean ok);
    }
    public void crearPublicacion(Publicacion p,Callback callback) {

        new Thread(() -> {
            boolean result = false;
            try {
                URL url = new URL(BASE_URL + "/publicaciones");

                HttpURLConnection conn =
                        (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                JSONObject json = new JSONObject();

                json.put("nombreUsuario", p.getNombreUsuario());
                json.put("fechaImagen", p.getFechaImagen());
                json.put("imagenBase64", p.getImagenBase64());
                json.put("comentario", p.getComentario());

                OutputStream os = conn.getOutputStream();
                os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                os.close();

                int responseCode = conn.getResponseCode();

                InputStream stream = (conn.getErrorStream() != null)
                        ? conn.getErrorStream()
                        : conn.getInputStream();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(stream, StandardCharsets.UTF_8)
                );

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                Log.e("API_BACKEND_ERROR", sb.toString());
                Log.e("API_CODE", String.valueOf(responseCode));
                result = (responseCode == 201);
            } catch (Exception e) {
                Log.e("API", "Error POST publicación: " + e.getMessage());

            }
            callback.onResult(result);
        }).start();
    }


    // --------------------------------------------------
    // GET USUARIO (SIN FOTO PERFIL)
    // --------------------------------------------------
    public Usuario obtenerDatosUsuario(String nombreUsuario) {

        try {
            URL url = new URL(BASE_URL + "/usuarios/" + nombreUsuario);

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(),
                                StandardCharsets.UTF_8)
                );

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line.trim());
                }

                JSONObject obj = new JSONObject(sb.toString());

                String usuario = obj.getString("nombreUsuario");
                String email = obj.getString("email");

                return new Usuario(usuario,email, null);
            }

        } catch (Exception e) {
            Log.e("API", "Error usuario: " + e.getMessage());
        }

        return null;
    }

    // --------------------------------------------------
    // GET USUARIOS
    // --------------------------------------------------
    public List<Usuario> obtenerUsuarios() {

        List<Usuario> usuarios = new ArrayList<>();

        try {
            URL url = new URL(BASE_URL + "/usuarios");

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(),
                                StandardCharsets.UTF_8)
                );

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line.trim());
                }

                JSONArray array = new JSONArray(sb.toString());

                for (int i = 0; i < array.length(); i++) {

                    JSONObject obj = array.getJSONObject(i);

                    String username = obj.getString("nombreUsuario");
                    String email = obj.getString("email");



                    usuarios.add(new Usuario(username,email, null));
                }
            }

        } catch (Exception e) {
            Log.e("API", "Error usuarios: " + e.getMessage());
        }

        return usuarios;
    }
    // GET USUARIO
    public String loginUsuario(String nombreUsuario, String contrasena) {

        try {
            URL url = new URL(BASE_URL + "/usuarios/login");

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "text/plain");
            conn.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("nombreUsuario", nombreUsuario);
            json.put("contrasena", contrasena);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.toString().getBytes(StandardCharsets.UTF_8));
            }

            int code = conn.getResponseCode();

            if(code == 200){
                return  nombreUsuario;
            }

        } catch (Exception e) {
            Log.e("API", "Error login: " + e.getMessage());

        }
        return null;
    }

    // REGISTRAR USUARIO
    public String registrarUsuario(String usuario, String email, String contrasena) {

        try {
            URL url = new URL(BASE_URL + "/usuarios");

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "text/plain");
            conn.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("nombreUsuario", usuario);
            json.put("email", email);
            json.put("contrasena", contrasena);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.toString().getBytes(StandardCharsets.UTF_8));
            }

            int code = conn.getResponseCode();

            InputStream stream =
                    (code >= 400) ? conn.getErrorStream() : conn.getInputStream();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8)
            );

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString(); // 👈 mensaje del backend

        } catch (Exception e) {
            return "ERROR_CONEXION";
        }
    }



    // --------------------------------------------------
    // GET PUBLICACIONES (FEED)
    // --------------------------------------------------
    private String getToday() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(new Date());
    }
    public List<Publicacion> obtenerPublicaciones() {

        List<Publicacion> publicaciones = new ArrayList<>();

        try {
            URL url = new URL(BASE_URL + "/publicaciones");

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(),
                                StandardCharsets.UTF_8)
                );

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line.trim());
                }

                JSONArray array = new JSONArray(sb.toString());
                String soloHoy = getToday();
                for (int i = 0; i < array.length(); i++) {


                    JSONObject obj = array.getJSONObject(i);
                    String fecha = obj.getString("fechaImagen");
                    if (fecha.startsWith(soloHoy)){
                    Publicacion p = new Publicacion();

                    p.setNombreUsuario(obj.getString("nombreUsuario"));

                    if (obj.has("imagenBase64")) {
                        p.setImagenBase64(obj.getString("imagenBase64"));
                    }
                    p.setFechaImagen(obj.getString("fechaImagen"));

                    p.setComentario(obj.getString("comentario"));

                    publicaciones.add(p);
                }
                }
            }

        } catch (Exception e) {
            Log.e("API", "Error publicaciones: " + e.getMessage());
        }

        return publicaciones;
    }

    //GET PUBLICACIONES PERFIL
    public List<Publicacion> obtenerPublicacionesPorUsuario(String usuarioBuscado) {

        List<Publicacion> publicaciones = new ArrayList<>();

        try {
            URL url = new URL(BASE_URL + "/publicaciones");

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(),
                                StandardCharsets.UTF_8)
                );

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line.trim());
                }

                JSONArray array = new JSONArray(sb.toString());

                for (int i = 0; i < array.length(); i++) {

                    JSONObject obj = array.getJSONObject(i);

                    String usuario = obj.getString("nombreUsuario");

                    if (usuario.equals(usuarioBuscado)) {

                        Publicacion p = new Publicacion();

                        p.setNombreUsuario(usuario);
                        p.setComentario(obj.getString("comentario"));
                        p.setFechaImagen(obj.getString("fechaImagen"));

                        if (obj.has("imagenBase64")) {
                            p.setImagenBase64(obj.getString("imagenBase64"));
                        }

                        publicaciones.add(p);
                    }
                }
            }

        } catch (Exception e) {
            Log.e("API", "Error publicaciones usuario: " + e.getMessage());
        }

        return publicaciones;
    }
}