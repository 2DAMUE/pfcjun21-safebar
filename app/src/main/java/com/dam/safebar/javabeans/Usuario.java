package com.dam.safebar.javabeans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Usuario implements Parcelable {

    private String nombre;
    private String email;
    private String password;
    private String direccion;
    private ArrayList<ReservaUsu> listaReservas;

    public Usuario() {
    }

    public Usuario(String nombre, String email, String password, String direccion, ArrayList<ReservaUsu> listaReservas) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.listaReservas = listaReservas;
    }

    public Usuario(String nombre, String email, String password, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
    }

    public ArrayList<ReservaUsu> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(ArrayList<ReservaUsu> listaReservas) {
        this.listaReservas = listaReservas;
    }

    protected Usuario(Parcel in) {
        nombre = in.readString();
        email = in.readString();
        password = in.readString();
        direccion = in.readString();
        //TODO:
        in.readList(listaReservas, ReservaUsu.class.getClassLoader());
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(direccion);
        dest.writeList(listaReservas);
    }
}
