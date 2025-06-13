package com.sise.pelixy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sise.pelixy.R;
import com.sise.pelixy.entities.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Pelicula pelicula);
    }

    private List<Pelicula> listaPeliculas = new ArrayList<>();
    private final OnItemClickListener listener;

    public PeliculaAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void actualizarLista(List<Pelicula> nuevasPeliculas) {
        listaPeliculas.clear();
        listaPeliculas.addAll(nuevasPeliculas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false);
        return new PeliculaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        holder.bind(listaPeliculas.get(position));
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    class PeliculaViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtTitulo, txtGenero;

        PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtGenero = itemView.findViewById(R.id.txtGenero);
        }

        void bind(final Pelicula pelicula) {
            txtTitulo.setText(pelicula.getTitulo());
            txtGenero.setText(pelicula.getGenero());
            itemView.setOnClickListener(v -> listener.onItemClick(pelicula));
        }
    }
}
