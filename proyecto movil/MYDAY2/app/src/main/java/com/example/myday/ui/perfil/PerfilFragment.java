package com.example.myday.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myday.AdaptadorPerfil;
import com.example.myday.ApiRest;
import com.example.myday.Publicacion;
import com.example.myday.SesionUsuario;
import com.example.myday.databinding.FragmentPerfilBinding;

import java.util.ArrayList;
import java.util.List;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;

    private final List<Publicacion> publicaciones = new ArrayList<>();
    private AdaptadorPerfil adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPerfilBinding.inflate(inflater, container, false);

        String usuarioPerfil = SesionUsuario.getUsuario();
        binding.txtNombre.setText(usuarioPerfil);

        RecyclerView rv = binding.recyclerView2;
        rv.setLayoutManager(new GridLayoutManager(requireContext(), 3));

        adapter = new AdaptadorPerfil(publicaciones);
        rv.setAdapter(adapter);

        cargarPublicaciones(usuarioPerfil);

        return binding.getRoot();
    }

    private void cargarPublicaciones(String usuario) {

        new Thread(() -> {

            ApiRest api = new ApiRest();

            List<Publicacion> lista =
                    api.obtenerPublicacionesPorUsuario(usuario);

            requireActivity().runOnUiThread(() -> {

                publicaciones.clear();
                publicaciones.addAll(lista);
                adapter.notifyDataSetChanged();

            });

        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}