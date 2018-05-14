package sample;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Resumen1 {

    private StringProperty nacionalidad;
    private FloatProperty promedio;

    public Resumen1 (String nacionaliad,  Float promedio){
        this.nacionalidad = new SimpleStringProperty(nacionaliad);
        this.promedio = new SimpleFloatProperty(promedio);
    }


    public Float getPromedio(){
        return promedio.get();
    }

    public String getNacionalidad() {
        return nacionalidad.get();
    }

}
