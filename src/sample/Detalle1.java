package sample;

import javafx.beans.property.*;

public class Detalle1 {

    private StringProperty departamento;
    private StringProperty nacionalidad;
    private FloatProperty promedio;

    public Detalle1 (String nacionaliad, String departamento, Float promedio){
        this.nacionalidad = new SimpleStringProperty(nacionaliad);
        this.promedio = new SimpleFloatProperty(promedio);
        this.departamento = new SimpleStringProperty(departamento);
    }

    public String getDepartamento() {
        return departamento.get();
    }

    public Float getPromedio(){
        return promedio.get();
    }

    public String getNacionalidad() {
        return nacionalidad.get();
    }

}
