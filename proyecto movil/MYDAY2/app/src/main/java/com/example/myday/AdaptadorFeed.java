package com.example.myday;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorFeed extends RecyclerView.Adapter<AdaptadorFeed.MyViewHolder> {

    private List<Publicacion> publicaciones;

    public AdaptadorFeed(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celda, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Publicacion pub = publicaciones.get(position);

        // 🧑 Nombre usuario
        holder.nombreUsuario.setText(pub.getNombreUsuario());

        // 🖼 Imagen publicación
        if (pub.getImagenBase64() != null && !pub.getImagenBase64().isEmpty()) {
            try {
                byte[] bytes = Base64.decode(pub.getImagenBase64(), Base64.DEFAULT);

                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                holder.imagen.setImageBitmap(bitmap);

            } catch (Exception e) {
                holder.imagen.setImageResource(android.R.color.darker_gray);
            }
        } else {
            holder.imagen.setImageResource(android.R.color.darker_gray);
        }
        String comentario = pub.getComentario() != null ? pub.getComentario() : "";
        String fecha = pub.getFechaImagen() != null ? pub.getFechaImagen() : "";

        holder.comentarioFecha.setText(comentario + "\n" + fecha);
        holder.comentarioFecha.setTextColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return publicaciones != null ? publicaciones.size() : 0;
    }

    // --------------------------------------------------

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombreUsuario;
        ImageView imagen;
        TextView comentarioFecha;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreUsuario = itemView.findViewById(R.id.textViewNombreUs);
            imagen = itemView.findViewById(R.id.fotoFeed);
            comentarioFecha = itemView.findViewById(R.id.txtComentario);
        }
    }
}