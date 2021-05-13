package com.dam.safebar.javabeans;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurante implements Parcelable {

    private String nombreRest;
    private String urlFoto;
    private String email;
    private String password;
    private String direccion;
    private String telefono;
    private int calificacion;
    private int precioMedio;
    private int aforo;
    private String descripcion;
    private String restUID;
    //private ArrayList<ReservaRest> listaReservas;

    public Restaurante() {
    }

//    public Restaurante(String nombreRest, String urlFoto, String correo, String password, String direccion, String telefono, int calificacion, int precioMedio, int aforo, String descripcion, ArrayList<ReservaRest> listaReservas) {
//        this.nombreRest = nombreRest;
//        this.urlFoto = urlFoto;
//        this.correo = correo;
//        this.password = password;
//        this.direccion = direccion;
//        this.telefono = telefono;
//        this.calificacion = calificacion;
//        this.precioMedio = precioMedio;
//        this.aforo = aforo;
//        this.descripcion = descripcion;
//        this.listaReservas = listaReservas;
//    }


    public Restaurante(String nombreRest, String email, String password, String direccion) {
        this.nombreRest = nombreRest;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
    }

    public Restaurante(String nombreRest, String urlFoto, String email, String password, String direccion, String telefono, int calificacion, int precioMedio, int aforo, String descripcion) {
        this.nombreRest = nombreRest;
        this.urlFoto = urlFoto;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.calificacion = calificacion;
        this.precioMedio = precioMedio;
        this.aforo = aforo;
        this.descripcion = descripcion;
    }

    public Restaurante(String nombreRest, String urlFoto, String email, String password, String direccion, String telefono, int precioMedio, int aforo, String descripcion) {
        this.nombreRest = nombreRest;
        this.urlFoto = urlFoto;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.precioMedio = precioMedio;
        this.aforo = aforo;
        this.descripcion = descripcion;
    }

    public Restaurante(String nombreRest, String urlFoto, String email, String password, String direccion, String telefono, int calificacion, int precioMedio, int aforo, String descripcion, String restUID) {
        this.nombreRest = nombreRest;
        this.urlFoto = urlFoto;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.calificacion = calificacion;
        this.precioMedio = precioMedio;
        this.aforo = aforo;
        this.descripcion = descripcion;
        this.restUID = restUID;
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


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getRestUID() {
        return restUID;
    }

    public void setRestUID(String restUID) {
        this.restUID = restUID;
    }

    //    public ArrayList<ReservaRest> getListaReservas() {
//        return listaReservas;
//    }
//
//    public void setListaReservas(ArrayList<ReservaRest> listaReservas) {
//        this.listaReservas = listaReservas;
//    }

    protected Restaurante(Parcel in) {
        nombreRest = in.readString();
        urlFoto = in.readString();
        email = in.readString();
        password = in.readString();
        direccion = in.readString();
        telefono = in.readString();
        password = in.readString();
        calificacion = in.readInt();
        precioMedio = in.readInt();
        aforo = in.readInt();
        descripcion = in.readString();
        restUID = in.readString();
        //in.readList(listaReservas, ReservaRest.class.getClassLoader());

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
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(direccion);
        dest.writeString(telefono);
        dest.writeInt(calificacion);
        dest.writeInt(precioMedio);
        dest.writeInt(aforo);
        dest.writeString(descripcion);
        dest.writeString(restUID);
        //dest.writeList(listaReservas);

    }
}

