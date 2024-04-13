package com.gtappdevelopers.findtoday;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import androidx.room.TypeConverters;

// Remova a importação do TypeConverters
@Database(entities = {FinModal.class}, version = 1)
@TypeConverters(Converters.class)

public abstract class FinDatabase extends RoomDatabase {
    private static FinDatabase instance;
    public abstract Dao dao(); // Renomeie Dao para dao

    public static synchronized FinDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            FinDatabase.class, "fin_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        PopulateDbAsyncTask(FinDatabase instance) {
            dao = instance.dao(); // Use dao() ao invés de Dao()
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Popule o banco de dados aqui, se necessário
            return null;
        }
    }
}
