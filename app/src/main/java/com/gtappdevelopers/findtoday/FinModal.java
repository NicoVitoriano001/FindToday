package com.gtappdevelopers.findtoday;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//below line is for setting table name.
@Entity(tableName = "fin_table")

public class FinModal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String valorDesp;
    private String tipoDesp;
    private String fontDesp;
    private String despDescr;
    private String dataDesp;

    //below line we are creating constructor class.
    //inside constructor class we are not passing our id because it is incrementing automatically
    //sequencia abaixo é exibido no main getdesall
    public FinModal(String valorDesp, String tipoDesp, String fontDesp,  String despDescr, String dataDesp) {
        this.valorDesp = valorDesp;
        this.tipoDesp = tipoDesp;
        this.fontDesp = fontDesp;
        this.despDescr = despDescr;
        this.dataDesp = dataDesp;
    }

    //on below line we are creating getter and setter methods.
    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }

    public String getValorDesp() {return valorDesp; }
    public void setValorDesp(String valorDesp) {
        this.valorDesp = valorDesp;
    }

    public String getTipoDesp() {
        return tipoDesp;
    }
    public void setTipoDesp(String tipoDesp) {
        this.tipoDesp = tipoDesp;
    }

    public String getFontDesp() {
        return fontDesp;
    }
    public void getFontDesp(String fontDesp) {
        this.fontDesp = fontDesp;
    }

    public String getDespDescr() { return despDescr; }
    public void setDespDescr(String despDescr) { this.despDescr = despDescr; }

    public String getDataDesp() {
        return dataDesp;
    }
    public void setDataDesp(String dataDesp) {
        this.dataDesp = dataDesp;
    }
}
