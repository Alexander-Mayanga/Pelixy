package com.sise.pelixy.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.toolbox.StringRequest;
import com.sise.pelixy.R;
import com.sise.pelixy.adapter.PeliculaAdapter;
import com.sise.pelixy.entities.Pelicula;
import com.sise.pelixy.network.ApiService;
import com.sise.pelixy.shared.ApiClient;
import com.sise.pelixy.shared.Utils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class BuscarPeliculasActivity extends AppCompatActivity {

    private EditText editBuscarTitulo;
    private Button btnBuscar;
    private ListView listViewPeliculas;

    private List<Pelicula> peliculas = new ArrayList<>();
    private PeliculaAdapter peliculaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_peliculas);

        editBuscarTitulo = findViewById(R.id.editBuscarTitulo);
        btnBuscar = findViewById(R.id.btnBuscar);
        listViewPeliculas = findViewById(R.id.listViewPeliculas);

        peliculaAdapter = new PeliculaAdapter(this, peliculas);
        listViewPeliculas.setAdapter(peliculaAdapter);

        btnBuscar.setOnClickListener(v -> buscarPeliculas());
    }

    private void buscarPeliculas() {
        String titulo = editBuscarTitulo.getText().toString().trim();
        if (titulo.isEmpty()) {
            Toast.makeText(this, "Ingresa un título", Toast.LENGTH_SHORT).show();
            return;
        }

        peliculas.clear();

        StringRequest request = ApiService.buscarPeliculasPorTitulo(titulo,
                response -> {
                    if (Utils.isJSONArray(response)) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                Pelicula p = new Pelicula(
                                        obj.getInt("id"),
                                        obj.getString("titulo"),
                                        obj.getString("genero")
                                );
                                peliculas.add(p);
                            }
                            peliculaAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(this, "Error procesando respuesta", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(this, "Error en búsqueda", Toast.LENGTH_SHORT).show());

        ApiClient.getInstance(this).addToRequestQueue(request);
    }
}
