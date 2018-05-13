package sample;

import javafx.beans.property.*;

public class Fila1 {

    private StringProperty tipoDocumento;
    private StringProperty numDocumento;
    private StringProperty nombre;
    private StringProperty apellido;
    private StringProperty direccion;
    private StringProperty nacionalidad;
    private StringProperty estadoCivil;
    private StringProperty encargado;

    public Fila1 (String tipoDocumento, String numDocumento, String nombre, String apellido, String direccion, String nacionaliad, String estadoCivil, String encargado){
        this.tipoDocumento = new SimpleStringProperty(tipoDocumento);
        this.numDocumento = new SimpleStringProperty(numDocumento);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.direccion = new SimpleStringProperty(direccion);
        this.nacionalidad = new SimpleStringProperty(nacionaliad);
        this.tipoDocumento = new SimpleStringProperty(tipoDocumento);
        this.estadoCivil = new SimpleStringProperty(estadoCivil);
        this.encargado = new SimpleStringProperty(encargado);
    }

    public String getTipoDocumento() {
        return tipoDocumento.get();
    }

    public String getNumDocumento() {
        return numDocumento.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getApellido() {
        return apellido.get();
    }

    public String getDireccion() {
        return direccion.get();
    }

    public String getEstadoCivil() {
        return estadoCivil.get();
    }

    public String getEncargado() {
        return encargado.get();
    }

    public String getNacionalidad() {
        return nacionalidad.get();
    }
}
