package com.example.myday;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorFeed extends RecyclerView.Adapter<AdaptadorFeed.MyViewHolder>{
//    ArrayList<DatosPerfiles> datosPerfiles;
    List<Usuario> usuarios;
    public AdaptadorFeed(List<Usuario> usuarios){
        this.usuarios = usuarios;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento= LayoutInflater.from(parent.getContext()).inflate(R.layout.celda,
                parent, false);
        MyViewHolder mvh = new MyViewHolder(elemento);
        return mvh;
        // return new MyViewHolder(elemento);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Usuario usu=this.usuarios.get(position);
        String foto = usu.getFotoperfil();

        byte[] decoded = android.util.Base64.decode(foto, android.util.Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
        holder.getNombre().setText(usu.getNombreUsuario());
        holder.getFotoPerfil().setImageBitmap(bitmap);
        holder.getfotoSubida().setImageBitmap(bitmap);


//        if (selectedPos == position)
//            holder.itemView.setBackgroundResource(R.color.seleccionado);
//        else holder.itemView.setBackgroundResource(R.color.colorcelda);
    }

    @Override
    public int getItemCount() {
        return this.usuarios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView nombreUsuario;
        ImageView fotoPerfil;
        ImageView fotoSubida;

        public MyViewHolder(View viewElemento) {
            super(viewElemento);
            this.nombreUsuario = viewElemento.findViewById(R.id.textViewNombreUs);
            this.fotoPerfil = viewElemento.findViewById(R.id.fotoPerf);
            this.fotoSubida = viewElemento.findViewById(R.id.fotoFeed);
        }
        public TextView getNombre() {
            return nombreUsuario;
        }
        public ImageView getFotoPerfil() {
            return fotoPerfil;
        }
        public ImageView getfotoSubida() {
            return fotoSubida;
        }
    }
}
