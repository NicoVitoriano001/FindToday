package com.gtappdevelopers.findtoday;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DatabaseBackup {

    public static boolean backupDatabase(Context context) {
        File src = context.getDatabasePath("fin_database.db");
        File dst = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "fin_database_.db");

        try {
            FileInputStream inputStream = new FileInputStream(src);
            FileOutputStream outputStream = new FileOutputStream(dst);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            //Log.d("DatabaseBackup", "Backuup do banco de dados concluído com sucesso.");

            // Após fazer o backup, forçar um checkpoint
            SQLiteDatabase db = SQLiteDatabase.openDatabase(src.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
            db.rawQuery("PRAGMA wal_checkpoint(FULL)", null).close();
            db.close();
            //Log.d("DatabaseBackup", "PRAGMA do banco de dados concluído com sucesso.");

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            //Log.e("DatabaseBackup", "Erro ao realizar o backup do banco de dados: " + e.getMessage());
            return false;
        }
    }
}
