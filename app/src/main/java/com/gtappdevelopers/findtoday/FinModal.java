package com.gtappdevelopers.findtoday;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//below line is for setting table name.
@Entity(tableName = "fin_table")//nome tabela

public class FinModal {
    //below line is to auto increment id for each course.
    @PrimaryKey(autoGenerate = true)
    //variable for our id.
    private int id;
    //below line is a variable for course name.
    private float valorDesp;
    //below line is use for course description.
    private String tipoDesp;
    //below line is use for course description.
    private String fontDesp;
    //below line is use for course description.
    private String despDescr;
    //below line is use for course duration.
    private String dataDesp;

    //below line we are creating constructor class.
    //inside constructor class we are not passing our id because it is incrementing automatically
    //sequencia abaixo é exibido no main getdesall
    public FinModal(float valorDesp, String tipoDesp, String fontDesp,  String despDescr, String dataDesp) {
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

    public float getValorDesp() {return valorDesp; }
    public void setValorDesp(float valorDesp) {
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
