package com.sise.pelixy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sise.pelixy.adapter.PeliculaAdapter;
import com.sise.pelixy.entities.Pelicula;
import com.sise.pelixy.ui.BuscarPeliculasActivity;
import com.sise.pelixy.viewmodel.PeliculasViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PeliculasViewModel viewModel;
    private PeliculaAdapter adapter;
    private FloatingActionButton fabBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerPeliculas);
        fabBuscar = findViewById(R.id.fabBuscar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PeliculaAdapter(this::irADetalle);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(PeliculasViewModel.class);
        viewModel.init(this);
        viewModel.getPeliculas().observe(this, this::mostrarPeliculas);

        fabBuscar.setOnClickListener(v -> {
            Intent intent = new Intent(this, BuscarPeliculasActivity.class);
            startActivity(intent);
        });
    }

    private void mostrarPeliculas(List<Pelicula> lista) {
        if (lista != null && !lista.isEmpty()) {
            adapter.setPeliculas(lista);
        } else {
            Toast.makeText(this, "No hay películas disponibles", Toast.LENGTH_SHORT).show();
        }
    }

    private void irADetalle(Pelicula pelicula) {
        Intent intent = new Intent(this, com.sise.pelixy.activities.DetallePeliculaActivity.class);
        intent.putExtra("pelicula_id", pelicula.getId());
        startActivity(intent);
    }
}
