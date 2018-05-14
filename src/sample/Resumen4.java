package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
public class Resumen4 {

    private IntegerProperty mes;
    private IntegerProperty cantidad;

    public Resumen4 (Integer mes, Integer cantidad ){
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.mes = new SimpleIntegerProperty(mes);
    }

    public Integer getMes() {
        return mes.get();
    }

    public Integer getCantidad(){
        return cantidad.get();
    }

}
