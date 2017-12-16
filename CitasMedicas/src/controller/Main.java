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
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);//Ocultar la opcion de maximizar
        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

<<<<<<< HEAD
=======
    private void abrirConexion() {
        try {
            conexion = DriverManager.getConnection(CONNECTION_STRING, USUARIO, CLAVE);
            JOptionPane.showMessageDialog(null, "Exito");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "La conexxion a la base de datos no fue establecida");
//            e.printStackTrace();
        }
    }


    public static Connection getConexion() {
        return conexion;
    }


>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
    public static void main(String[] args) {
        launch(args);
    }

}
