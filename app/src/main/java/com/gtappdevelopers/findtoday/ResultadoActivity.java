package com.gtappdevelopers.findtoday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ResultadoActivity extends AppCompatActivity {
    private RecyclerView idRVRetorno;
    private FinRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retorno_rv);

        // Inicializar RecyclerView
        idRVRetorno = findViewById(R.id.idRVRetorno);
        adapter = new FinRVAdapter();
        idRVRetorno.setLayoutManager(new LinearLayoutManager(this));
        idRVRetorno.setAdapter(adapter);

        // Obter os resultados passados da tela anterior
        ArrayList<Parcelable> parcelableList = getIntent().getParcelableArrayListExtra("resultados");
        List<FinModal> resultados = new ArrayList<>();

        if (parcelableList != null) {
            for (Parcelable parcelable : parcelableList) {
                if (parcelable instanceof FinModal) {
                    resultados.add((FinModal) parcelable);
                }
            }
            // Atualize o adaptador com os resultados
            adapter.submitList(resultados);
        }
    }


}
