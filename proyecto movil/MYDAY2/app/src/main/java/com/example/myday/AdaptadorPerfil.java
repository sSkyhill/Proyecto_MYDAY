package com.example.myday;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPerfil extends RecyclerView.Adapter<AdaptadorPerfil.PhotoViewHolder> {

    List<Usuario> usuarios = new ArrayList<>();
        public AdaptadorPerfil(List<Usuario> usuarios){
        this.usuarios = usuarios;
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
        Usuario usu = this.usuarios.get(position);
        String foto = usu.getFotoperfil();

        byte[] decoded = android.util.Base64.decode(foto, android.util.Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
        holder.getfotoSubida().setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
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