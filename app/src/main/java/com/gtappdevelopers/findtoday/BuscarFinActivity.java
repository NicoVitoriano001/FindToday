package com.gtappdevelopers.findtoday;

import com.gtappdevelopers.findtoday.ResultadoActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BuscarFinActivity extends AppCompatActivity {
    //creating a variables for our button and edittext.

    //creating a constant string variable for our course name, description and duration.
    public static final String EXTRA_ID_BUSCA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID_BUSCA";
    public static final String EXTRA_VALOR_DESP_BUSCA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_VALOR_DESP_BUSCA";
    public static final String EXTRA_TIPO_DESP_BUSCA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_TIPO_DESP_BUSCA";
    public static final String EXTRA_FONT_DESP_BUSCA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_FONT_DESP_BUSCA";
    public static final String EXTRA_DESCR_DESP_BUSCA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DESP_DESCR";
    public static final String EXTRA_DURATION_BUSCA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DURATION_BUSCA";
    private Dao dao;
    private EditText valorDespEdtBusca, tipoDespEdtBusca, fontDespEdtBusca, despDescrEdtBusca, dataDespEdtBusca;
    private Button FinBtnBusca;
    private FinRVAdapter adapter; // Adaptador para RecyclerView

    private RecyclerView idRVRetorno; // Adicione esta linha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_fin); // Defina o layout para activity_busca_fin.xml

        dao = FinDatabase.getInstance(this).Dao();

        valorDespEdtBusca = findViewById(R.id.idEdtValorDespBuscar);
        tipoDespEdtBusca = findViewById(R.id.idEdtTipoDespBuscar);
        fontDespEdtBusca = findViewById(R.id.idEdtFontDespBuscar);
        despDescrEdtBusca = findViewById(R.id.idEdtDespDescrBuscar);
        dataDespEdtBusca = findViewById(R.id.idEdtDataDespBuscar);
        FinBtnBusca = findViewById(R.id.idBtnBuscarDesp);

        FinBtnBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorDesp = valorDespEdtBusca.getText().toString();
                String tipoDesp = tipoDespEdtBusca.getText().toString();
                String fontDesp = fontDespEdtBusca.getText().toString();
                String despDescr = despDescrEdtBusca.getText().toString();
                String dataDesp = dataDespEdtBusca.getText().toString();

                // Chame o método de busca no DAO e observe os resultados
                dao.buscaDesp(valorDesp, tipoDesp, fontDesp, despDescr, dataDesp)
                        .observe(BuscarFinActivity.this, new Observer<List<FinModal>>() {
                            @Override
                            public void onChanged(List<FinModal> finModals) {
                                Intent intent = new Intent(BuscarFinActivity.this, ResultadoActivity.class);
                                intent.putExtra("resultados", new ArrayList<>(finModals));
                                startActivity(intent);
                            }
                        });
            }
        });
    }



    private void exibirResultados(List<FinModal> resultados) {
            ResultadosDialogFragment dialogFragment = ResultadosDialogFragment.newInstance(resultados);
            dialogFragment.show(getSupportFragmentManager(), "resultados_dialog");
    }



}
