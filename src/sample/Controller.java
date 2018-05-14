package sample;

import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML private JFXButton editarUsuarioButton;
    @FXML private JFXButton eliminarUsuarioButton;

    @FXML private ImageView flecha1;
    @FXML private ImageView flecha2;
    @FXML private ImageView flecha3;
    @FXML private ImageView flecha4;

    @FXML private BarChart chart1;
    @FXML private BarChart chart2;
    @FXML private BarChart chart3;
    @FXML private BarChart chart4;
    @FXML private BarChart chart5;

    @FXML private ImageView imgAgregar;
    @FXML private ImageView imgSearch;
    @FXML private ImageView imgGraph;
    @FXML private ImageView imgTwitter;
    @FXML private ImageView imgSalir;
    @FXML private ImageView avatarImg;
    @FXML private ImageView avatarImg1;
    @FXML private ImageView avatarSeleccionado;

    @FXML private AnchorPane busquedaPanel;
    @FXML private AnchorPane agregarPanel;
    @FXML private AnchorPane reportesPanel;
    @FXML private AnchorPane twitterPanel;
    @FXML private AnchorPane modificarPanel;

    @FXML private JFXComboBox<String> comboNacionalidad;
    @FXML private JFXComboBox<String> comboEstadoCivil;
    @FXML private JFXComboBox<String> comboEncargado;

    @FXML private JFXTextField nombreText;
    @FXML private JFXTextField apellidoText;
    @FXML private JFXTextField emailText;
    @FXML private JFXTextField twitterText;
    @FXML private JFXTextField telefonoText;
    @FXML private JFXTextField documentoText;
    @FXML private JFXTextField montoText;
    @FXML private JFXTextField pathText;
    @FXML private JFXTextField telefonoBusqueda;
    @FXML private JFXTextField documentoBusqueda;
    @FXML private JFXTextField nombreBusqueda;
    @FXML private JFXTextField apellidoBusqueda;
    @FXML private JFXTextArea direccionText;

    @FXML private  JFXTextField nombreEdit;
    @FXML private  JFXTextField apellidoEdit;
    @FXML private  JFXTextField pathEdit;
    @FXML private  JFXTextArea departamentoEdit;
    @FXML private  JFXComboBox nacionalidadEdit;
    @FXML private  JFXComboBox estadoCivilEdit;
    @FXML private  JFXComboBox encargadoEdit;



    @FXML private JFXRadioButton dpiRadio;
    @FXML private JFXRadioButton pasaporteRadio;
    @FXML private JFXRadioButton licenciaRadio;
    @FXML private JFXRadioButton dpiRadio1;
    @FXML private JFXRadioButton pasaporteRadio1;
    @FXML private JFXRadioButton licenciaRadio1;

    @FXML private JFXDatePicker fechaPicker;

    @FXML private TableView<Fila1> tablaResultado = new TableView();
    @FXML private TableView<Detalle1> detalle1 = new TableView();
    @FXML private TableView<Resumen1> resumen1 = new TableView();
    @FXML private TableView<Detalle2> detalle2 = new TableView();
    @FXML private TableView<Resumen2> resumen2 = new TableView();
    @FXML private TableView<Detalle3> detalle3 = new TableView();
    @FXML private TableView<Resumen3> resumen3 = new TableView();
    @FXML private TableView<Detalle4> detalle4 = new TableView();
    @FXML private TableView<Resumen4> resumen4 = new TableView();
    @FXML private TableView<Detalle5> detalle5 = new TableView();
    @FXML private TableView<Resumen5> resumen5 = new TableView();


    @FXML private TableColumn<Fila1,String> colTipoDoc;
    @FXML private TableColumn<Fila1,String> colNumDoc;
    @FXML private TableColumn<Fila1,String> colNombre;
    @FXML private TableColumn<Fila1,String> colApellido;
    @FXML private TableColumn<Fila1,String> colDireccion;
    @FXML private TableColumn<Fila1,String> colNacionalidad;
    @FXML private TableColumn<Fila1,String> colEstadoC;
    @FXML private TableColumn<Fila1,String> colEncargado;

    @FXML private TableColumn<Detalle1,String> d1Nacionalidad;
    @FXML private TableColumn<Detalle1,String> d1Departamento;
    @FXML private TableColumn<Detalle1,Float> d1Promedio;

    @FXML private TableColumn<Resumen1,Float> r1Promedio;
    @FXML private TableColumn<Resumen1,String> r1Nacionalidad;

    @FXML private TableColumn<Detalle2,String> d2Nacionalidad;
    @FXML private TableColumn<Detalle2,String> d2Edad;
    @FXML private TableColumn<Detalle2,String> d2Cantidad;

    @FXML private TableColumn<Resumen2,Integer> r2Edad;
    @FXML private TableColumn<Resumen2,Integer> r2Cantidad;

    @FXML private TableColumn<Detalle3,String> d3Nacionalidad;
    @FXML private TableColumn<Detalle3,String> d3EstadoCivil;
    @FXML private TableColumn<Detalle3,Integer> d3CantidadPrestamos;

    @FXML private TableColumn<Resumen3,Integer> r3Cantidad;

    @FXML private TableColumn<Detalle4,String> d4Nacionalidad;
    @FXML private TableColumn<Detalle4,String> d4Departamento;
    @FXML private TableColumn<Detalle4,Integer> d4Mes;
    @FXML private TableColumn<Detalle4,Integer> d4Cantidad;

    @FXML private TableColumn<Resumen4,Integer> r4Mes;
    @FXML private TableColumn<Resumen4,Integer> r4Cantidad;

    @FXML private TableColumn<Detalle5,String> d5Departamento;
    @FXML private TableColumn<Detalle5,String> d5Nombre;
    @FXML private TableColumn<Detalle5,Integer> d5Cantidad;

    @FXML private TableColumn<Resumen5,String> r5Nacionalidad;
    @FXML private TableColumn<Resumen5,Integer> r5Cantidad;

    @FXML private Label nombreSeleccionado;
    @FXML private Label idSeleccionado;
    @FXML private Label documentoEdit;
    @FXML private Label labelConteo;

    @FXML private JFXListView<Label> tweetList;

    private File avatar;

    private Fila1 filaSeleccionada;

    ObservableList<String> comboEncargadoContent = FXCollections.observableArrayList("Rodrigo Barrios","Joice Miranda","José Ramírez");
    ObservableList<String> comboEstadoCivilContent = FXCollections.observableArrayList("Soltero","Casado","Divorciado","Viudo");
    ObservableList<String> comboNacionalidadContent = FXCollections.observableArrayList("Afganistán"
            ,"Albania"
            ,"Alemania"
            ,"Andorra"
            ,"Angola"
            ,"Antigua y Barbuda"
            ,"Arabia Saudita"
            ,"Argelia"
            ,"Argentina"
            ,"Armenia"
            ,"Australia"
            ,"Austria"
            ,"Azerbaiyán"
            ,"Bahamas"
            ,"Bangladés"
            ,"Barbados"
            ,"Baréin"
            ,"Bélgica"
            ,"Belice"
            ,"Benín"
            ,"Bielorrusia"
            ,"Birmania"
            ,"Bolivia"
            ,"Bosnia y Herzegovina"
            ,"Botsuana"
            ,"Brasil"
            ,"Brunéi"
            ,"Bulgaria"
            ,"Burkina Faso"
            ,"Burundi"
            ,"Bután"
            ,"Cabo Verde"
            ,"Camboya"
            ,"Camerún"
            ,"Canadá"
            ,"Catar"
            ,"Chad"
            ,"Chile"
            ,"China"
            ,"Chipre"
            ,"Ciudad del Vaticano"
            ,"Colombia"
            ,"Comoras"
            ,"Corea del Norte"
            ,"Corea del Sur"
            ,"Costa de Marfil"
            ,"Costa Rica"
            ,"Croacia"
            ,"Cuba"
            ,"Dinamarca"
            ,"Dominica"
            ,"Ecuador"
            ,"Egipto"
            ,"El Salvador"
            ,"Emiratos Árabes Unidos"
            ,"Eritrea"
            ,"Eslovaquia"
            ,"Eslovenia"
            ,"España"
            ,"Estados Unidos"
            ,"Estonia"
            ,"Etiopía"
            ,"Filipinas"
            ,"Finlandia"
            ,"Fiyi"
            ,"Francia"
            ,"Gabón"
            ,"Gambia"
            ,"Georgia"
            ,"Ghana"
            ,"Granada"
            ,"Grecia"
            ,"Guatemala"
            ,"Guyana"
            ,"Guinea"
            ,"Guinea ecuatorial"
            ,"Guinea-Bisáu"
            ,"Haití"
            ,"Honduras"
            ,"Hungría"
            ,"India"
            ,"Indonesia"
            ,"Irak"
            ,"Irán"
            ,"Irlanda"
            ,"Islandia"
            ,"Islas Marshall"
            ,"Islas Salomón"
            ,"Israel"
            ,"Italia"
            ,"Jamaica"
            ,"Japón"
            ,"Jordania"
            ,"Kazajistán"
            ,"Kenia"
            ,"Kirguistán"
            ,"Kiribati"
            ,"Kuwait"
            ,"Laos"
            ,"Lesoto"
            ,"Letonia"
            ,"Líbano"
            ,"Liberia"
            ,"Libia"
            ,"Liechtenstein"
            ,"Lituania"
            ,"Luxemburgo"
            ,"Madagascar"
            ,"Malasia"
            ,"Malaui"
            ,"Maldivas"
            ,"Malí"
            ,"Malta"
            ,"Marruecos"
            ,"Mauricio"
            ,"Mauritania"
            ,"México"
            ,"Micronesia"
            ,"Moldavia"
            ,"Mónaco"
            ,"Mongolia"
            ,"Montenegro"
            ,"Mozambique"
            ,"Namibia"
            ,"Nauru"
            ,"Nepal"
            ,"Nicaragua"
            ,"Níger"
            ,"Nigeria"
            ,"Noruega"
            ,"Nueva Zelanda"
            ,"Omán"
            ,"Países Bajos"
            ,"Pakistán"
            ,"Palaos"
            ,"Panamá"
            ,"Papúa Nueva Guinea"
            ,"Paraguay"
            ,"Perú"
            ,"Polonia"
            ,"Portugal"
            ,"Reino Unido"
            ,"República Centroafricana"
            ,"República Checa"
            ,"República de Macedonia"
            ,"República del Congo"
            ,"República Democrática del Congo"
            ,"República Dominicana"
            ,"República Sudafricana"
            ,"Ruanda"
            ,"Rumanía"
            ,"Rusia"
            ,"Samoa"
            ,"San Cristóbal y Nieves"
            ,"San Marino"
            ,"San Vicente y las Granadinas"
            ,"Santa Lucía"
            ,"Santo Tomé y Príncipe"
            ,"Senegal"
            ,"Serbia"
            ,"Seychelles"
            ,"Sierra Leona"
            ,"Singapur"
            ,"Siria"
            ,"Somalia"
            ,"Sri Lanka"
            ,"Suazilandia"
            ,"Sudán"
            ,"Sudán del Sur"
            ,"Suecia"
            ,"Suiza"
            ,"Surinam"
            ,"Tailandia"
            ,"Tanzania"
            ,"Tayikistán"
            ,"Timor Oriental"
            ,"Togo"
            ,"Tonga"
            ,"Trinidad y Tobago"
            ,"Túnez"
            ,"Turkmenistán"
            ,"Turquía"
            ,"Tuvalu"
            ,"Ucrania"
            ,"Uganda"
            ,"Uruguay"
            ,"Uzbekistán"
            ,"Vanuatu"
            ,"Venezuela"
            ,"Vietnam"
            ,"Yemen"
            ,"Yibuti"
            ,"Zambia"
            ,"Zimbabue");

    public void onEliminarUsuarioClicked(MouseEvent event){
        Query query = new Query();
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmar decisión");
        alerta.setHeaderText(null);
        alerta.setContentText("Seleccionar 'OK' para eliminar al cliente "+filaSeleccionada.getNombre()+" "+filaSeleccionada.getApellido()+" de la base de datos.");
        Optional<ButtonType> result = alerta.showAndWait();
        if (result.get() == ButtonType.OK){
            query.eliminarQuery(filaSeleccionada.getNumDocumento());
            reiniciarTable();
        }

    }

    public void onActualizarUsuarioClicked(MouseEvent event){
        String[] valores = new String[7];
        Query query = new Query();
        boolean todobien = true;
        String warn = "";
        if (nombreEdit.getText().trim().equals("")){
            todobien = false;
            warn = warn+"Nombre, ";
        }
        if (apellidoEdit.getText().trim().equals("")){
            todobien = false;
            warn = warn+"Apellido, ";
        }
        if (departamentoEdit.getText().trim().equals("")){
            todobien = false;
            warn = warn+"Estado/Departamento, ";
        }
        if(todobien) {
            if (pasaporteRadio1.isSelected()){
                valores[0]="2";
            }
            else if(licenciaRadio1.isSelected()){
                valores[0]="3";
            }
            else {
                valores[0]="1";
            }
            valores[1] = nombreEdit.getText();
            valores[2] = apellidoEdit.getText();
            valores[3] = departamentoEdit.getText();
            for (int i = 0; i <comboNacionalidadContent.size(); i++){
                if (comboNacionalidadContent.get(i).equals(nacionalidadEdit.getValue().toString())){
                    valores[4]=String.valueOf(i+1);
                }
            }
            for (int i = 0; i <comboEstadoCivilContent.size(); i++){
                if (comboEstadoCivilContent.get(i).equals(estadoCivilEdit.getValue().toString())){
                    valores[5]=String.valueOf(i+1);
                }
            }
            for (int i = 0; i <comboEncargadoContent.size(); i++){
                if (comboEncargadoContent.get(i).equals(encargadoEdit.getValue().toString())){
                    valores[6]=String.valueOf(i+1);
                }
            }

            query.updateQuery(valores,filaSeleccionada.getNumDocumento());

            busquedaPanel.setVisible(true);
            agregarPanel.setVisible(false);
            reportesPanel.setVisible(false);
            twitterPanel.setVisible(false);
            modificarPanel.setVisible(false);
            reiniciarTable();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Éxito");
            alerta.setHeaderText(null);
            alerta.setContentText("El cliente "+valores[1]+" "+valores[2]+" fue modificado correctamente.");
            alerta.showAndWait();

        }
        else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Hace falta llenar campos:");
            alerta.setHeaderText(null);
            alerta.setContentText(warn.substring(0, warn.length() - 2)+".");
            alerta.showAndWait();
        }
    }

    public void onEditarUsuarioClicked(MouseEvent event){


        modificarPanel.setVisible(true);
        busquedaPanel.setVisible(false);
        agregarPanel.setVisible(false);
        reportesPanel.setVisible(false);
        twitterPanel.setVisible(false);

        nombreEdit.setText(filaSeleccionada.getNombre());
        apellidoEdit.setText(filaSeleccionada.getApellido());
        documentoEdit.setText(filaSeleccionada.getNumDocumento());
        if (filaSeleccionada.getTipoDocumento().equals("Pasaporte")){
            pasaporteRadio1.setSelected(true);
        }
        else if(filaSeleccionada.getTipoDocumento().equals("DPI")){
            dpiRadio1.setSelected(true);
        }
        else if(filaSeleccionada.getTipoDocumento().equals("Licencia")){
            licenciaRadio1.setSelected(true);
        }
        for (int i =0; i<comboNacionalidadContent.size();i++){
            if(filaSeleccionada.getNacionalidad().equals(comboNacionalidadContent.get(i))){
                nacionalidadEdit.getSelectionModel().select(i);
                break;
            }
        }
        for (int i =0; i<comboEstadoCivilContent.size();i++){
            if(filaSeleccionada.getEstadoCivil().equals(comboEstadoCivilContent.get(i))){
                estadoCivilEdit.getSelectionModel().select(i);
                break;
            }
        }
        for (int i =0; i<comboEncargadoContent.size();i++){
            if(filaSeleccionada.getEncargado().equals(comboEncargadoContent.get(i))){
                encargadoEdit.getSelectionModel().select(i);
                break;
            }
        }
        departamentoEdit.setText(filaSeleccionada.getDireccion());

    }

    //La imagen para salir cierra el proceso
    public void onExitButtonClicked(MouseEvent event){
        imgSalir.setEffect(new Glow(0.85));
        Platform.exit();
        System.exit(0);
    }
    //Cuando se selecciona el boton de examinar
    public void onExaminarButtonClicked(MouseEvent event){
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(imageFilter);
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile!=null){
            pathText.setText(selectedFile.toString());
            avatar=selectedFile;
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                Image image = SwingFXUtils.toFXImage(bufferedImage,null);
                avatarImg.setImage(image);
                avatarImg1.setImage(image);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor escoja un archivo válido.");
            alerta.showAndWait();
        }
    }

    //Cuando se quiere hacer una búsqueda
    public void onBusquedaButtonClicked(MouseEvent event){

        ArrayList<String> conditions = new ArrayList();
        ArrayList<String> values = new ArrayList<>();
        //Se obtienen los valores de los textfields
        String nombre = nombreBusqueda.getText();
        String apellido = apellidoBusqueda.getText();
        String telefono = telefonoBusqueda.getText();
        String documento = documentoBusqueda.getText();
        //Se verifica qué datos se ingresaron
        if (!nombre.trim().equals("")){
            conditions.add("nombre");
            values.add(nombre);
        }
        if (!apellido.trim().equals("")){
            conditions.add("apellido");
            values.add(apellido);
        }
        if (!telefono.trim().equals("")){
            conditions.add("telefono");
            values.add(telefono);
        }
        if (!documento.trim().equals("")){
            conditions.add("documento");
            values.add(documento);
        }

        String[] condiciones =  new String[conditions.size()];
        String[] valores = new String[conditions.size()];
        //El método recibe arreglos, por lo que se convierten los arraylists a esto
        for (int i = 0; i<conditions.size();i++){
            condiciones[i] = conditions.get(i);
            valores[i] = values.get(i);
        }
        Query query = new Query();
        //se llama al metodo generalQuery que retorna una lista observable para la tabla
        ObservableList<Fila1> lista = FXCollections.observableArrayList();
        lista = query.generalQuery(condiciones,valores);
        labelConteo.setText(String.valueOf(lista.size()));
        tablaResultado.setItems(lista);
        //Se enlazan las columnas a la tabla
        colTipoDoc.setCellValueFactory(new PropertyValueFactory<Fila1,String>("tipoDocumento"));
        colNumDoc.setCellValueFactory(new PropertyValueFactory<Fila1,String>("numDocumento"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Fila1,String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Fila1,String>("apellido"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Fila1,String>("direccion"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<Fila1,String>("nacionalidad"));
        colEstadoC.setCellValueFactory(new PropertyValueFactory<Fila1,String>("estadoCivil"));
        colEncargado.setCellValueFactory(new PropertyValueFactory<Fila1,String>("encargado"));

    }

    public void onSearchButtonClicked(MouseEvent event){
        busquedaPanel.setVisible(true);
        agregarPanel.setVisible(false);
        reportesPanel.setVisible(false);
        twitterPanel.setVisible(false);
        modificarPanel.setVisible(false);
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
        modificarPanel.setVisible(false);
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
        modificarPanel.setVisible(false);
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
        modificarPanel.setVisible(false);
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

    public void onCrearUsuarioClick(MouseEvent event){
        int documento = 1;
        String nombre ="";
        String apellido="";
        String telefono="";
        String twitter="";
        String correo="";
        String numDocumento="";
        String direccion="";
        Date fechaNacimiento= new Date();
        int idEstadoC = 1;
        double montoMaximo = 0.0;
        String usernameTwitter="";
        String idEmpleado = "1";
        Integer idNacionalidad=1;
        String idDocumento = "1";

        if(dpiRadio.isSelected()){
            documento=1;
        }
        else if(licenciaRadio.isSelected()){
            documento=2;
        }
        else {
            documento=3;
        }
        if (!validateFields()){
            nombre=nombreText.getText();
            apellido=apellidoText.getText();
            correo=emailText.getText();
            telefono=telefonoText.getText();
            twitter=twitterText.getText();
            numDocumento=documentoText.getText();
            direccion=direccionText.getText();

            fechaNacimiento = Date.from(Instant.from(fechaPicker.getValue().atStartOfDay(ZoneId.systemDefault())));
            System.out.println(fechaNacimiento);
            String estado = comboEstadoCivil.getValue().toString();
            for(int i = 0; i<comboEstadoCivilContent.size();i++){
                if (estado.equals(comboEstadoCivilContent.get(i))){
                    idEstadoC = i+1;
                    break;
                }
            }
            String nacionalidad = comboNacionalidad.getValue().toString();
            for(int i = 0; i<comboNacionalidadContent.size();i++){
                if (nacionalidad.equals(comboNacionalidadContent.get(i))){
                    idNacionalidad = i+1;
                    break;
                }
            }
            String encargado = comboEncargado.getValue().toString();
            for (int i = 0 ; i<comboEncargadoContent.size();i++){
                if (comboEncargadoContent.get(i).equals(encargado)){
                    idEmpleado= String.valueOf(i+1);
                    break;
                }
            }
            montoMaximo=Float.parseFloat(montoText.getText());

            Cliente cliente = new Cliente();
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setCorreo(correo);
            if(twitter.trim().equals("")){
                twitter=null;
            }
            //Se crea el path para la nueva imagen
            File copia = new File("src\\sample\\img\\"+numDocumento+".jpg");
            try {
                //se copia la imagen a la nueva dirección
                Files.copy(avatar.toPath(),copia.toPath());
            } catch (IOException e){
                e.printStackTrace();
            }

            cliente.setUsernameTwitter(twitter);
            cliente.setTelefono(telefono);
            cliente.setDocumento(documento);
            cliente.setIdDocumento(numDocumento);
            cliente.setIdNacionalidad(idNacionalidad);
            cliente.setDireccion(direccion);
            cliente.setIdEstadoC(idEstadoC);
            cliente.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime()));
            cliente.setIdEmpleado(idEmpleado);
            cliente.setMontoMaximo(montoMaximo);
            Query query = new Query();
            query.insertarCliente(cliente);

            nombreText.setText("");
            apellidoText.setText("");
            emailText.setText("");
            telefonoText.setText("");
            emailText.setText("");
            twitterText.setText("");
            documentoText.setText("");
            direccionText.setText("");
            montoText.setText("");
            pathText.setText("");
            fechaPicker.setValue(LocalDate.now());
            comboEncargado.getSelectionModel().select(0);
            comboEstadoCivil.getSelectionModel().select(0);


            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Cliente insertado");
            alerta.setHeaderText(null);
            alerta.setContentText("El cliente "+nombre+" "+apellido+" fue insertado a la base de datos.");
            pathText.setText("");
               try {
                   File file = new File("src\\sample\\img\\portrait.png");
                   BufferedImage bufferedImage = ImageIO.read(file);
                   Image image = SwingFXUtils.toFXImage(bufferedImage,null);
                   avatarImg.setImage(image);
                   avatarImg1.setImage(image);
               }catch (Exception e){
                   e.printStackTrace();
            }

            alerta.showAndWait();
            reiniciarTable();
        }
    }

    private boolean validateFields(){
        boolean salida = false;
        String warn = "";
        if(nombreText.getText().trim().equals("")){
            salida=true;
            warn=warn+"Nombre, ";
        }
        if(apellidoText.getText().trim().equals("")){
            salida=true;
            warn=warn+"Apellido, ";
        }
        if(emailText.getText().trim().equals("")){
            salida=true;
            warn=warn+"Correo Electrónico, ";
        }
        if(telefonoText.getText().trim().equals("")){
            salida=true;
            warn=warn+"Teléfono, ";
        }
        if(documentoText.getText().trim().equals("")){
            salida=true;
            warn=warn+"Número de documento, ";
        }

        if(direccionText.getText().trim().equals("")){
            salida=true;
            warn=warn+"Dirección, ";
        }
        if(montoText.getText().trim().equals("")){
            salida=true;
            warn=warn+"Monto máximo, ";
        }
        if(pathText.getText().trim().equals("")){
            salida=true;
            warn=warn+"Dirección de la imagen, ";
        }
        if (salida) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Hace falta llenar campos:");
            alerta.setHeaderText(null);
            alerta.setContentText(warn.substring(0, warn.length() - 2)+".");
            alerta.showAndWait();
        }
        return salida;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboEstadoCivil.setItems(comboEstadoCivilContent);
        comboNacionalidad.setItems(comboNacionalidadContent);
        comboEncargado.setItems(comboEncargadoContent);
        nacionalidadEdit.setItems(comboNacionalidadContent);
        estadoCivilEdit.setItems(comboEstadoCivilContent);
        encargadoEdit.setItems(comboEncargadoContent);

        imgAgregar.setEffect(new Glow(0.85));

        nombreText.addEventFilter(KeyEvent.ANY,handlerLetters);
        nombreEdit.addEventFilter(KeyEvent.ANY,handlerLetters);
        apellidoText.addEventFilter(KeyEvent.ANY,handlerLetters);
        apellidoEdit.addEventFilter(KeyEvent.ANY,handlerLetters);
        telefonoText.addEventFilter(KeyEvent.ANY,handlerNumbers);
        documentoText.addEventFilter(KeyEvent.ANY,handlerNumbers);
        montoText.addEventFilter(KeyEvent.ANY,handlerFloat);
        comboNacionalidad.getSelectionModel().select(72);
        comboEstadoCivil.getSelectionModel().select(0);
        comboEncargado.getSelectionModel().select(0);
        fechaPicker.setValue(LocalDate.now());
        telefonoBusqueda.addEventFilter(KeyEvent.ANY,handlerNumbers);
        documentoBusqueda.addEventFilter(KeyEvent.ANY,handlerNumbers);
        nombreBusqueda.addEventFilter(KeyEvent.ANY,handlerLetters);
        apellidoBusqueda.addEventFilter(KeyEvent.ANY,handlerLetters);
        pathText.setEditable(false);
        reiniciarTable();

        Query query = new Query();
        //Detalle1
        ObservableList<Detalle1> lista1 = query.CallDetalle1();
        detalle1.setItems(lista1);
        d1Nacionalidad.setCellValueFactory(new PropertyValueFactory<Detalle1,String>("nacionalidad"));
        d1Departamento.setCellValueFactory(new PropertyValueFactory<Detalle1,String>("departamento"));
        d1Promedio.setCellValueFactory(new PropertyValueFactory<Detalle1,Float>("promedio"));

        //Resumen1
        ObservableList<Resumen1> lista2 = query.CallResumen1();
        resumen1.setItems(lista2);
        r1Nacionalidad.setCellValueFactory(new PropertyValueFactory<Resumen1,String>("nacionalidad"));
        r1Promedio.setCellValueFactory(new PropertyValueFactory<Resumen1,Float>("promedio"));

        //Detalle 2
        ObservableList<Detalle2> lista3 = query.CallDetalle2();
        detalle2.setItems(lista3);
        d2Nacionalidad.setCellValueFactory(new PropertyValueFactory<Detalle2,String>("nacionalidad"));
        d2Edad.setCellValueFactory(new PropertyValueFactory<Detalle2,String>("edad"));
        d2Cantidad.setCellValueFactory(new PropertyValueFactory<Detalle2,String>("cantidad"));

        //Resumen2
        ObservableList<Resumen2> lista4 = query.CallResumen2();
        resumen2.setItems(lista4);
        r2Cantidad.setCellValueFactory(new PropertyValueFactory<Resumen2,Integer>("cantidad"));
        r2Edad.setCellValueFactory(new PropertyValueFactory<Resumen2,Integer>("edad"));

        //Detalle3
        ObservableList<Detalle3> lista5 = query.CallDetalle3();
        detalle3.setItems(lista5);
        d3CantidadPrestamos.setCellValueFactory(new PropertyValueFactory<Detalle3,Integer>("cantidad"));
        d3EstadoCivil.setCellValueFactory(new PropertyValueFactory<Detalle3,String>("estadoCivil"));
        d3Nacionalidad.setCellValueFactory(new PropertyValueFactory<Detalle3,String>("nacionalidad"));

        //Resumen3
        ObservableList<Resumen3>  lista6 = query.CallResumen3();
        resumen3.setItems(lista6);
        r3Cantidad.setCellValueFactory(new PropertyValueFactory<Resumen3,Integer>("cantidad"));

        //Detalle4
        ObservableList<Detalle4> lista7 = query.CallDetalle4();
        detalle4.setItems(lista7);
        d4Cantidad.setCellValueFactory(new PropertyValueFactory<Detalle4,Integer>("cantidad"));
        d4Departamento.setCellValueFactory(new PropertyValueFactory<Detalle4,String>("departamento"));
        d4Mes.setCellValueFactory(new PropertyValueFactory<Detalle4,Integer>("mes"));
        d4Nacionalidad.setCellValueFactory(new PropertyValueFactory<Detalle4,String>("nacionalidad"));

        //Resumen4
        ObservableList<Resumen4> lista8 = query.CallResumen4();
        resumen4.setItems(lista8);
        r4Cantidad.setCellValueFactory(new PropertyValueFactory<Resumen4,Integer>("cantidad"));
        r4Mes.setCellValueFactory(new PropertyValueFactory<Resumen4,Integer>("mes"));

        //Detalle5
        ObservableList<Detalle5> lista9 = query.CallDetalle5();
        detalle5.setItems(lista9);
        d5Cantidad.setCellValueFactory(new PropertyValueFactory<Detalle5,Integer>("cantidad"));
        d5Departamento.setCellValueFactory(new PropertyValueFactory<Detalle5,String>("departamento"));
        d5Nombre.setCellValueFactory(new PropertyValueFactory<Detalle5,String>("empleado"));

        //Resumen5
        ObservableList<Resumen5> lista10 = query.CallResumen5();
        resumen5.setItems(lista10);
        r5Cantidad.setCellValueFactory(new PropertyValueFactory<Resumen5,Integer>("cantidad"));
        r5Nacionalidad.setCellValueFactory(new PropertyValueFactory<Resumen5,String>("nacionalidad"));

        //Cuano se haga click en una fila de la tabla de clientes
        tablaResultado.setOnMouseClicked((MouseEvent event) -> {
            Fila1 fila = tablaResultado.getSelectionModel().getSelectedItem();
            filaSeleccionada = fila;
            editarUsuarioButton.setVisible(true);
            eliminarUsuarioButton.setVisible(true);
            String id = fila.getNumDocumento();
            if(event.getButton().equals(MouseButton.PRIMARY)){
                File imagen = new File("src\\sample\\img\\"+id+".jpg");
                avatar = imagen;
                try {
                    BufferedImage bufferedImage = ImageIO.read(imagen);
                    Image image = SwingFXUtils.toFXImage(bufferedImage,null);
                    avatarSeleccionado.setImage(image);
                    avatarImg1.setImage(image);
                    pathEdit.setText("src\\sample\\img\\"+id+".jpg");
                }catch (Exception e){
                    try {
                        File picture = new File("src\\sample\\img\\portrait.png");
                        BufferedImage bufferedImage = ImageIO.read(picture);
                        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
                        avatarSeleccionado.setImage(image);
                        avatarImg1.setImage(image);
                        pathEdit.setText("src\\sample\\img\\portrait.png");
                    }catch (Exception e1){
                        e.printStackTrace();
                    }
                }
            }
            idSeleccionado.setText("Documento no. "+fila.getNumDocumento());
            nombreSeleccionado.setText(fila.getNombre()+" "+fila.getApellido());
        });

        //Para llenar la tabla de tweets
        ArrayList<String> usuarios = query.usuariosQuery();
        TweetController tweetController = new TweetController();
        tweetController.reiniciarColeccion();
        tweetController.IngresarVariosTweets(usuarios);
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i<usuarios.size();i++){
            tweets.add(tweetController.buscarUltimoTweet(usuarios.get(i)));
            String usuario = tweets.get(i).getUsuario();
            String fecha = tweets.get(i).getFecha();
            String texto = tweets.get(i).getTexto();
            String url = tweets.get(i).getUrl();
            try{
                Label lbl = new Label ("@"+usuario+" el "+fecha+": "+texto);
                lbl.setGraphic(new ImageView(new Image(new FileInputStream("src\\sample\\img\\twitteruser.png"))));

                tweetList.getItems().add(lbl);
                tweetList.setExpanded(true);
                tweetList.setDepthProperty(1);
            }catch (IOException e){
                System.out.println("No se encontró el logo de twitter.");
            }
        }
        tweetList.setOnMouseClicked((MouseEvent event) -> {
            Label labelSelected = tweetList.getSelectionModel().getSelectedItem();
            String todalalabel = labelSelected.getText();
            String path = "www.twitter.com";
            for(int j = 0; j<tweets.size();j++){
                if (todalalabel.contains(tweets.get(j).getUsuario())){
                    path = tweets.get(j).getUrl();
                    break;
                }
            }
            try {

                java.awt.Desktop.getDesktop().browse(new java.net.URI(path));
            }catch (Exception e){
                System.out.println("La página no existe.");
            }
        });


        //grafica 1
        int[] datosG1 = query.grafica1Query();
        XYChart.Series set1 = new XYChart.Series();
        set1.getData().add(new XYChart.Data("Sí tienen Twitter",datosG1[0]));
        System.out.println("notwitter"+datosG1[1]);
        set1.getData().add(new XYChart.Data("No tienen Twitter",datosG1[1]));
        chart1.getData().addAll(set1);

        //grafica 2
        int [] datodG2 = query.grafica2Query();
        XYChart.Series set2 = new XYChart.Series();
        set2.getData().add(new XYChart.Data("Joice Miranda",datodG2[0]));
        set2.getData().add(new XYChart.Data("José Ramirez",datodG2[1]));
        set2.getData().add(new XYChart.Data("Rodrigo Barrios",datodG2[2]));
        chart2.getData().addAll(set2);

        //grafica 3
        int [] datoG3 = query.grafica3Query();
        XYChart.Series set3 = new XYChart.Series();
        set3.getData().add(new XYChart.Data("Soltero",datoG3[0]));
        set3.getData().add(new XYChart.Data("Casado",datoG3[1]));
        set3.getData().add(new XYChart.Data("Divorciado",datoG3[2]));
        set3.getData().add(new XYChart.Data("Viudo",datoG3[3]));
        chart3.getData().addAll(set3);

        //grafica 4
        int[] datosG4 = query.grafica4Query();
        XYChart.Series set4 = new XYChart.Series();
        set4.getData().add(new XYChart.Data("Alta Verapaz",datosG4[0]));
        set4.getData().add(new XYChart.Data("Baja Verapaz",datosG4[1]));
        set4.getData().add(new XYChart.Data("Chimaltenango",datosG4[2]));
        set4.getData().add(new XYChart.Data("Chiquimula",datosG4[3]));
        set4.getData().add(new XYChart.Data("El Progreso",datosG4[4]));
        set4.getData().add(new XYChart.Data("Escuintla",datosG4[5]));
        set4.getData().add(new XYChart.Data("Guatemala",datosG4[6]));
        set4.getData().add(new XYChart.Data("Huehuetenango",datosG4[7]));
        set4.getData().add(new XYChart.Data("Izabal",datosG4[8]));
        set4.getData().add(new XYChart.Data("Jalapa",datosG4[9]));
        set4.getData().add(new XYChart.Data("Jutiapa",datosG4[10]));
        set4.getData().add(new XYChart.Data("Petén",datosG4[11]));
        set4.getData().add(new XYChart.Data("Quetzaltenango",datosG4[12]));
        set4.getData().add(new XYChart.Data("Quiché",datosG4[13]));
        set4.getData().add(new XYChart.Data("Retalhuleu",datosG4[14]));
        set4.getData().add(new XYChart.Data("Sacatepéquez",datosG4[15]));
        set4.getData().add(new XYChart.Data("San Marcos",datosG4[16]));
        set4.getData().add(new XYChart.Data("Santa Rosa",datosG4[17]));
        set4.getData().add(new XYChart.Data("Sololá",datosG4[18]));
        set4.getData().add(new XYChart.Data("Suchitepéquez",datosG4[19]));
        set4.getData().add(new XYChart.Data("Totonicapán",datosG4[20]));
        set4.getData().add(new XYChart.Data("Zacapa",datosG4[21]));
        chart4.getData().addAll(set4);

        int[] datosG5 = query.grafica5Query();
        XYChart.Series set5 = new XYChart.Series();
        set5.getData().add(new XYChart.Data(">25000",datosG5[0]));
        set5.getData().add(new XYChart.Data("<25000",datosG5[1]));
        chart5.getData().addAll(set5);
    }

    public void onReiniciarButtonClicked(MouseEvent event) {
        reiniciarTable();
    }

    private void reiniciarTable(){
            nombreBusqueda.setText("");
            apellidoBusqueda.setText("");
            telefonoBusqueda.setText("");
            documentoBusqueda.setText("");
            String[] condiciones =  new String[0];
            String[] valores = new String[0];
            Query query = new Query();
            //se llama al metodo generalQuery que retorna una lista observable para la tabla
            ObservableList<Fila1> lista = FXCollections.observableArrayList();
            lista = query.generalQuery(condiciones,valores);
            labelConteo.setText(String.valueOf(lista.size()));
            tablaResultado.setItems(lista);
            //Se enlazan las columnas a la tabla
            colTipoDoc.setCellValueFactory(new PropertyValueFactory<Fila1,String>("tipoDocumento"));
            colNumDoc.setCellValueFactory(new PropertyValueFactory<Fila1,String>("numDocumento"));
            colNombre.setCellValueFactory(new PropertyValueFactory<Fila1,String>("nombre"));
            colApellido.setCellValueFactory(new PropertyValueFactory<Fila1,String>("apellido"));
            colDireccion.setCellValueFactory(new PropertyValueFactory<Fila1,String>("direccion"));
            colNacionalidad.setCellValueFactory(new PropertyValueFactory<Fila1,String>("nacionalidad"));
            colEstadoC.setCellValueFactory(new PropertyValueFactory<Fila1,String>("estadoCivil"));
            colEncargado.setCellValueFactory(new PropertyValueFactory<Fila1,String>("encargado"));
        try {
            File picture = new File("src\\sample\\img\\portrait.png");
            BufferedImage bufferedImage = ImageIO.read(picture);
            Image image = SwingFXUtils.toFXImage(bufferedImage,null);
            avatarSeleccionado.setImage(image);
            avatarImg1.setImage(image);
            pathEdit.setText("src\\sample\\img\\portrait.png");
        }catch (Exception e){
            e.printStackTrace();
        }
        nombreSeleccionado.setText("...");
        idSeleccionado.setText("...");
        editarUsuarioButton.setVisible(false);
        eliminarUsuarioButton.setVisible(false);

    }

    //El siguiente método lo obtuve de esta fuente: https://www.youtube.com/watch?v=9YqkZJw-95o
    EventHandler<KeyEvent> handlerLetters = new EventHandler<KeyEvent>() {

        private  boolean willConsume = false;

        @Override
        public void handle(KeyEvent event) {
            Object temp0 = event.getSource();
            if(willConsume){
                event.consume();
            }
            String temp = event.getCode().toString();
            if(!event.getCode().toString().matches("[a-zA-ZáéíóúÁÉÍÓÚ´]") && event.getCode() != KeyCode.BACK_SPACE && event.getCode() != KeyCode.SPACE
            && event.getCode() != KeyCode.SHIFT){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    willConsume=false;
                }
            }
        }
    };
    EventHandler<KeyEvent> handlerNumbers = new EventHandler<KeyEvent>() {

        private  boolean willConsume = false;

        @Override
        public void handle(KeyEvent event) {
            Object temp0 = event.getSource();
            if(willConsume){
                event.consume();
            }
            String temp = event.getCode().toString();
            if(!event.getText().matches("[0-9]") && event.getCode() != KeyCode.BACK_SPACE){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    willConsume=false;
                }
            }
        }
    };

    EventHandler<KeyEvent> handlerFloat = new EventHandler<KeyEvent>() {

        private  boolean willConsume = false;
        private  boolean punto = false;
        @Override
        public void handle(KeyEvent event) {
            Object temp0 = event.getSource();
            if(willConsume){
                event.consume();
            }
            String temp = event.getCode().toString();
            if(!event.getText().matches("[0-9.]") && event.getCode() != KeyCode.BACK_SPACE){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    willConsume=false;
                }
            }
        }
    };

}
