package com.sise.pelixy.repositories;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.sise.pelixy.entities.Pelicula;
import com.sise.pelixy.shared.ApiClient;
import com.sise.pelixy.shared.Constants;
import com.sise.pelixy.shared.ResponseHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class PeliculasRepository {

    private Context context;

    public PeliculasRepository(Context context) {
        this.context = context;
    }

    public void obtenerPeliculas(ResponseHandler<List<Pelicula>> handler) {
        String url = Constants.BASE_URL + "/api/v1/peliculas";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> handler.onSuccess(parsePeliculas(response)),
                error -> handler.onError("Error al obtener películas")
        );

        ApiClient.getInstance(context).addToRequestQueue(request);
    }

    private List<Pelicula> parsePeliculas(JSONArray jsonArray) {
        List<Pelicula> lista = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Pelicula pelicula = new Pelicula();
                pelicula.setId(obj.getInt("id"));
                pelicula.setTitulo(obj.getString("titulo"));
                pelicula.setGenero(obj.getString("genero"));
                lista.add(pelicula);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
