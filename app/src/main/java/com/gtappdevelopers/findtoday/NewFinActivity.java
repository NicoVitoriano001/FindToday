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

    // Variáveis para os componentes da tela
    private EditText valorDespEdt, tipoDespEdt, natDespEdt, despDescrEdt, dataDespEdt;
    private Button finBtn;

    // Constantes para passar dados entre atividades
    public static final String EXTRA_ID = "com.gtappdevelopers.findtoday.EXTRA_ID";
    public static final String EXTRA_VALOR_DESP = "com.gtappdevelopers.findtoday.EXTRA_VALOR_DESP";
    public static final String EXTRA_TIPO_DESP = "com.gtappdevelopers.findtoday.EXTRA_TIPO_DESP";
    public static final String EXTRA_NAT_DESP = "com.gtappdevelopers.findtoday.EXTRA_NAT_DESP";
    public static final String EXTRA_DESCR_DESP = "com.gtappdevelopers.findtoday.EXTRA_DESCR_DESP";
    public static final String EXTRA_DURATION = "com.gtappdevelopers.findtoday.EXTRA_DURATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fin);

        // Inicializando os componentes da tela
        valorDespEdt = findViewById(R.id.idEdtValorDesp);
        tipoDespEdt = findViewById(R.id.idEdtTipoDesp);
        natDespEdt = findViewById(R.id.idEdtNatDesp);
        despDescrEdt = findViewById(R.id.idEdtDespDescr);
        dataDespEdt = findViewById(R.id.idEdtDataDesp);
        finBtn = findViewById(R.id.idBtnSaveCourse);

        // Preenchendo o EditText com a data e hora atual formatada
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        dataDespEdt.setText(formattedDateTime);

        // Configurando o botão de salvar
        finBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtendo os valores dos EditTexts
                float valorDesp = Float.parseFloat(valorDespEdt.getText().toString());
                String tipoDesp = tipoDespEdt.getText().toString();
                String natDesp = natDespEdt.getText().toString();
                String despDescr = despDescrEdt.getText().toString();
               //String dataDesp = dataDespEdt.getText().toString();
                String dataDespString = dataDespEdt.getText().toString();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dataDesp = LocalDateTime.parse(dataDespString, formatter);

                // Verificando se os campos estão preenchidos
                if (String.valueOf(valorDesp).isEmpty() || tipoDesp.isEmpty() || despDescr.isEmpty() || dataDesp == null) {
                    Toast.makeText(NewFinActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                    return;
                }

                // Salvando o curso
                saveCourse(valorDesp, tipoDesp, natDesp, despDescr, dataDesp);
            }
        });
    }

    private void saveCourse(float valorDesp, String tipoDesp, String natDesp, String despDescr, LocalDateTime dataDesp) {
        // Criando uma intent para retornar os dados
        Intent data = new Intent();
        data.putExtra(EXTRA_VALOR_DESP, valorDesp);
        data.putExtra(EXTRA_TIPO_DESP, tipoDesp);
        data.putExtra(EXTRA_NAT_DESP, natDesp);
        data.putExtra(EXTRA_DESCR_DESP, despDescr);
        //data.putExtra(EXTRA_DURATION, dataDesp);
        // Convertendo LocalDateTime para String usando um formato específico
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataDespString = dataDesp.format(formatter);
        data.putExtra(EXTRA_DURATION, dataDespString);

        // Verificando se existe um ID
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        // Definindo o resultado como OK e retornando os dados
        setResult(RESULT_OK, data);
        finish();
    }

}
