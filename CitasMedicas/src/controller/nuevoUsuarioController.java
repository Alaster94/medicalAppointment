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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Medico;
import model.Pacientes;
import model.TipoUsuario;
import model.Usuarios;
import validation.TextFieldValidation;

import java.io.IOException;
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
    @FXML    private JFXPasswordField txtConfirmPassword;
    //RadioButton
    @FXML    private ToggleGroup GrupoGenero;
    @FXML    private JFXRadioButton rbtInactivo;
    @FXML    private JFXRadioButton rbtActivo;
    //DatePicker
    @FXML    private DatePicker dpBirthDate;
    //ComboBox
    @FXML    private JFXComboBox<TipoUsuario> cmbPrivilegio;
    @FXML    private Button returnList;
    //LABEL
    @FXML    private Label error_Identidad;
    @FXML    private Label error_Nombre;
    @FXML    private Label error_Telefono;
    @FXML    private Label error_Sesion;
    @FXML    private Label error_Password;
    @FXML    private Label error_Apellido;
    @FXML    private Label error_Email;
    @FXML    private Label error_Fecha;
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
        boolean isIdentidadEmpty = TextFieldValidation.istexFieldTypeNumber(txtIdUsuario, error_Identidad,"Campo Vacio o Incorrecto.");
        boolean isValidEmail = TextFieldValidation.isValidEmail(txtEmail,error_Email,"Email Invalido.");
        boolean isNombreEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtNombre, error_Nombre,"Nombre del Paciente Requerido.");
        boolean isApellidoEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtApellido, error_Apellido,"Apellido del Paciente Requerido.");
        boolean isTelefonoEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtTelefono, error_Telefono,"Telefono del Medico Requerido.");
        boolean isSesionEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtUsuario, error_Sesion,"Direccion del Medico Requerida.");
        boolean isPasswordMatched = TextFieldValidation.isPasswordMatched(txtPassword,txtConfirmPassword, error_Password,"Password no es la misma");

        if (isIdentidadEmpty && isValidEmail && isNombreEmpty && isApellidoEmpty && isTelefonoEmpty && isSesionEmpty && isPasswordMatched) {
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
                    rbtActivo.isSelected() ? "Activo" : "Inactivo",
                    cmbPrivilegio.getSelectionModel().getSelectedItem());
            //Llamar al metodo guardarRegistro de la clase Alumno
            int resultado = u.guardarUsuario(conexion.getConnection());
            if (resultado == 1) {
                listUsuarios.add(u);
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Registro Agregado con Exito");
                mensaje.setContentText("El registro ha sido agregado exitosamente");
                mensaje.setHeaderText("Resultado:");
                mensaje.show();
            }
        }
    }

    public void cancelAction(ActionEvent event) {
        txtIdUsuario.setText(null);
        txtNombre.setText(null);
        txtApellido.setText(null);
        dpBirthDate.setValue(null);
        txtTelefono.setText(null);
        txtDireccion.setText(null);
        txtEmail.setText(null);
        txtUsuario.setText(null);
        txtPassword.setText(null);
        rbtActivo.setSelected(false);
        rbtInactivo.setSelected(false);
        cmbPrivilegio.setValue(null);
    }

    public void returnList(ActionEvent event) {
        Stage med = new Stage();
        med.setResizable(false);
        try {
            returnList.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../view/usuarios.fxml"));
            med.initModality(Modality.APPLICATION_MODAL);
            med.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            med.setScene(scene);
            med.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
