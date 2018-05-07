package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.krb5.SCDynamicStoreConfig;

public class Main extends Application {
    //variables que tienen el corrimiento en ambas direcciones
    private double xOffset;
    private double yOffset;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //cuando se presiona el panel
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Se actualiza la posición del panel
                xOffset=event.getScreenX();
                yOffset=event.getSceneY();
            }
        });

        //Con la función anterior, se usa la siguiente para mover el panel

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX()-xOffset);
                primaryStage.setY(event.getScreenY()-yOffset);
            }
        });

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("CRM");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
