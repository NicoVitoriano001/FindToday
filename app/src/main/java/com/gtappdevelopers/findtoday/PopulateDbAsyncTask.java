package com.gtappdevelopers.findtoday;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
    private FinDatabase database;
    private Context context;

    PopulateDbAsyncTask(FinDatabase instance, Context context) {
        database = instance;
        this.context = context; // Atribuído o contexto recebido ao membro da classe
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // Ler o arquivo CSV e inserir os dados no banco de dados
            InputStream inputStream = context.getAssets().open("Sheet80.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            CSVReader reader = new CSVReader(inputStreamReader);

            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                FinModal finModal = new FinModal(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], nextRecord[4]);
                database.Dao().insert(finModal);
            }

            reader.close();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return null;
    }

}
