package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateUserController implements Initializable {

    @FXML
    private CheckBox cbxEstado;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtDireccion;

    @FXML
    private JFXTextField txtTelefono;

    @FXML
    private DatePicker dpBirthDate;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtApellido;

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXTextField txtPassword;

    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCliente(String id) {
//        ResultSet datosCliente = updateModelUser.mostrarUsuario(id);
//        try {
//            datosCliente.next();
////            System.out.println(datosCliente.getString("nombres"));
////            txtNombre.setText(datosCliente.getString("nombres"));
////            txtApellido.setText(datosCliente.getString("apellidos"));
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
