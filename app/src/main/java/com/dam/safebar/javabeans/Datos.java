package com.dam.safebar.javabeans;

import java.util.ArrayList;

public class Datos {

    ArrayList<Usuario> listaUsuarios;
    ArrayList<Restaurante> listaRestaurantes;

    public Datos() {
        listaUsuarios = new ArrayList<Usuario>();

        // TODO: Rellenar listaUsuarios

        listaRestaurantes = new ArrayList<Restaurante>();

        // TODO: REllenar listaRestaurantes

    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public ArrayList<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }


}
