package com.example.myday.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myday.AdaptadorFeed;
import com.example.myday.ApiRest;
import com.example.myday.Publicacion;
import com.example.myday.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rv;
    private AdaptadorFeed adaptadorFeed;
    private List<Publicacion> publicaciones;
    private RecyclerView.LayoutManager layoutManager;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rv = binding.recyclerView;

        layoutManager = new LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false);

        rv.setLayoutManager(layoutManager);

        // Lista inicial vacía SIEMPRE
        publicaciones = new ArrayList<>();
        adaptadorFeed = new AdaptadorFeed(publicaciones);
        rv.setAdapter(adaptadorFeed);

        cargarPublicaciones();

        return root;
    }

    private void cargarPublicaciones() {

        new Thread(() -> {

            try {
                ApiRest api = new ApiRest();
                List<Publicacion> datos = api.obtenerPublicaciones();

                requireActivity().runOnUiThread(() -> {

                    if (datos == null) {
                        Log.e("FEED", "API devolvió NULL");
                        return;
                    }

                    Log.d("FEED", "Publicaciones recibidas: " + datos.size());

                    publicaciones.clear();
                    publicaciones.addAll(datos);

                    adaptadorFeed.notifyDataSetChanged();
                });

            } catch (Exception e) {
                Log.e("FEED", "Error cargando publicaciones: " + e.getMessage());
            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}