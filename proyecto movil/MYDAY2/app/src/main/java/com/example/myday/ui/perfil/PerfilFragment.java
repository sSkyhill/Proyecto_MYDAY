package com.example.myday.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myday.AdaptadorPerfil;
import com.example.myday.ApiRest;
import com.example.myday.DatosPerfiles;
import com.example.myday.R;
import com.example.myday.Usuario;
import com.example.myday.databinding.FragmentPerfilBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    AdaptadorPerfil adaptadorPerfil;


    List<Usuario> usuarios;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PerfilViewModel perfilViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        usuarios = new ArrayList<Usuario>();
        ApiRest apr = new ApiRest();
        usuarios = apr.obtenerUsuarios();
        RecyclerView rv = binding.recyclerView2;
        adaptadorPerfil = new AdaptadorPerfil(usuarios);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int imageSize = screenWidth / 3;
        ViewGroup.LayoutParams params = rv.getLayoutParams();
        params.height = imageSize * 4; // 4 filas EXACTAS
        rv.setLayoutParams(params);

        rv.setAdapter(adaptadorPerfil);
        rv.setLayoutManager(new GridLayoutManager(requireContext(), 3));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}