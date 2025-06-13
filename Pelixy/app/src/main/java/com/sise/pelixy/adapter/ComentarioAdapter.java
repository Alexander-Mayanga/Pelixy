package com.sise.pelixy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sise.pelixy.R;
import com.sise.pelixy.entities.Comentario;
import java.util.List;

public class ComentarioAdapter extends BaseAdapter {

    private Context context;
    private List<Comentario> comentarios;

    public ComentarioAdapter(Context context, List<Comentario> comentarios) {
        this.context = context;
        this.comentarios = comentarios;
    }

    @Override
    public int getCount() {
        return comentarios.size();
    }

    @Override
    public Object getItem(int position) {
        return comentarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return comentarios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comentario comentario = comentarios.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comentario, parent, false);
        }

        TextView txtAutor = convertView.findViewById(R.id.txtAutor);
        TextView txtContenido = convertView.findViewById(R.id.txtContenido);

        txtAutor.setText(comentario.getAutor());
        txtContenido.setText(comentario.getContenido());

        return convertView;
    }
}
