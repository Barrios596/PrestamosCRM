package sample;

import javafx.beans.property.*;

public class Resumen2 {

    private IntegerProperty edad;
    private IntegerProperty cantidad;

    public Resumen2 (Integer edad, Integer cantidad){
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.edad = new SimpleIntegerProperty(edad);
    }

    public Integer getEdad() {
        return edad.get();
    }

    public Integer getCantidad(){
        return cantidad.get();
    }


}
