package com.example.myday;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Registro extends AppCompatActivity {
    Button btnRegistro;
    EditText editMail;
    EditText editUsuario;
    EditText editContrasena;
    EditText editNombreApellidos;
    String nombreUsuario;
    String contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editMail = findViewById(R.id.editEmail);
        editUsuario = findViewById(R.id.editNombre2);
        editNombreApellidos = findViewById(R.id.editConfirmaContra);
        editContrasena = findViewById(R.id.editContra2);
        btnRegistro = findViewById(R.id.btnRegistrarse);
        btnRegistro.setOnClickListener(v -> {

            String usuario = editUsuario.getText().toString().trim();
            String email = editMail.getText().toString().trim();
            String pass = editContrasena.getText().toString().trim();

            boolean ok = true;

            if (usuario.isEmpty()) {
                editUsuario.setError("Introduce usuario");
                ok = false;
            }

            if (email.isEmpty()) {
                editMail.setError("Introduce email");
                ok = false;
            }

            if (pass.isEmpty()) {
                editContrasena.setError("Introduce contraseña");
                ok = false;
            }

            if (pass.length() < 6) {
                editContrasena.setError("La contraseña debe tener mínimo 6 caracteres");
                return;
            }

            if (!ok) return;

                new Thread(() -> {

                    ApiRest api = new ApiRest();

                    String respuesta = api.registrarUsuario(usuario, email, pass);

                    runOnUiThread(() -> {

                        switch (respuesta) {

                            case "Usuario ya existe":
                                editUsuario.setError("Ese usuario ya existe");
                                break;

                            case "Email ya existe":
                                editMail.setError("Ese email ya está registrado");
                                break;

                            case "Usuario creado":
                                finish(); // registro OK
                                break;

                            default:
                                editUsuario.setError("Error de conexión o servidor");
                                break;
                        }

                    });

                }).start();



        });

    }
}