package com.sise.pelixy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.toolbox.StringRequest;
import com.sise.pelixy.R;
import com.sise.pelixy.adapter.ComentarioAdapter;
import com.sise.pelixy.entities.Comentario;
import com.sise.pelixy.network.ApiService;
import com.sise.pelixy.shared.Utils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class DetallePeliculaActivity extends AppCompatActivity {

    private TextView txtTituloDetalle, txtGeneroDetalle;
    private Button btnAgregarComentario;
    private ListView listComentarios;

    private int peliculaId;
    private String titulo, genero;

    private List<Comentario> comentarios = new ArrayList<>();
    private ComentarioAdapter comentarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        txtTituloDetalle = findViewById(R.id.txtTituloDetalle);
        txtGeneroDetalle = findViewById(R.id.txtGeneroDetalle);
        btnAgregarComentario = findViewById(R.id.btnAgregarComentario);
        listComentarios = findViewById(R.id.listComentarios);

        Intent intent = getIntent();
        peliculaId = intent.getIntExtra("pelicula_id", -1);
        titulo = intent.getStringExtra("titulo");
        genero = intent.getStringExtra("genero");

        txtTituloDetalle.setText(titulo);
        txtGeneroDetalle.setText(genero);

        comentarioAdapter = new ComentarioAdapter(this, comentarios);
        listComentarios.setAdapter(comentarioAdapter);

        cargarComentarios();

        btnAgregarComentario.setOnClickListener(v -> {
            Intent i = new Intent(this, RegistrarComentarioActivity.class);
            i.putExtra("pelicula_id", peliculaId);
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarComentarios();
    }

    private void cargarComentarios() {
        comentarios.clear();

        StringRequest request = ApiService.getComentariosPorPelicula(peliculaId,
                response -> {
                    if (Utils.isJSONArray(response)) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                Comentario c = new Comentario(
                                        obj.getInt("id"),
                                        obj.getString("autor"),
                                        obj.getString("contenido"),
                                        obj.getInt("pelicula_id")
                                );
                                comentarios.add(c);
                            }
                            comentarioAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(this, "Error al procesar comentarios", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "No se encontraron comentarios", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(this, "Error al cargar comentarios", Toast.LENGTH_SHORT).show());

        ApiClient.getInstance(this).addToRequestQueue(request);
    }
}
