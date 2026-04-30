package com.example.myday;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SubirPublicacion extends AppCompatActivity {

    ImageView imageView;
    EditText editComentario;
    Button btnPublicar;

    Uri imageUri;
    String base64Imagen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_publicacion);

        imageView = findViewById(R.id.imgSeleccionada);
        editComentario = findViewById(R.id.editComentario);
        btnPublicar = findViewById(R.id.btnPublicar);




        String uriString = getIntent().getStringExtra("imagenUri");

        if (uriString != null) {
            imageUri = Uri.parse(uriString);
            imageView.setImageURI(imageUri);

            base64Imagen = uriToBase64(imageUri);
        }

        btnPublicar.setOnClickListener(v -> publicar());
    }

    // -------------------------
    // SUBIR PUBLICACIÓN
    // -------------------------
    private void publicar() {

        if (SesionUsuario.getUsuario() == null || base64Imagen == null) {
            Toast.makeText(this, "Error de datos", Toast.LENGTH_SHORT).show();
            return;
        }

        Publicacion p = new Publicacion();

        p.setNombreUsuario(SesionUsuario.getUsuario());
        p.setComentario(editComentario.getText().toString());

        String fecha = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss",
                Locale.getDefault()
        ).format(new Date());

        p.setFechaImagen(fecha);
        p.setImagenBase64(base64Imagen);

        ApiRest api = new ApiRest();
        api.crearPublicacion(p, ok -> {

            runOnUiThread(() -> {

                if (ok) {
                    Toast.makeText(this, "OK subida", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "ERROR subida", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    // -------------------------
    // URI -> BASE64
    // -------------------------
    private String uriToBase64(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);

            java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[1024];

            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();
            inputStream.close();

            return Base64.encodeToString(buffer.toByteArray(), Base64.NO_WRAP);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}