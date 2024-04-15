package com.gtappdevelopers.findtoday;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class BuscarFinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_fin);
    }

    public static void showToast(MainActivity context) {
        Toast.makeText(context, "Buscar FIN.", Toast.LENGTH_LONG).show();
    }
}
