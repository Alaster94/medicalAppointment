package controller;

import com.jfoenix.controls.JFXComboBox;
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
import model.Areas;
import model.Medico;
import model.Pacientes;
import validation.TextFieldValidation;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class newPacienteController implements Initializable {
    //TextField
    @FXML private JFXTextField txtIdentidadPaciente;
    @FXML private JFXTextField txtNombrePaciente;
    @FXML private JFXTextField txtTelefonoPaciente;
    @FXML private JFXTextField txtApellidoPaciente;
    @FXML private JFXTextField txtDireccionPaciente;
    @FXML private JFXTextField txtEmailPaciente;
    //RadioButton
    @FXML private JFXRadioButton rbtFemenino;
    @FXML private JFXRadioButton rbtMasculino;
    @FXML private ToggleGroup GrupoGenero;
    //DatePicker
    @FXML private DatePicker dpFechaIngreso;
    //ComboBox
    @FXML private JFXComboBox<Medico> cmbMedico;

    @FXML private Button returnList;

    //LABEL
    @FXML    private Label error_Identidad;
    @FXML    private Label error_Nombre;
    @FXML    private Label error_TelMedico;
    @FXML    private Label error_Apellido;
    @FXML    private Label error_Email;

    //Colecciones
    private ObservableList<Medico> listaMedicos;
    private ObservableList<Pacientes> listaPacientes;

    private DBConnection conexion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexion = new DBConnection();
        //Inicializar listas
        listaMedicos = FXCollections.observableArrayList();
        listaPacientes = FXCollections.observableArrayList();
        //Llenar listas
        try {
            Medico.llenarInformacionMedicos(conexion.getConnection(), listaMedicos);
            Pacientes.llenarInformacionPacientes(conexion.getConnection(), listaPacientes);
        } catch (ClassNotFoundException a) {
            a.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Enlazar Listas con ComboBox y TableView
        cmbMedico.setItems(listaMedicos);
    }

    public void registerPaciente(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isIdentidadEmpty = TextFieldValidation.istexFieldTypeNumber(txtIdentidadPaciente, error_Identidad,"Campo Vacio o Incorrecto.");
        boolean isValidEmail = TextFieldValidation.isValidEmail(txtEmailPaciente,error_Email,"Email Invalido.");
        boolean isNombreEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtNombrePaciente, error_Nombre,"Nombre del Paciente Requerido.");
        boolean isApellidoEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtApellidoPaciente, error_Apellido,"Apellido del Paciente Requerido.");
//        boolean isTelefonoEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtTelefonoMedico, error_TelMedico,"Telefono del Medico Requerido.");
//        boolean isDireccEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtDireccionMedico, error_DirecMedico,"Direccion del Medico Requerida.");
        if (isIdentidadEmpty && isValidEmail && isNombreEmpty && isApellidoEmpty) {
            //Crear una nueva instancia del tipo Paciente
            Pacientes p = new Pacientes(txtIdentidadPaciente.getText(),
                    txtNombrePaciente.getText(),
                    txtApellidoPaciente.getText(),
                    rbtFemenino.isSelected() ? "Femenino" : "Masculino",
                    Date.valueOf(dpFechaIngreso.getValue()),
                    txtDireccionPaciente.getText(),
                    txtEmailPaciente.getText(),
                    txtTelefonoPaciente.getText(),
                    cmbMedico.getSelectionModel().getSelectedItem());
            //Llamar al metodo guardarRegistro de la clase Alumno
            int resultado = p.guardarPaciente(conexion.getConnection());
            if (resultado == 1) {
                listaPacientes.add(p);
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Registro Agregado");
                mensaje.setContentText("El registro ha sido agregado exitosamente");
                mensaje.setHeaderText("Resultado:");
                mensaje.show();
            }
        }
    }

    public void cancelPaciente(ActionEvent event) {
        txtIdentidadPaciente.setText(null);
        txtNombrePaciente.setText(null);
        txtApellidoPaciente.setText(null);
        rbtFemenino.setSelected(false);
        rbtMasculino.setSelected(false);
        dpFechaIngreso.setValue(null);
        txtDireccionPaciente.setText(null);
        txtEmailPaciente.setText(null);
        txtTelefonoPaciente.setText(null);
        cmbMedico.setValue(null);
    }


    public void returnList(ActionEvent event) {
        Stage med = new Stage();
        med.setResizable(false);
        try {
            returnList.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../view/pacient.fxml"));
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
