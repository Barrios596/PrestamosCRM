package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Resumen3 {

    private IntegerProperty cantidad;

    public Resumen3 (Integer cantidad){
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }


    public Integer getCantidad(){
        return cantidad.get();
    }

}
