package com.dam.safebar.listeners;

public interface CuentaListener {

    void abrirConfiguracion();
    void abrirAboutUs();
    void abrirAyuda();
    void abrirProtocoloCovid();

    void llamar();
    void mandarCorreo(String email);

    void abrirEditPerf();
    void salir();

}
