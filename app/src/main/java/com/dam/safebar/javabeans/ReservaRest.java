package com.dam.safebar.javabeans;

import android.os.Parcel;
import android.os.Parcelable;

public class ReservaRest implements Parcelable {

    private String userUID;
    private String fecha;
    private String hora;
    private int numPersonas;

    public ReservaRest() {
    }

    public ReservaRest(String userUID, String fecha, String hora, int numPersonas) {
        this.userUID = userUID;
        this.fecha = fecha;
        this.hora = hora;
        this.numPersonas = numPersonas;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    protected ReservaRest(Parcel in) {
        userUID = in.readString();
        fecha = in.readString();
        hora = in.readString();
        numPersonas = in.readInt();
    }

    public static final Creator<ReservaRest> CREATOR = new Creator<ReservaRest>() {
        @Override
        public ReservaRest createFromParcel(Parcel in) {
            return new ReservaRest(in);
        }

        @Override
        public ReservaRest[] newArray(int size) {
            return new ReservaRest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userUID);
        dest.writeString(fecha);
        dest.writeString(hora);
        dest.writeInt(numPersonas);
    }
}
