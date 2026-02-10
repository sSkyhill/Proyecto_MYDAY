package com.example.myday.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myday.AdaptadorFeed;
import com.example.myday.DatosPerfiles;
import com.example.myday.R;
import com.example.myday.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView rv;
    AdaptadorFeed adaptadorFeed;
    ArrayList<DatosPerfiles> datosPerfiles;
    RecyclerView.LayoutManager miLayoutManager;
    public void rellenaDatos(){
        datosPerfiles = new ArrayList<DatosPerfiles>();

        datosPerfiles.add(new DatosPerfiles("@travisscott", R.drawable.travisperfil,R.drawable.travis));
        datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));
        datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));
        datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));
        datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));
        datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));
        datosPerfiles.add(new DatosPerfiles("@travisscott",R.drawable.travisperfil,R.drawable.travis));

    }
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        rellenaDatos();
        adaptadorFeed = new AdaptadorFeed(datosPerfiles);
        rv = binding.recyclerView;

        rv.setAdapter(adaptadorFeed);
        miLayoutManager = new LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(miLayoutManager);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}