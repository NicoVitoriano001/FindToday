package com.gtappdevelopers.findtoday;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    void insert(FinModal model);

    @Update
    void update(FinModal model);

    @Delete
    void delete(FinModal model);

    @Query("DELETE FROM fin_table")
    void deleteallDesp();

    @Query("SELECT * FROM fin_table WHERE dataDesp LIKE '%2024%' ORDER BY dataDesp DESC, tipoDesp ASC")
    LiveData<List<FinModal>> getallDesp();

    /*
    @Query("SELECT * FROM fin_table ORDER BY dataDesp DESC, tipoDesp ASC")
    LiveData<List<FinModal>> getallDesp();
    */


    @Query("SELECT * FROM fin_table WHERE valorDesp LIKE '%' || :valorDesp || '%' " +
            "AND tipoDesp LIKE '%' || :tipoDesp || '%' " +
            "AND fontDesp LIKE '%' || :fontDesp || '%' " +
            "AND despDescr LIKE '%' || :despDescr || '%' " +
            "AND dataDesp LIKE '%' || :dataDesp || '%' ORDER BY dataDesp DESC")
    LiveData<List<FinModal>> buscaDesp(String valorDesp, String tipoDesp, String fontDesp, String despDescr, String dataDesp);

}
