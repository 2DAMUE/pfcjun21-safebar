package com.dam.safebar.javabeans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Usuario implements Parcelable {

    private String urlFoto;
    private String nombre;
    private String email;
    private String password;
    private String direccion;
    //private ArrayList<ReservaUsu> listaReservas;

    public Usuario() {
    }

//    public Usuario(String nombre, String email, String password, String direccion, ArrayList<ReservaUsu> listaReservas) {
//        this.nombre = nombre;
//        this.email = email;
//        this.password = password;
//        this.direccion = direccion;
//        this.listaReservas = listaReservas;
//    }

    public Usuario(String nombre, String email, String password, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
    }

    public Usuario(String urlFoto, String nombre, String email, String password, String direccion) {
        this.urlFoto = urlFoto;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
    }

    //    public ArrayList<ReservaUsu> getListaReservas() {
//        return listaReservas;
//    }
//
//    public void setListaReservas(ArrayList<ReservaUsu> listaReservas) {
//        this.listaReservas = listaReservas;
//    }


    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    protected Usuario(Parcel in) {
        urlFoto = in.readString();
        nombre = in.readString();
        email = in.readString();
        password = in.readString();
        direccion = in.readString();
        //in.readList(listaReservas, ReservaUsu.class.getClassLoader());
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
        dest.writeString(urlFoto);
        dest.writeString(nombre);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(direccion);
       // dest.writeList(listaReservas);
    }
}
