package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Detalle5 {

    private StringProperty empleado;
    private IntegerProperty cantidad;
    private StringProperty departamento;

    public Detalle5 (Integer cantidad , String departamento, String empleado){
        this.departamento = new SimpleStringProperty(departamento);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.empleado = new SimpleStringProperty(empleado);
    }

    public String getEmpleado() {
        return empleado.get();
    }

    public String getDepartamento() {
        return departamento.get();
    }

    public Integer getCantidad(){
        return cantidad.get();
    }


}
