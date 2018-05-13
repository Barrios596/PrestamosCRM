package com.company;

import java.util.Date;

public class Tweet {
    String usuario;
    String texto;
    String fecha;
    String url;

    public Tweet(String usuario, String texto, String fecha, String url) {
        this.usuario = usuario;
        this.texto = texto;
        this.fecha = fecha;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
