package com.gtappdevelopers.findtoday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewFinActivity extends AppCompatActivity {

    //creating a variables for our button and edittext.
    private EditText valorDespEdt, tipoDespEdt, fontDespEdt, despDescrEdt, dataDespEdt;
    private Button FinBtn;

    //creating a constant string variable for our course name, description and duration.
    public static final String EXTRA_ID =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_VALOR_DESP =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_VALOR_DESP";
    public static final String EXTRA_TIPO_DESP =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_TIPO_DESP";
    public static final String EXTRA_NAT_DESP =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_NAT_DESP";
    public static final String EXTRA_DESCR_DESP =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_DESP_DESCR";
    public static final String EXTRA_DURATION =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_DURATION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_fin);
        //tela inicial dos novos
        valorDespEdt = findViewById(R.id.idEdtValorDesp);
        tipoDespEdt = findViewById(R.id.idEdtTipoDesp);
        fontDespEdt = findViewById(R.id.idEdtFontDesp);
        despDescrEdt = findViewById(R.id.idEdtDespDescr);
        dataDespEdt = findViewById(R.id.idEdtDataDesp);
        FinBtn = findViewById(R.id.idBtnSaveDesp);
        // Prencher dataHora automatico. Inicializando os componentes da tela
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataHoraFormatada = dataHoraAtual.format(formatter);
        dataDespEdt.setText(dataHoraFormatada);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            valorDespEdt.setText(intent.getStringExtra(EXTRA_VALOR_DESP));
            tipoDespEdt.setText(intent.getStringExtra(EXTRA_TIPO_DESP));
            fontDespEdt.setText(intent.getStringExtra(EXTRA_NAT_DESP));
            despDescrEdt.setText(intent.getStringExtra(EXTRA_DESCR_DESP));
            dataDespEdt.setText(intent.getStringExtra(EXTRA_DURATION));
        }
        //adding on click listner for our save button.
        FinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting text value from edittext and validating if the text fields are empty or not.
                float valorDesp = Float.parseFloat(valorDespEdt.getText().toString());
                String tipoDesp = tipoDespEdt.getText().toString();
                String fontDesp = fontDespEdt.getText().toString();
                String despDescr = despDescrEdt.getText().toString();
                String dataDesp = dataDespEdt.getText().toString();

                if (String.valueOf(valorDesp).isEmpty() || tipoDesp.isEmpty() || despDescr.isEmpty() || dataDesp.isEmpty()) {
                    Toast.makeText(NewFinActivity.this, "Entre com todos valores do resgistro.", Toast.LENGTH_LONG).show();
                    return;
                }
                //calling a method to save our course.
                saveCourse(valorDesp, tipoDesp, fontDesp, despDescr, dataDesp);
            }
        });

    }

    private void saveCourse(float valorDesp, String tipoDesp, String fontDesp, String despDescr, String dataDesp) {
        //PASSA OS DADOS NOVOS/RECUPERADOS PARA SALVAR
        Intent data = new Intent();
        data.putExtra(EXTRA_VALOR_DESP, valorDesp);
        data.putExtra(EXTRA_TIPO_DESP, tipoDesp);
        data.putExtra(EXTRA_NAT_DESP, fontDesp);
        data.putExtra(EXTRA_DESCR_DESP, despDescr);
        data.putExtra(EXTRA_DURATION, dataDesp);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);//SENDO NOVO -1, NAO SENDO PEGA ID
        if (id != -1) {
            //in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }
        //at last we are setting result as data.
        setResult(RESULT_OK, data);
        //displaying a toast message after adding the data
        Toast.makeText(this, "Registro foi salvo no Database -EDIT.", Toast.LENGTH_LONG).show();
    }

}