package com.example.myday;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFeed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFeed extends Fragment {

    ArrayList<DatosPerfiles> datosPerfiles;
    AdaptadorFeed adaptadorFeed;
    RecyclerView rv;
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


    public FragmentFeed() {


    }



    public static FragmentFeed newInstance(String param1, String param2) {
        FragmentFeed fragment = new FragmentFeed();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        rellenaDatos();
        adaptadorFeed = new AdaptadorFeed(datosPerfiles);


        rv = view.findViewById(R.id.recyclerFeed);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adaptadorFeed);


    return view;
    }
}