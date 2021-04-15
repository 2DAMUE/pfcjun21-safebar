package com.dam.safebar.javabeans;

import android.os.Parcel;
import android.os.Parcelable;

public class Reserva implements Parcelable {

    private String nombreRest;
    private String fecha;
    private String hora;
    private int numPersonas;

    public Reserva() {
    }

    public Reserva(String nombreRest, String fecha, String hora, int numPersonas) {
        this.nombreRest = nombreRest;
        this.fecha = fecha;
        this.hora = hora;
        this.numPersonas = numPersonas;
    }

    public String getNombreRest() {
        return nombreRest;
    }

    public void setNombreRest(String nombreRest) {
        this.nombreRest = nombreRest;
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

    protected Reserva(Parcel in) {
        nombreRest = in.readString();
        fecha = in.readString();
        hora = in.readString();
        numPersonas = in.readInt();
    }

    public static final Creator<Reserva> CREATOR = new Creator<Reserva>() {
        @Override
        public Reserva createFromParcel(Parcel in) {
            return new Reserva(in);
        }

        @Override
        public Reserva[] newArray(int size) {
            return new Reserva[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreRest);
        dest.writeString(fecha);
        dest.writeString(hora);
        dest.writeInt(numPersonas);
    }
}
