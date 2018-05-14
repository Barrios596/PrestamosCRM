package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Resumen5 {

    private StringProperty nacionalidad;
    private IntegerProperty cantidad;

    public Resumen5 (String nacionaliad, Integer cantidad ){
        this.nacionalidad = new SimpleStringProperty(nacionaliad);
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }


    public Integer getCantidad(){
        return cantidad.get();
    }

    public String getNacionalidad() {
        return nacionalidad.get();
    }

}
