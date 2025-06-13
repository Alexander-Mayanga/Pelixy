package com.sise.pelixy.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.sise.pelixy.R;
import com.sise.pelixy.shared.ApiClient;
import com.sise.pelixy.shared.Constants;
import java.util.HashMap;
import java.util.Map;

public class RegistrarComentarioActivity extends AppCompatActivity {
    private EditText editAutor, editContenido;
    private Button btnGuardarComentario;
    private int peliculaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_comentario);

        editAutor = findViewById(R.id.editAutor);
        editContenido = findViewById(R.id.editContenido);
        btnGuardarComentario = findViewById(R.id.btnGuardarComentario);

        peliculaId = getIntent().getIntExtra("pelicula_id", -1);

        btnGuardarComentario.setOnClickListener(v -> guardarComentario());
    }

    private void guardarComentario() {
        String autor = editAutor.getText().toString().trim();
        String contenido = editContenido.getText().toString().trim();

        if (autor.isEmpty() || contenido.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = Constants.BASE_URL + "/api/v1/comentarios";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                response -> {
                    Toast.makeText(this, "Comentario guardado", Toast.LENGTH_SHORT).show();
                    finish();
                },
                error -> Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("autor", autor);
                params.put("contenido", contenido);
                params.put("pelicula_id", String.valueOf(peliculaId));
                return params;
            }
        };

        ApiClient.getInstance(this).addToRequestQueue(request);
    }
}
