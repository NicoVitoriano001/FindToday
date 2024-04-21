package com.gtappdevelopers.findtoday;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Adding annotation to our Dao class
@androidx.room.Dao
public interface Dao {
    //below method is use to add data to database.
    @Insert
    void insert(FinModal model);

    //below method is use to update the data in our database.
    @Update
    void update(FinModal model);

    //below line is use to delete a specific course in our database.
    @Delete
    void delete(FinModal model);

    //on below line we are making query to delete all courses from our databse.
    @Query("DELETE FROM fin_table")
    void deleteallDesp();

    //beloe line is to read all the courses from our database.
    //in this we are ordering our courses in ascending order with our course name.
    @Query("SELECT * FROM fin_table ORDER BY dataDesp DESC, tipoDesp ASC")
    LiveData<List<FinModal>> getallDesp();

    @Query("SELECT * FROM fin_table WHERE valorDesp LIKE '%' || :valorDesp || '%' " +
            "AND tipoDesp LIKE '%' || :tipoDesp || '%' " +
            "AND fontDesp LIKE '%' || :fontDesp || '%' " +
            "AND despDescr LIKE '%' || :despDescr || '%' " +
            "AND dataDesp LIKE '%' || :dataDesp || '%'")
    LiveData<List<FinModal>> buscaDesp(String valorDesp, String tipoDesp, String fontDesp, String despDescr, String dataDesp);


}
