package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import model.usersList;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class nuevoUsuarioController implements Initializable {
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


    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<usersList> data;

    public void registerAction(ActionEvent event) {

        String sql = "INSERT INTO usuarios(nombres,apellidos,birthDate,telefono,direccion,email,usuario,password,estado) VALUE (?,?,?,?,?,?,?,?,?)";
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
//        String birthDate = (String) dpBirthDate.getUserData();
//        Object birthDate = dpBirthDate.getUserData();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();
        String email = txtEmail.getText();
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();
        String estado = cbxEstado.getText();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, apellido);
//            pst.setString(3,birthDate);
            pst.setDate(3, java.sql.Date.valueOf(dpBirthDate.getValue()));
            pst.setString(4, telefono);
            pst.setString(5, direccion);
            pst.setString(6, email);
            pst.setString(7, usuario);
            pst.setString(8, password);
            pst.setString(9, estado);


            int i = pst.executeUpdate();
            if (i == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Datos insertados corectamente");
                alert.show();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelAction(ActionEvent event) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            con = dba.DBConnection.getConnection();
//            data = FXCollections.observableArrayList();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
