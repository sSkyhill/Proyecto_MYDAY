package com.example.myday;

import android.content.Intent;
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
        setContentView(R.layout.activity_inicio);
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



        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                boolean flag = true;
                if(editNombre.getText().isEmpty()){
                    editNombre.setHint("Introduce un nombre de usuario");
                    editNombre.setHintTextColor(Color.RED);
                    flag = false;
                }else{
                    nombreUsuario = editNombre.getText().toString();
                    Log.i("Consola",nombreUsuario);
                    flag = true;
                }

                if(editContrasena.getText().isEmpty()){
                    editContrasena.setHint("Introduce una contraseña");
                    editContrasena.setHintTextColor(Color.RED);
                    flag = false;
                }else {
                contrasena = editContrasena.getText().toString();
                    flag = true;
                }
                if(flag){
                    Intent i = new Intent(InicioSesion.this, Feed.class);
                    startActivity(i);
                }


            }

        });

        txtRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InicioSesion.this,Registro.class);
                startActivity(i);
            }
        });
    }
}