package com.dam.safebar.javabeans;

import android.os.Parcel;
import android.os.Parcelable;

public class ReservaUsu implements Parcelable {

    private String nombreRest;
    private String fecha;
    private String hora;
    private int numPersonas;
    private  String codigo;


    public ReservaUsu() {
    }

    public ReservaUsu(String nombreRest, String fecha, String hora, int numPersonas) {
        this.nombreRest = nombreRest;
        this.fecha = fecha;
        this.hora = hora;
        this.numPersonas = numPersonas;
    }

    public ReservaUsu(String nombreRest, int numPersonas, String codigo) {
        this.nombreRest = nombreRest;
        this.numPersonas = numPersonas;
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    protected ReservaUsu(Parcel in) {
        nombreRest = in.readString();
        fecha = in.readString();
        hora = in.readString();
        numPersonas = in.readInt();
        codigo = in.readString();
    }

    public static final Creator<ReservaUsu> CREATOR = new Creator<ReservaUsu>() {
        @Override
        public ReservaUsu createFromParcel(Parcel in) {
            return new ReservaUsu(in);
        }

        @Override
        public ReservaUsu[] newArray(int size) {
            return new ReservaUsu[size];
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
        dest.writeString(codigo);
    }
}
