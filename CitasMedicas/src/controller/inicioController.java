package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class inicioController implements Initializable {
    @FXML    private JFXButton btnCalendario;
    @FXML    private JFXButton btnEstadoCitas;
    @FXML    private JFXButton btnCitas;
    @FXML    private JFXButton btnPacientes;
    @FXML    private ImageView imgExit;
    @FXML    private JFXButton btnUsuarios;
    @FXML    private JFXButton btnReportes;
    @FXML    private Label user;
    private String users;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUsername(Controller.getInstance().username());
    }
    public void setUsername(String user){
        this.user.setText("Bienvenido(a), "+user);
//        if (user.equals("alaster")){
//            btnCitas.setDisable(true);
//            btnPacientes.setDisable(true);
//        }
    }

//    public void setUsername(String user){
//        this.users = user;
//        if (users.equals("alaster")){
//            btnCitas.setDisable(true);
//            btnPacientes.setDisable(true);
//        }
//    }

    public void actionCitas(ActionEvent actionEvent) {
        Stage users = new Stage();
        users.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/Cita.fxml"));
            users.initModality(Modality.APPLICATION_MODAL);
            users.initOwner((Stage) ((Button) actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            users.setScene(scene);
            users.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionPacientes(ActionEvent actionEvent) {
        Stage users = new Stage();
        users.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/pacient.fxml"));
            users.initModality(Modality.APPLICATION_MODAL);
            users.initOwner((Stage) ((Button) actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            users.setScene(scene);
            users.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionEstadoCitas(ActionEvent actionEvent) {
        Stage users = new Stage();
        users.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/estadoCita.fxml"));
            users.initModality(Modality.APPLICATION_MODAL);
            users.initOwner((Stage) ((Button) actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            users.setScene(scene);
            users.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionCalendario(ActionEvent actionEvent) {
        try {
            excel.crearExcel();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void actionReportes(ActionEvent actionEvent) {
        Stage users = new Stage();
        users.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/FXbarchart.fxml"));
            users.initModality(Modality.APPLICATION_MODAL);
            users.initOwner((Stage) ((Button) actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            users.setScene(scene);
            users.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionUsuarios(ActionEvent actionEvent) {
        Stage users = new Stage();
        users.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/usuarios.fxml"));
            users.initModality(Modality.APPLICATION_MODAL);
            users.initOwner((Stage) ((Button) actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            users.setScene(scene);
            users.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setImgExit() {

    }

    public void actionPacientes(ActionEvent actionEvent) {
        Stage users = new Stage();
        users.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/pacienteee.fxml"));
            users.initModality(Modality.APPLICATION_MODAL);
            users.initOwner((Stage) ((Button) actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            users.setScene(scene);
            users.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void actionMedicos(ActionEvent event) {
        Stage users = new Stage();
        users.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/medicos.fxml"));
            users.initModality(Modality.APPLICATION_MODAL);
            users.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            users.setScene(scene);
            users.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void salir(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
