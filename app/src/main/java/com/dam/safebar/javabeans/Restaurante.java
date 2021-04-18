package com.dam.safebar.javabeans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Restaurante implements Parcelable {

    private String nombreRest;
    private String urlFoto;
    private String correo;
    private String password;
    private String direccion;
    private int calificacion;
    private int precioMedio;
    private int aforo;
    private String descripcion;
    private ArrayList<ReservaRest> listaReservas;

    public Restaurante() {
    }

    public Restaurante(String nombreRest, String urlFoto, String correo, String password, String direccion, int calificacion, int precioMedio, int aforo, String descripcion, ArrayList<ReservaRest> listaReservas) {
        this.nombreRest = nombreRest;
        this.urlFoto = urlFoto;
        this.correo = correo;
        this.password = password;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.precioMedio = precioMedio;
        this.aforo = aforo;
        this.descripcion = descripcion;
        this.listaReservas = listaReservas;
    }

    public Restaurante(String nombreRest, String urlFoto, String correo, String password, String direccion, int calificacion, int precioMedio, int aforo, String descripcion) {
        this.nombreRest = nombreRest;
        this.urlFoto = urlFoto;
        this.correo = correo;
        this.password = password;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.precioMedio = precioMedio;
        this.aforo = aforo;
        this.descripcion = descripcion;
    }

    public String getNombreRest() {
        return nombreRest;
    }

    public void setNombreRest(String nombreRest) {
        this.nombreRest = nombreRest;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getPrecioMedio() {
        return precioMedio;
    }

    public void setPrecioMedio(int precioMedio) {
        this.precioMedio = precioMedio;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<ReservaRest> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(ArrayList<ReservaRest> listaReservas) {
        this.listaReservas = listaReservas;
    }

    protected Restaurante(Parcel in) {
        nombreRest = in.readString();
        urlFoto = in.readString();
        correo = in.readString();
        password = in.readString();
        direccion = in.readString();
        password = in.readString();
        calificacion = in.readInt();
        precioMedio = in.readInt();
        aforo = in.readInt();
        descripcion = in.readString();
        in.readList(listaReservas, ReservaRest.class.getClassLoader());

    }

    public static final Creator<Restaurante> CREATOR = new Creator<Restaurante>() {
        @Override
        public Restaurante createFromParcel(Parcel in) {
            return new Restaurante(in);
        }

        @Override
        public Restaurante[] newArray(int size) {
            return new Restaurante[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreRest);
        dest.writeString(urlFoto);
        dest.writeString(correo);
        dest.writeString(password);
        dest.writeString(direccion);
        dest.writeInt(calificacion);
        dest.writeInt(precioMedio);
        dest.writeInt(aforo);
        dest.writeString(descripcion);
        dest.writeList(listaReservas);

    }
}

