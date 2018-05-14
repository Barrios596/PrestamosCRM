package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Detalle4 {

    private IntegerProperty mes;
    private StringProperty nacionalidad;
    private IntegerProperty cantidad;
    private StringProperty departamento;

    public Detalle4 (String nacionaliad, Integer mes, Integer cantidad , String departamento){
        this.nacionalidad = new SimpleStringProperty(nacionaliad);
        this.departamento = new SimpleStringProperty(departamento);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.mes = new SimpleIntegerProperty(mes);
    }

    public Integer getMes() {
        return mes.get();
    }

    public String getDepartamento() {
        return departamento.get();
    }

    public Integer getCantidad(){
        return cantidad.get();
    }

    public String getNacionalidad() {
        return nacionalidad.get();
    }

}
