package com.gtappdevelopers.findtoday;
import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

public class FinRepository {
    //below line is the create a variable for dao and list for all courses.
    private Dao dao;
    private LiveData<List<FinModal>> allDesp;

    //creating a constructor for our variables and passing the variables to it.
    public FinRepository(Application application) {
        FinDatabase database = FinDatabase.getInstance(application);
        dao = database.Dao();
        allDesp = dao.getallDesp();
    }

    //creating a method to insert the data to our database.
    public void insert(FinModal model) {
        new InsertFinAsyncTask(dao).execute(model);
    }

    //creating a method to update data in database.
    public void update(FinModal model) {
        new UpdateFinAsyncTask(dao).execute(model);
    }

    //creating a method to delete the data in our database.
    public void delete(FinModal model) {
        new DeleteFinAsyncTask(dao).execute(model);
    }

    //below is the method to delete all the courses.
    public void deleteallDesp() {
        new DeleteallDespAsyncTask(dao).execute();
    }

    //below method is to read all the courses.
    public LiveData<List<FinModal>> getallDesp() {
        return allDesp;
    }

    //we are creating a async task method to insert new course.
    private static class InsertFinAsyncTask extends AsyncTask<FinModal, Void, Void> {
        private Dao dao;

        private InsertFinAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(FinModal... model) {
            //below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    //we are creating a async task method to update our course.
    private static class UpdateFinAsyncTask extends AsyncTask<FinModal, Void, Void> {
        private Dao dao;

        private UpdateFinAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(FinModal... models) {
            //below line is use to update our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete course.
    private static class DeleteFinAsyncTask extends AsyncTask<FinModal, Void, Void> {
        private Dao dao;

        private DeleteFinAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(FinModal... models) {
            //below line is use to delete our course modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete all courses.
    private static class DeleteallDespAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        private DeleteallDespAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //on below line calling method to delete all courses.
            dao.deleteallDesp();
            return null;
        }
    }
}
