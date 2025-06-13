package com.sise.pelixy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sise.pelixy.activities.BuscarPeliculasActivity;
import com.sise.pelixy.activities.DetallePeliculaActivity;
import com.sise.pelixy.activities.RegistrarComentarioActivity;
import com.sise.pelixy.adapter.PeliculaAdapter;
import com.sise.pelixy.entities.Pelicula;
import com.sise.pelixy.viewmodel.PeliculasViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PeliculaAdapter adapter;
    private ProgressBar progressBar;
    private PeliculasViewModel viewModel;
    private FloatingActionButton fabBuscar, fabComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewPeliculas);
        progressBar = findViewById(R.id.progressBar);
        fabBuscar = findViewById(R.id.fabBuscar);
        fabComentario = findViewById(R.id.fabComentario);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PeliculaAdapter(pelicula -> irADetalle(pelicula));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(PeliculasViewModel.class);

        observarPeliculas();

        fabBuscar.setOnClickListener(v -> {
            Intent intent = new Intent(this, BuscarPeliculasActivity.class);
            startActivity(intent);
        });

        fabComentario.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegistrarComentarioActivity.class);
            startActivity(intent);
        });
    }

    private void observarPeliculas() {
        progressBar.setVisibility(View.VISIBLE);
        viewModel.obtenerPeliculas().observe(this, peliculas -> {
            progressBar.setVisibility(View.GONE);
            if (peliculas != null) {
                adapter.actualizarLista(peliculas);
            } else {
                Toast.makeText(this, "Error al obtener películas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void irADetalle(Pelicula pelicula) {
        Intent intent = new Intent(this, DetallePeliculaActivity.class);
        intent.putExtra("pelicula_id", pelicula.getId());
        startActivity(intent);
    }
}
