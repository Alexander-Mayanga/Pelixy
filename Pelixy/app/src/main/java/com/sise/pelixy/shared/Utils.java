package com.sise.pelixy.shared;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void mostrarToast(Context context, String mensaje) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }
}
