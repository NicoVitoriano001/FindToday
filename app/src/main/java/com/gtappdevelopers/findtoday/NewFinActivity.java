package com.gtappdevelopers.findtoday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import androidx.core.app.ActivityCompat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class NewFinActivity extends AppCompatActivity {
    private EditText valorDespEdt, tipoDespEdt, fontDespEdt, despDescrEdt, dataDespEdt;
    private Button FinBtnSave;
    private Button FinBtnConsult;
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_VALOR_DESP = "com.gtappdevelopers.gfgroomdatabase.EXTRA_VALOR_DESP";
    public static final String EXTRA_TIPO_DESP = "com.gtappdevelopers.gfgroomdatabase.EXTRA_TIPO_DESP";
    public static final String EXTRA_FONT_DESP = "com.gtappdevelopers.gfgroomdatabase.EXTRA_FONT_DESP";
    public static final String EXTRA_DESCR_DESP = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DESP_DESCR";
    public static final String EXTRA_DURATION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DURATION";
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1001;

    public String getDataHoraAtual() {
        Locale locale = new Locale("pt", "BR");
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, yyyy-MM-dd", locale);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dataHoraAtual.format(formatter);
    }

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
        FinBtnSave = findViewById(R.id.idBtnSaveDesp);
        FinBtnConsult = findViewById(R.id.idBtnConsultarResumo);
        String dataHoraAtual = getDataHoraAtual();
        dataDespEdt.setText(dataHoraAtual);

        /*está verificando se a atividade foi iniciada com dados extras na Intent e,
        se houver, você está recuperando esses dados e preenchendo os campos da
        interface do usuário com esses valores.*/
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            valorDespEdt.setText(intent.getStringExtra(EXTRA_VALOR_DESP));
            tipoDespEdt.setText(intent.getStringExtra(EXTRA_TIPO_DESP));
            fontDespEdt.setText(intent.getStringExtra(EXTRA_FONT_DESP));
            despDescrEdt.setText(intent.getStringExtra(EXTRA_DESCR_DESP));
            dataDespEdt.setText(intent.getStringExtra(EXTRA_DURATION));
        }

        FinBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting text value from edittext and validating if the text fields are empty or not.
                //float valorDesp = Float.parseFloat(valorDespEdt.getText().toString());
                String valorDesp = valorDespEdt.getText().toString();
                String tipoDesp = tipoDespEdt.getText().toString();
                String fontDesp = fontDespEdt.getText().toString();
                String despDescr = despDescrEdt.getText().toString();
                String dataDesp = dataDespEdt.getText().toString();

                if (valorDesp.isEmpty() || tipoDesp.isEmpty() || despDescr.isEmpty() || dataDesp.isEmpty()) {
                    Toast.makeText(NewFinActivity.this, "Entre com todos valores do resgistro.", Toast.LENGTH_LONG).show();
                    return;
                }
                //calling a method to save our course.
                saveFin(valorDesp, tipoDesp, fontDesp, despDescr, dataDesp);
            }
        });

        FinBtnConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir a activity_resumo_desp.xml
                Intent intent = new Intent(NewFinActivity.this, ResumoDespActivity.class);
                startActivity(intent);
            }
        });

        Button bkpButton = findViewById(R.id.idBtnbkp);
        bkpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(NewFinActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(NewFinActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
                } else {
                    boolean backupSuccess = DatabaseBackup.backupDatabase(NewFinActivity.this);
                    if (backupSuccess) {
                        Toast.makeText(NewFinActivity.this, "Backup do banco de dados concluído com sucesso.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NewFinActivity.this, "Falha ao realizar backup do banco de dados.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void saveFin(String valorDesp, String tipoDesp, String fontDesp, String despDescr, String dataDesp) {
        //PASSA OS DADOS NOVOS/RECUPERADOS PARA SALVAR
        Intent data = new Intent();
        data.putExtra(NewFinActivity.EXTRA_VALOR_DESP, valorDesp);
        data.putExtra(NewFinActivity.EXTRA_TIPO_DESP, tipoDesp);
        data.putExtra(NewFinActivity.EXTRA_FONT_DESP, fontDesp);
        data.putExtra(NewFinActivity.EXTRA_DESCR_DESP, despDescr);
        data.putExtra(NewFinActivity.EXTRA_DURATION, dataDesp);

        int id = getIntent().getIntExtra(NewFinActivity.EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(NewFinActivity.EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        Toast.makeText(this, "Registro foi salvo no Database -EDIT.", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                boolean backupSuccess = DatabaseBackup.backupDatabase(this);
                if (backupSuccess) {
                    Toast.makeText(this, "Backup do banco de dados concluído com sucesso.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Falha ao realizar o backup do banco de dados.", Toast.LENGTH_SHORT).show();
                }
            }   else {
                Toast.makeText(this, "Permissão de escrita no armazenamento externo negada.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}