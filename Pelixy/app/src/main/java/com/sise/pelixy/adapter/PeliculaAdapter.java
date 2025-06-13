package com.sise.pelixy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sise.pelixy.R;
import com.sise.pelixy.activities.DetallePeliculaActivity;
import com.sise.pelixy.entities.Pelicula;
import java.util.List;

public class PeliculaAdapter extends BaseAdapter {

    private Context context;
    private List<Pelicula> peliculas;

    public PeliculaAdapter(Context context, List<Pelicula> peliculas) {
        this.context = context;
        this.peliculas = peliculas;
    }

    @Override
    public int getCount() {
        return peliculas.size();
    }

    @Override
    public Object getItem(int position) {
        return peliculas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return peliculas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pelicula pelicula = peliculas.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pelicula, parent, false);
        }

        TextView txtTitulo = convertView.findViewById(R.id.txtTitulo);
        TextView txtGenero = convertView.findViewById(R.id.txtGenero);

        txtTitulo.setText(pelicula.getTitulo());
        txtGenero.setText(pelicula.getGenero());

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetallePeliculaActivity.class);
            intent.putExtra("pelicula_id", pelicula.getId());
            intent.putExtra("titulo", pelicula.getTitulo());
            intent.putExtra("genero", pelicula.getGenero());
            context.startActivity(intent);
        });

        return convertView;
    }
}
