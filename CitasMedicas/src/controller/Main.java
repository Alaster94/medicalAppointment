package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
//    private static final String CONNECTION_STRING = "jdbc:mysql://127.0.0.1:3306/medicalAppointment";
//    private static final String USUARIO = "root";
//    private static final String CLAVE = "";
//    private static Connection conexion;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setResizable(false);//Ocultar la opcion de maximizar
        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
