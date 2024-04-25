package com.gtappdevelopers.findtoday;

import android.widget.EditText;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ResumoDespActivity extends AppCompatActivity {

    private FinDatabase finDatabase;

    private Spinner spinnerOptions;
    private String[] options = {"Selecione", "valorDesp", "tipoDesp", "fontDesp", "despDescr", "dataDesp"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_desp);

        // Inicialize o banco de dados
        finDatabase = FinDatabase.getInstance(getApplicationContext());

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

        Button resumoButton = findViewById(R.id.idBtnFazerResumo);
        resumoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtém uma instância do Dao
                Dao dao = finDatabase.Dao();

                // Obtém os valores selecionados do Spinner e dos EditTexts
                //String selectedOption = options[spinnerOptions.getSelectedItemPosition()];
                String ano = ((EditText) findViewById(R.id.idEdtAno)).getText().toString();
                String mes = ((EditText) findViewById(R.id.idEdtMes)).getText().toString();

                // Chama um método do Dao para buscar informações
                LiveData<List<FinModal>> dados = dao.buscaDesp(null, null, null, null, null);
                // Cria e exibe o DialogFragment com os dados buscados
                DialogFragment dialogFragment = new ResumoDialogFragment(dados);
                dialogFragment.show(getSupportFragmentManager(), "ResultDialogFragment");
            }
        });
    }
}
