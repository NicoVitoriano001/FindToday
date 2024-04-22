package com.gtappdevelopers.findtoday;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "fin_table")

public class FinModal implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String valorDesp;
    private String tipoDesp;
    private String fontDesp;
    private String despDescr;
    private String dataDesp;

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


    protected FinModal(Parcel in) {
        id = in.readInt();
        valorDesp = in.readString();
        tipoDesp = in.readString();
        fontDesp = in.readString();
        despDescr = in.readString();
        dataDesp = in.readString();
    }

    public static final Creator<FinModal> CREATOR = new Creator<FinModal>() {
        @Override
        public FinModal createFromParcel(Parcel in) {
            return new FinModal(in);
        }

        @Override
        public FinModal[] newArray(int size) {
            return new FinModal[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(valorDesp);
        dest.writeString(tipoDesp);
        dest.writeString(fontDesp);
        dest.writeString(despDescr);
        dest.writeString(dataDesp);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
