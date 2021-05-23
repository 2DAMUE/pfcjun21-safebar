package com.dam.safebar.listeners;

public interface CuentaListener {

    public void abrirConfiguracion();
    public void abrirAboutUs();
    public void abrirAyuda();
    public void abrirProtocoloCovid();

    public void llamar();
    public void mandarCorreo(String email);

    public void abrirEditPerf();
    public void salir();

}
