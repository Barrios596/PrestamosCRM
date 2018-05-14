package sample;

import javafx.beans.property.*;

public class Detalle2 {

    private StringProperty edad;
    private StringProperty nacionalidad;
    private StringProperty cantidad;

    public Detalle2 (String nacionaliad, Integer edad, Integer cantidad){
        this.nacionalidad = new SimpleStringProperty(nacionaliad);
        this.cantidad = new SimpleStringProperty(String.valueOf(cantidad));
        this.edad = new SimpleStringProperty(String.valueOf(edad));
    }

    public String getEdad() {
        return edad.get();
    }

    public String getCantidad(){
        return cantidad.get();
    }

    public String getNacionalidad() {
        return nacionalidad.get();
    }

}
