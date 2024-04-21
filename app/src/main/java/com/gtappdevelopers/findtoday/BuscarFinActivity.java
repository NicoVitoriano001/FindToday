package com.gtappdevelopers.findtoday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class BuscarFinActivity extends AppCompatActivity {
    //creating a variables for our button and edittext.
    private EditText valorDespEdtBusca, tipoDespEdtBusca, fontDespEdtBusca, despDescrEdtBusca, dataDespEdtBusca;
    private Button FinBtnBusca;
    //creating a constant string variable for our course name, description and duration.
    public static final String EXTRA_ID_BUSCA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID_BUSCA";
    public static final String EXTRA_VALOR_DESP_BUSCA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_VALOR_DESP_BUSCA";
    public static final String EXTRA_TIPO_DESP_BUSCA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_TIPO_DESP_BUSCA";
    public static final String EXTRA_FONT_DESP_BUSCA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_FONT_DESP_BUSCA";
    public static final String EXTRA_DESCR_DESP_BUSCA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DESP_DESCR";
    public static final String EXTRA_DURATION_BUSCA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DURATION_BUSCA";

    //passar data e hora para toast
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_fin);
        //tela inicial dos novos
        valorDespEdtBusca = findViewById(R.id.idEdtValorDespBuscar);
        tipoDespEdtBusca = findViewById(R.id.idEdtTipoDespBuscar);
        fontDespEdtBusca = findViewById(R.id.idEdtFontDespBuscar);
        despDescrEdtBusca = findViewById(R.id.idEdtDespDescrBuscar);
        dataDespEdtBusca = findViewById(R.id.idEdtDataDespBuscar);
        FinBtnBusca = findViewById(R.id.idBtnBuscarDesp);

        /*está verificando se a atividade foi iniciada com dados extras na Intent e,
        se houver, você está recuperando esses dados e preenchendo os campos da
        interface do usuário com esses valores.*/

        //configurando um ouvinte de clique para o botão FinBtnBusca. Quando o botão é clicado,
        // o código dentro do método onClick() é executado.
        FinBtnBusca.setOnClickListener(new View.OnClickListener() {//botao de salvar
            @Override
            public void onClick(View v) {
                //getting text value from edittext and validating if the text fields are empty or not.
                //float valorDesp = Float.parseFloat(valorDespEdtBusca.getText().toString());
                String valorDesp = valorDespEdtBusca.getText().toString();
                String tipoDesp = tipoDespEdtBusca.getText().toString();
                String fontDesp = fontDespEdtBusca.getText().toString();
                String despDescr = despDescrEdtBusca.getText().toString();
                String dataDesp = dataDespEdtBusca.getText().toString();

                if (valorDesp.isEmpty() || tipoDesp.isEmpty() || fontDesp.isEmpty() || despDescr.isEmpty() || dataDesp.isEmpty()) {
                    Toast.makeText(BuscarFinActivity.this, "Entre com todos valores do resgistro.", Toast.LENGTH_LONG).show();
                    return;
                }
                //calling a method to save our course.
                //saveFin(valorDesp, tipoDesp, fontDesp, despDescr, dataDesp);
            }
        });
    }

    private void saveFin(String valorDesp, String tipoDesp, String fontDesp, String despDescr, String dataDesp) {
        //PASSA OS DADOS NOVOS/RECUPERADOS PARA SALVAR
        Intent data = new Intent();
        data.putExtra(EXTRA_VALOR_DESP_BUSCA, valorDesp);
        data.putExtra(EXTRA_TIPO_DESP_BUSCA, tipoDesp);
        data.putExtra(EXTRA_FONT_DESP_BUSCA, fontDesp);
        data.putExtra(EXTRA_DESCR_DESP_BUSCA, despDescr);
        data.putExtra(EXTRA_DURATION_BUSCA, dataDesp);

        int id = getIntent().getIntExtra(EXTRA_ID_BUSCA, -1);//SENDO NOVO -1, NAO SENDO PEGA ID
        if (id != -1) {
            //in below line we are passing our id.
            data.putExtra(EXTRA_ID_BUSCA, id);
        }
        /*os dados do curso são empacotados em um Intent e enviados de volta para a atividade
        anterior usando setResult(RESULT_OK, data)*/
        setResult(RESULT_OK, data);
        //displaying a toast message after adding the data
        Toast.makeText(this, "Registro foi salvo no Database -EDIT.", Toast.LENGTH_LONG).show();
    }
}
