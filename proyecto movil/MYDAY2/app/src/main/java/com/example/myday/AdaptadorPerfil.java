package com.example.myday;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPerfil extends RecyclerView.Adapter<AdaptadorPerfil.PhotoViewHolder> {

    ArrayList<DatosPerfiles> datosPerfiles = new ArrayList<>();
    public AdaptadorPerfil(ArrayList<DatosPerfiles> datosPerfiles){
        this.datosPerfiles = datosPerfiles;
    }



    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celda_perfil, parent, false);

        int screenWidth = parent.getContext().getResources().getDisplayMetrics().widthPixels;
        int imageSize = screenWidth / 3;

        view.getLayoutParams().height = imageSize;

        return new PhotoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        DatosPerfiles dp = this.datosPerfiles.get(position);
        holder.getfotoSubida().setImageResource(dp.getFotoMuro());
    }

    @Override
    public int getItemCount() {
        return datosPerfiles.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView fotoPerfil;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            this.fotoPerfil = itemView.findViewById(R.id.fotoSperfil);
        }
        public ImageView getfotoSubida() {
            return fotoPerfil;
        }
    }
}