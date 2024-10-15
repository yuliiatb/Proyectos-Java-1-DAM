/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prog10_tarea;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *La clase que establece el escenario del programa. Se utiliza JavaFX22. 
 * 
 * @author Yuliia Teterko Bobrytska
 */

//clase para poder hacer y ejecutar la aplicación de JavaFX
public class PROG10_Tarea extends Application {
    
    //configuración de la GUI
    @Override
    public void start(Stage primaryStage) throws IOException {
   
        Parent root= FXMLLoader.load(getClass().getResource("CalculadoraFXML.fxml"));
        Scene scene= new Scene(root);
        
        primaryStage.setTitle("Calculadora JavaFX Tarea 10");
        primaryStage.setScene(scene);
        primaryStage.show();         
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        launch(args);
    }
}
