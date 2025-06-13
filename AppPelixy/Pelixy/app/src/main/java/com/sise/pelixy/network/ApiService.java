package com.sise.pelixy.network;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.sise.pelixy.shared.Constants;

public class ApiService {

    public static StringRequest getPeliculas(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = Constants.BASE_URL + "/api/v1/peliculas";
        return new StringRequest(StringRequest.Method.GET, url, listener, errorListener);
    }

    public static StringRequest buscarPeliculasPorTitulo(String titulo, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = Constants.BASE_URL + "/api/v1/peliculas?titulo=" + titulo;
        return new StringRequest(StringRequest.Method.GET, url, listener, errorListener);
    }

    public static StringRequest getComentariosPorPelicula(int peliculaId, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = Constants.BASE_URL + "/api/v1/comentarios?pelicula_id=" + peliculaId;
        return new StringRequest(StringRequest.Method.GET, url, listener, errorListener);
    }
}
