package com.example.myday;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myday.Publicacion;
import com.example.myday.R;

import java.util.List;

public class AdaptadorPerfil extends RecyclerView.Adapter<AdaptadorPerfil.PhotoViewHolder> {

    List<Publicacion> publicaciones;

    public AdaptadorPerfil(List<Publicacion> publicaciones){
        this.publicaciones = publicaciones;
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

        Publicacion pub = publicaciones.get(position);
        Log.e("BASE64", String.valueOf(pub.getImagenBase64()));

        if (pub.getImagenBase64() != null) {

            byte[] decodedString = android.util.Base64.decode(
                    pub.getImagenBase64(),
                    android.util.Base64.DEFAULT
            );

            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            holder.imagenPublicacion.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        ImageView imagenPublicacion;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            imagenPublicacion = itemView.findViewById(R.id.imagen);
        }
    }
}