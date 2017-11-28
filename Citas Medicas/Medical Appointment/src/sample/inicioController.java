package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class inicioController implements Initializable {
    @FXML
    private JFXButton btnCalendario;

    @FXML
    private JFXButton btnEstadoCitas;

    @FXML
    private JFXButton btnCitas;

    @FXML
    private JFXButton btnPacientes;

    @FXML
    private ImageView imgExit;

    @FXML
    private JFXButton btnUsuarios;

    @FXML
    private JFXButton btnReportes;

    Controller login = new Controller();


    public void actionCitas(ActionEvent actionEvent) {
    }

    public void actionPacientes(ActionEvent actionEvent) {
    }

    public void actionEstadoCitas(ActionEvent actionEvent) {
    }

    public void actionCalendario(ActionEvent actionEvent) {
    }

    public void actionReportes(ActionEvent actionEvent) {
    }

    public void actionUsuarios(ActionEvent actionEvent){
        Stage users = new Stage();
        users.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("users.fxml"));
            users.initModality(Modality.APPLICATION_MODAL);
            users.initOwner((Stage) ((Button) actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            users.setScene(scene);
            users.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setImgExit(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
