package com.gtappdevelopers.findtoday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

public class NewFinActivity extends AppCompatActivity {
    private EditText valorDespEdt, despDescrEdt, dataDespEdt;
    private Spinner tipoDespEdt, fontDespEdt;
    private Button FinBtnSave, FinBtnConsult;
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_VALOR_DESP = "com.gtappdevelopers.gfgroomdatabase.EXTRA_VALOR_DESP";
    public static final String EXTRA_TIPO_DESP = "com.gtappdevelopers.gfgroomdatabase.EXTRA_TIPO_DESP";
    public static final String EXTRA_FONT_DESP = "com.gtappdevelopers.gfgroomdatabase.EXTRA_FONT_DESP";
    public static final String EXTRA_DESCR_DESP = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DESP_DESCR";
    public static final String EXTRA_DURATION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DURATION";
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1001;

    public String getDataHoraAtual() {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE yyyy-MM-dd");
        return dataHoraAtual.format(formatter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fin);

        valorDespEdt = findViewById(R.id.idEdtValorDesp);
        tipoDespEdt = findViewById(R.id.idEdtTipoDesp);
        fontDespEdt = findViewById(R.id.idEdtFontDesp);
        despDescrEdt = findViewById(R.id.idEdtDespDescr);
        dataDespEdt = findViewById(R.id.idEdtDataDesp);
        FinBtnSave = findViewById(R.id.idBtnSaveDesp);
        FinBtnConsult = findViewById(R.id.idBtnConsultarResumo);

        // Configurando os Spinners
        setupSpinners();

        dataDespEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        String dataHoraAtual = getDataHoraAtual();
        dataDespEdt.setText(dataHoraAtual);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            valorDespEdt.setText(intent.getStringExtra(EXTRA_VALOR_DESP));
            tipoDespEdt.setSelection(getIndex(tipoDespEdt, intent.getStringExtra(EXTRA_TIPO_DESP)));
            fontDespEdt.setSelection(getIndex(fontDespEdt, intent.getStringExtra(EXTRA_FONT_DESP)));
            despDescrEdt.setText(intent.getStringExtra(EXTRA_DESCR_DESP));
            dataDespEdt.setText(intent.getStringExtra(EXTRA_DURATION));
        }

        FinBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorDesp = valorDespEdt.getText().toString();
                String tipoDesp = tipoDespEdt.getSelectedItem().toString();
                String fontDesp = fontDespEdt.getSelectedItem().toString();
                String despDescr = despDescrEdt.getText().toString();
                String dataDesp = dataDespEdt.getText().toString();

                if (valorDesp.isEmpty() || tipoDesp.isEmpty() || despDescr.isEmpty() || dataDesp.isEmpty()) {
                    Toast.makeText(NewFinActivity.this, "Entre com todos valores do registro.", Toast.LENGTH_LONG).show();
                    return;
                }
                saveFin(valorDesp, tipoDesp, fontDesp, despDescr, dataDesp);
            }
        });

        FinBtnConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void setupSpinners() {
        // Configurando o Spinner para Tipo de Despesa
        String[] tiposDespesa = {"-","ALIM", "CRED", "D PUB","EDUC", "EMPREST", "INVEST","LAZER","OUTR", "TRANSP","SAUDE"};
        ArrayAdapter<String> tipoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposDespesa);
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoDespEdt.setAdapter(tipoAdapter);

        // Configurando o Spinner para Fonte de Despesa
        String[] fontesDespesa = {"-","ALELO","BB","BRA","BTG","CASH", "CEF1","CEF2","NU", "MP", "STDER","OUTR"};
        ArrayAdapter<String> fontAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fontesDespesa);
        fontAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontDespEdt.setAdapter(fontAdapter);
    }

    private int getIndex(Spinner spinner, String myString) {
        int index = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void showDatePickerDialog() {
        // Obter a data atual
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Criar o DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                NewFinActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // Criar um LocalDateTime com a data selecionada
                        LocalDateTime selectedDateTime = LocalDateTime.of(selectedYear, selectedMonth + 1, selectedDay, 0, 0);
                        // Formatar a data selecionada
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE yyyy-MM-dd");
                        String formattedDate = selectedDateTime.format(formatter);
                        dataDespEdt.setText(formattedDate);
                    }
                },
                year, month, day);

        // Mostrar o DatePickerDialog
        datePickerDialog.show();
    }

    private void saveFin(String valorDesp, String tipoDesp, String fontDesp, String despDescr, String dataDesp) {
        // PASSA OS DADOS NOVOS/RECUPERADOS PARA SALVAR
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
            } else {
                Toast.makeText(this, "Permissão de escrita no armazenamento externo negada.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


