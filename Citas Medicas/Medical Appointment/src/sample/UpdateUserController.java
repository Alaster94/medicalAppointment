package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateUserController implements Initializable{

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



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
