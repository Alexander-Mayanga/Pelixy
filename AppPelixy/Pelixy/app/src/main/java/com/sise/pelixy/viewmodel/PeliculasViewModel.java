package com.sise.pelixy.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.sise.pelixy.entities.Pelicula;
import com.sise.pelixy.repositories.PeliculasRepository;
import com.sise.pelixy.shared.ResponseHandler;
import java.util.List;

public class PeliculasViewModel extends ViewModel {

    private MutableLiveData<List<Pelicula>> peliculasLiveData;
    private PeliculasRepository repository;

    public void init(Context context) {
        if (peliculasLiveData != null) return;
        peliculasLiveData = new MutableLiveData<>();
        repository = new PeliculasRepository(context);
        cargarPeliculas();
    }

    public LiveData<List<Pelicula>> getPeliculas() {
        return peliculasLiveData;
    }

    public void cargarPeliculas() {
        repository.obtenerPeliculas(new ResponseHandler<List<Pelicula>>() {
            @Override
            public void onSuccess(List<Pelicula> response) {
                peliculasLiveData.setValue(response);
            }

            @Override
            public void onError(String message) {
                // Manejar errores si se desea
            }
        });
    }
}
