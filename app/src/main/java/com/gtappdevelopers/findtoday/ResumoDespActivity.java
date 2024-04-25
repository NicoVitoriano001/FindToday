package com.gtappdevelopers.findtoday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class ResumoDespActivity extends AppCompatActivity {

    private Button FinBtnResumo;
    private Spinner spinnerOptions;
    private String[] options = {"Selecione", "valorDesp", "tipoDesp", "fontDesp", "despDescr", "dataDesp"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_desp);

        spinnerOptions = findViewById(R.id.spinnerOptions);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOptions.setAdapter(adapter);
        spinnerOptions.setSelection(0, false);
        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // Se o hint for selecionado, não faça nada ou realize alguma ação específica
                } else {
                    // Se uma opção válida for selecionada, realize alguma ação
                    String selectedOption = options[position];
                    // Faça algo com a opção selecionada
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Este método é chamado quando nada é selecionado no Spinner
            }
        });

        FinBtnResumo = findViewById(R.id.idBtnFazerResumo);
        FinBtnResumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumoDespActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
