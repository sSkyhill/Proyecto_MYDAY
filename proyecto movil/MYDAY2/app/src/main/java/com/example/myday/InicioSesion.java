package com.example.myday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileWriter;

public class InicioSesion extends AppCompatActivity {
    TextView txtPregunta;
    TextView txtRegistro;
    Button btnIniciar;
    EditText editNombre;
    EditText editContrasena;
    String nombreUsuario ="";
    String contrasena ="";
    FileWriter fw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_sesion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtPregunta = findViewById(R.id.textPregunta);
        txtRegistro = findViewById(R.id.textRegistro);
        btnIniciar = findViewById(R.id.botonIniciar);
        editNombre = findViewById(R.id.editNombre);
        editContrasena = findViewById(R.id.editContrasena);
        editNombre.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});

        txtRegistro.setOnClickListener(v -> {
            Intent i = new Intent(InicioSesion.this, Registro.class);
            startActivity(i);
        });

        btnIniciar.setOnClickListener(v -> {

            String nombre = editNombre.getText().toString().trim();
            String pass = editContrasena.getText().toString().trim();

            boolean flag = true;

            if (nombre.isEmpty()) {
                editNombre.setError("Introduce un nombre de usuario");
                flag = false;
            }

            if (pass.isEmpty()) {
                editContrasena.setError("Introduce una contraseña");
                flag = false;
            }

            if (!flag) return;

            ApiRest api = new ApiRest();

            new Thread(() -> {

                String user = api.loginUsuario(
                        editNombre.getText().toString(),
                        editContrasena.getText().toString()
                );

                runOnUiThread(() -> {

                    if (user!= null) {


                        SesionUsuario.setUsuario(user);

                        Intent i = new Intent(InicioSesion.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {

                        btnIniciar.setError("Login incorrecto");
                    }

                });

            }).start();
        });
    }
}