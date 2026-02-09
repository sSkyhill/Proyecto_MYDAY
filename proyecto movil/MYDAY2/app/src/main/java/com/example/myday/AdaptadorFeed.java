package com.example.myday;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorFeed extends RecyclerView.Adapter<AdaptadorFeed.MyViewHolder>{
    ArrayList<DatosPerfiles> datosPerfiles;
    public AdaptadorFeed(ArrayList<DatosPerfiles> datosPerfiles){
        this.datosPerfiles = datosPerfiles;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento= LayoutInflater.from(parent.getContext()).inflate(R.layout.celda,
                parent, false);
        MyViewHolder mvh = new MyViewHolder(elemento);
        return mvh ;
        // return new MyViewHolder(elemento);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DatosPerfiles dp=this.datosPerfiles.get(position);
        holder.getNombre().setText(dp.getNombreUsuario());
        holder.getFotoPerfil().setImageResource(dp.getFotoPerfil());
        holder.getfotoSubida().setImageResource(dp.getFotoMuro());


//        if (selectedPos == position)
//            holder.itemView.setBackgroundResource(R.color.seleccionado);
//        else holder.itemView.setBackgroundResource(R.color.colorcelda);
    }

    @Override
    public int getItemCount() {
        return this.datosPerfiles.size();
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
