package com.example.myday;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Feed extends AppCompatActivity {
RecyclerView rv;
AdaptadorFeed adaptadorFeed;
ArrayList<DatosPerfiles> datosPerfiles;
    RecyclerView.LayoutManager miLayoutManager;
    public void rellenaDatos(){
        datosPerfiles = new ArrayList<DatosPerfiles>();

            datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));
            datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));
            datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));
            datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));
            datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));
            datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));
            datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feed);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.coordinatorLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rellenaDatos();
        adaptadorFeed = new AdaptadorFeed(datosPerfiles);
        Toolbar tb = findViewById(R.id.toolbar3);
        setSupportActionBar(tb);
        rv = findViewById(R.id.recyclerView);

        rv.setAdapter(adaptadorFeed);
        miLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(miLayoutManager);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feed, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itemPerfil) {

            return true;
        }
        if (id == R.id.itemMensajes){

        }
        if (id == R.id.myDayitem){

        }
        return super.onOptionsItemSelected(item);
    }
}