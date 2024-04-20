package com.gtappdevelopers.findtoday;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DatabaseBackup {

    public static boolean backupDatabase(Context context) {
        File src = context.getDatabasePath("fin_database.db");
        File dst = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "fin_database_.db");
        //File dst = new File(context.getFilesDir(), "fin_database_backup.db");

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
            Log.d("DatabaseBackup", "Backup do banco de dados concluído com sucesso.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("DatabaseBackup", "Erro ao realizar o backup do banco de dados: " + e.getMessage());
            return false;
        }
    }
}
