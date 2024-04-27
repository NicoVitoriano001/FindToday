package com.gtappdevelopers.findtoday;

import android.widget.EditText;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ResumoDespActivity extends AppCompatActivity {
    private FinDatabase finDatabase;
    private String[] options = {"Selecione", "valorDesp", "tipoDesp", "fontDesp", "despDescr", "dataDesp"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_desp);

        finDatabase = FinDatabase.getInstance(getApplicationContext());

        Button resumoButton = findViewById(R.id.idBtnFazerResumo);
        resumoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dao dao = finDatabase.Dao();
                String ano = ((EditText) findViewById(R.id.idEdtAno)).getText().toString();
                String mes = ((EditText) findViewById(R.id.idEdtMes)).getText().toString();

                // Usa SUBSTR para extrair ano e mês da data
                LiveData<List<FinModal>> dados = dao.buscaPorAnoEMes(ano, mes);

                // Cria e exibe o DialogFragment com os dados buscados
                DialogFragment dialogFragment = new ResumoDialogFragment(dados);
                dialogFragment.show(getSupportFragmentManager(), "ResultDialogFragment");
            }
        });
    }

    }
