package com.example.myday;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentoPerfil extends Fragment {


    public FragmentoPerfil() {

    }


    // TODO: Rename and change types and number of parameters
    public static FragmentoPerfil newInstance(String param1, String param2) {
        FragmentoPerfil fragment = new FragmentoPerfil();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_perfil, container, false);
    }
}