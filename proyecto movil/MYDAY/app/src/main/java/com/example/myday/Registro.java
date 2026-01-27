package com.example.myday;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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
        editNombreApellidos = findViewById(R.id.editNombreyAp);
        editContrasena = findViewById(R.id.editContra2);
        btnRegistro = findViewById(R.id.btnRegistrarse);
    btnRegistro.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(editUsuario.getText().isEmpty()){
                editUsuario.setHint("Introduce un nombre de usuario");
                editUsuario.setHintTextColor(Color.RED);
            }
            if(editContrasena.getText().isEmpty()){
                editContrasena.setHint("Introduce una contraseña");
                editContrasena.setHintTextColor(Color.RED);
            }
            if(editNombreApellidos.getText().isEmpty()){
                editNombreApellidos.setHint("Introduce nombre y apellidos");
                editNombreApellidos.setHintTextColor(Color.RED);
            }
            if(editMail.getText().isEmpty()){
                editMail.setHint("Introduce un E-Mail");
                editMail.setHintTextColor(Color.RED);
            }


        }
    });

    }
}