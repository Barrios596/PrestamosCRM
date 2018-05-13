package sample;

import com.jfoenix.controls.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import java.net.URL;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
    @FXML private ImageView avatarImg;
    @FXML private ImageView avatarSeleccionado;

    @FXML private AnchorPane busquedaPanel;
    @FXML private AnchorPane agregarPanel;
    @FXML private AnchorPane reportesPanel;
    @FXML private AnchorPane twitterPanel;

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

    @FXML private JFXRadioButton dpiRadio;
    @FXML private JFXRadioButton pasaporteRadio;
    @FXML private JFXRadioButton licenciaRadio;

    @FXML private JFXDatePicker fechaPicker;

    @FXML private TableView<Fila1> tablaResultado = new TableView();

    @FXML private TableColumn<Fila1,String> colTipoDoc;
    @FXML private TableColumn<Fila1,String> colNumDoc;
    @FXML private TableColumn<Fila1,String> colNombre;
    @FXML private TableColumn<Fila1,String> colApellido;
    @FXML private TableColumn<Fila1,String> colDireccion;
    @FXML private TableColumn<Fila1,String> colNacionalidad;
    @FXML private TableColumn<Fila1,String> colEstadoC;
    @FXML private TableColumn<Fila1,String> colEncargado;

    @FXML private Label nombreSeleccionado;
    @FXML private Label idSeleccionado;

    private File avatar;


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
            conditions.add(telefono);
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
            alerta.showAndWait();
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

        imgAgregar.setEffect(new Glow(0.85));

        nombreText.addEventFilter(KeyEvent.ANY,handlerLetters);
        apellidoText.addEventFilter(KeyEvent.ANY,handlerLetters);
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

        //Cuano se haga click en una fila de la tabla de clientes
        tablaResultado.setOnMouseClicked((MouseEvent event) -> {
            Fila1 fila = tablaResultado.getSelectionModel().getSelectedItem();
            String id = fila.getNumDocumento();
            if(event.getButton().equals(MouseButton.PRIMARY)){
                File imagen = new File("src\\sample\\img\\"+id+".jpg");
                try {
                    BufferedImage bufferedImage = ImageIO.read(imagen);
                    Image image = SwingFXUtils.toFXImage(bufferedImage,null);
                    avatarSeleccionado.setImage(image);
                }catch (Exception e){
                    try {
                        File picture = new File("src\\sample\\img\\portrait.png");
                        BufferedImage bufferedImage = ImageIO.read(picture);
                        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
                        avatarSeleccionado.setImage(image);
                    }catch (Exception e1){
                        e.printStackTrace();
                    }
                }
            }
            idSeleccionado.setText("Documento no. "+fila.getNumDocumento());
            nombreSeleccionado.setText(fila.getNombre()+" "+fila.getApellido());
        });
    }

    public void onReiniciarButtonClicked(MouseEvent event) {
        reiniciarTable();
    }

    private void reiniciarTable(){
            String[] condiciones =  new String[0];
            String[] valores = new String[0];
            Query query = new Query();
            //se llama al metodo generalQuery que retorna una lista observable para la tabla
            ObservableList<Fila1> lista = FXCollections.observableArrayList();
            lista = query.generalQuery(condiciones,valores);
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
            if(!event.getCode().toString().matches("[a-zA-Z]") && event.getCode() != KeyCode.BACK_SPACE && event.getCode() != KeyCode.SPACE
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
