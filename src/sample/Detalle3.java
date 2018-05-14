package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Detalle3 {

    private StringProperty estadoCivil;
    private StringProperty nacionalidad;
    private IntegerProperty cantidad;

    public Detalle3 (String nacionaliad, String estadoCivil, Integer cantidad){
        this.nacionalidad = new SimpleStringProperty(nacionaliad);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.estadoCivil = new SimpleStringProperty(estadoCivil);
    }

    public String getEstadoCivil() {
        return estadoCivil.get();
    }

    public Integer getCantidad(){
        return cantidad.get();
    }

    public String getNacionalidad() {
        return nacionalidad.get();
    }
}
