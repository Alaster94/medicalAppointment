package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dba.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import model.Medico;
import model.Pacientes;
import model.TipoUsuario;
import model.Usuarios;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class nuevoUsuarioController implements Initializable{
    //TextField
    @FXML    private JFXTextField txtIdUsuario;
    @FXML    private JFXTextField txtNombre;
    @FXML    private JFXTextField txtDireccion;
    @FXML    private JFXTextField txtTelefono;
    @FXML    private JFXTextField txtEmail;
    @FXML    private JFXTextField txtApellido;
    @FXML    private JFXTextField txtUsuario;
    //PasswordField
    @FXML    private JFXPasswordField txtPassword;
    //RadioButton
    @FXML    private ToggleGroup GrupoGenero;
    @FXML    private JFXRadioButton rbtInactivo;
    @FXML    private JFXRadioButton rbtActivo;
    //DatePicker
    @FXML    private DatePicker dpBirthDate;
    //ComboBox
    @FXML    private JFXComboBox<TipoUsuario> cmbPrivilegio;
    //Colecciones
    private ObservableList<TipoUsuario> listTipoUsuario;
    private ObservableList<Usuarios> listUsuarios;
    private DBConnection conexion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexion = new DBConnection();
        //Inicializar listas
        listTipoUsuario = FXCollections.observableArrayList();
        listUsuarios = FXCollections.observableArrayList();
        //Llenar listas
        try {
            TipoUsuario.llenarInformacionTipo(conexion.getConnection(), listTipoUsuario);
            Usuarios.llenarInformacionUsuarios(conexion.getConnection(), listUsuarios);
        } catch (ClassNotFoundException a) {
            a.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Enlazar Listas con ComboBox y TableView
        cmbPrivilegio.setItems(listTipoUsuario);
    }

    public void registrarUsuario(ActionEvent event) throws SQLException, ClassNotFoundException {
        //Crear una nueva instancia del tipo Paciente
        Usuarios u = new Usuarios(txtIdUsuario.getText(),
                txtNombre.getText(),
                txtApellido.getText(),
                Date.valueOf(dpBirthDate.getValue()),
                txtTelefono.getText(),
                txtDireccion.getText(),
                txtEmail.getText(),
                txtUsuario.getText(),
                txtPassword.getText(),
                rbtActivo.isSelected()?"Activo":"Inactivo",
                cmbPrivilegio.getSelectionModel().getSelectedItem());
        //Llamar al metodo guardarRegistro de la clase Alumno
        int resultado = u.guardarUsuario(conexion.getConnection());
        if (resultado == 1){
            listUsuarios.add(u);
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Registro Agregado con Exito");
            mensaje.setContentText("El registro ha sido agregado exitosamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        }
    }

    public void cancelAction(ActionEvent event) {
    }
}
