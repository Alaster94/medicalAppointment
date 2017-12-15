package model;

import dba.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class updateModelUser implements Initializable {

//    public static ResultSet mostrarUsuario(String id){
//        try {
//            if(DBConnection.conn == null){
//                System.out.println("es nulo");
//            }
//            ResultSet datosUsuario = DBConnection.conn.executeQuery("SELECT * FROM usuarios WHERE idUsuarios = '"+ id +"'");
//
//
//            return datosUsuario;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return  null;
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
