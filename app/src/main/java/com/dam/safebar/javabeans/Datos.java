package com.dam.safebar.javabeans;

import java.util.ArrayList;

public class Datos {
//V
    ArrayList<Usuario> listaUsuarios;
    ArrayList<Restaurante> listaRestaurantes;

    public Datos() {
        listaUsuarios = new ArrayList<Usuario>();

        listaRestaurantes = new ArrayList<Restaurante>();

    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public ArrayList<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }


}
