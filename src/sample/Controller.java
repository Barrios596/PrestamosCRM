package sample;

import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML private ImageView flecha1;
    @FXML private ImageView flecha2;
    @FXML private ImageView flecha3;
    @FXML private ImageView flecha4;

    @FXML private ImageView imgAgregar;
    @FXML private ImageView imgSearch;
    @FXML private ImageView imgGraph;
    @FXML private ImageView imgTwitter;
    @FXML private ImageView imgSalir;

    @FXML private AnchorPane busquedaPanel;
    @FXML private AnchorPane agregarPanel;
    @FXML private AnchorPane reportesPanel;
    @FXML private AnchorPane twitterPanel;

    @FXML private JFXComboBox<String> comboNacionalidad;
    @FXML private JFXComboBox<String> comboEstadoCivil;
    @FXML private JFXComboBox<String> comboEncargado;

    ObservableList<String> comboEstadoCivilContent = FXCollections.observableArrayList("Soltero","Casado","Divorciado","Viudo");

    //La imagen para salir cierra el proceso
    public void onExitButtonClicked(MouseEvent event){
        imgSalir.setEffect(new Glow(0.85));
        Platform.exit();
        System.exit(0);
    }

    public void onSearchButtonClicked(MouseEvent event){
        busquedaPanel.setVisible(true);
        agregarPanel.setVisible(false);
        reportesPanel.setVisible(false);
        twitterPanel.setVisible(false);
        flecha1.setVisible(false);
        flecha2.setVisible(true);
        flecha3.setVisible(false);
        flecha4.setVisible(false);
        //Para sacar brillo en la imagen que se seleccionó
        imgSearch.setEffect(new Glow(0.85));
        imgGraph.setEffect(new Glow(0));
        imgAgregar.setEffect(new Glow(0));
        imgTwitter.setEffect(new Glow(0));
    }

    public void onUserButtonClicked(MouseEvent event){
        busquedaPanel.setVisible(false);
        agregarPanel.setVisible(true);
        reportesPanel.setVisible(false);
        twitterPanel.setVisible(false);
        flecha1.setVisible(true);
        flecha2.setVisible(false);
        flecha3.setVisible(false);
        flecha4.setVisible(false);
        imgSearch.setEffect(new Glow(0));
        imgGraph.setEffect(new Glow(0));
        imgAgregar.setEffect(new Glow(0.85));
        imgTwitter.setEffect(new Glow(0));
    }

    public void onGraphButtonClicked(MouseEvent event){
        busquedaPanel.setVisible(false);
        agregarPanel.setVisible(false);
        reportesPanel.setVisible(true);
        twitterPanel.setVisible(false);
        flecha1.setVisible(false);
        flecha2.setVisible(false);
        flecha3.setVisible(true);
        flecha4.setVisible(false);

        imgSearch.setEffect(new Glow(0));
        imgGraph.setEffect(new Glow(0.85));
        imgAgregar.setEffect(new Glow(0));
        imgTwitter.setEffect(new Glow(0));
    }

    public void onTwitterButtonClicked(MouseEvent event){
        busquedaPanel.setVisible(false);
        agregarPanel.setVisible(false);
        reportesPanel.setVisible(false);
        twitterPanel.setVisible(true);
        flecha1.setVisible(false);
        flecha2.setVisible(false);
        flecha3.setVisible(false);
        flecha4.setVisible(true);

        imgSearch.setEffect(new Glow(0));
        imgGraph.setEffect(new Glow(0));
        imgAgregar.setEffect(new Glow(0));
        imgTwitter.setEffect(new Glow(0.85));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboEstadoCivil.setItems(comboEstadoCivilContent);
        imgAgregar.setEffect(new Glow(0.85));
    }
}
