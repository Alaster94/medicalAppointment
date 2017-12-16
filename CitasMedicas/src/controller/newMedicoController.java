package controller;

import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Areas;
import model.Medico;
import validation.TextFieldValidation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

<<<<<<< HEAD
public class newMedicoController implements Initializable{
    @FXML    private JFXTextField txtNombreMedico;
    @FXML    private JFXTextField txtApellidoMedico;
    @FXML    private JFXTextField txtEmailMedico;
    @FXML    private JFXTextField txtDireccionMedico;
    @FXML    private JFXTextField txtIdentidadMedico;
    @FXML    private JFXTextField txtTelefonoMedico;
    @FXML    private JFXComboBox<Areas> cmbMedicoArea;
    //LABEL
    @FXML    private Label error_Identidad;
    @FXML    private Label error_DirecMedico;
    @FXML    private Label error_NombreMedico;
    @FXML    private Label error_AreaMedico;
    @FXML    private Label error_TelMedico;
    @FXML    private Label error_ApellidoMedico;
    @FXML    private Label error_EmailMedico;

    @FXML
    private Button returnList;
=======
public class newMedicoController implements Initializable {
    @FXML
    private JFXTextField txtNombreMedico;
    @FXML
    private JFXTextField txtApellidoMedico;
    @FXML
    private JFXTextField txtEmailMedico;
    @FXML
    private JFXTextField txtDireccionMedico;
    @FXML
    private JFXTextField txtIdentidadMedico;
    @FXML
    private JFXTextField txtTelefonoMedico;
    @FXML
    private JFXComboBox<Areas> cmbMedicoArea;
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92

    //Colecciones
    private ObservableList<Areas> listaAreas;
    private ObservableList<Medico> listaMedicos;

    private DBConnection conexion;

    private Medico dao;
    private Medico medicoSeleccionado;
    private Medico pacienteDetalle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexion = new DBConnection();
        //Inicializar listas
        listaAreas = FXCollections.observableArrayList();
        listaMedicos = FXCollections.observableArrayList();
        //Llenar listas
        try {
            Areas.llenarInformacion(conexion.getConnection(), listaAreas);
            Medico.llenarInformacionMedicos(conexion.getConnection(), listaMedicos);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Enlazar Listas con ComboBox y TableView
        cmbMedicoArea.setItems(listaAreas);

    }

    public void registerMedico(ActionEvent event) throws SQLException, ClassNotFoundException {
<<<<<<< HEAD
        //Crear una nueva instancia del tipo Medico
        boolean isIdentidadEmpty = validation.TextFieldValidation.istexFieldTypeNumber(txtIdentidadMedico, error_Identidad,"Campo Vacio o Incorrecto.");
        boolean isValidEmail = TextFieldValidation.isValidEmail(txtEmailMedico,error_EmailMedico,"Email Invalido.");
        boolean isNombreEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtNombreMedico, error_NombreMedico,"Nombre del Medico Requerido.");
        boolean isApellidoEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtApellidoMedico, error_ApellidoMedico,"Apellido del Medico Requerido.");
        boolean isTelefonoEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtTelefonoMedico, error_TelMedico,"Telefono del Medico Requerido.");
        boolean isDireccEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtDireccionMedico, error_DirecMedico,"Direccion del Medico Requerida.");
        if (isIdentidadEmpty && isValidEmail && isNombreEmpty && isApellidoEmpty && isTelefonoEmpty && isDireccEmpty){
            Medico a = new Medico(txtIdentidadMedico.getText(),
                    txtNombreMedico.getText(),
                    txtApellidoMedico.getText(),
                    txtDireccionMedico.getText(),
                    txtEmailMedico.getText(),
                    txtTelefonoMedico.getText(),
                    cmbMedicoArea.getSelectionModel().getSelectedItem());
            //Llamar al metodo guardarRegistro de la clase Alumno
            int resultado = a.guardarRegistro(conexion.getConnection());
            if (resultado == 1){
                listaMedicos.add(a);
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Registro agregado");
                mensaje.setContentText("El registro ha sido agregado exitosamente");
                mensaje.setHeaderText("Resultado:");
                mensaje.show();
            }
=======
        //Crear una nueva instancia del tipo Alumno
        Medico a = new Medico(txtIdentidadMedico.getText(),
                txtNombreMedico.getText(),
                txtApellidoMedico.getText(),
                txtDireccionMedico.getText(),
                txtEmailMedico.getText(),
                txtTelefonoMedico.getText(),
                cmbMedicoArea.getSelectionModel().getSelectedItem());
        //Llamar al metodo guardarRegistro de la clase Alumno
//        conexion.establecerConexion();
        int resultado = a.guardarRegistro(conexion.getConnection());
//        conexion.cerrarConexion();

        if (resultado == 1) {
            listaMedicos.add(a);
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Registro agregado");
            mensaje.setContentText("El registro ha sido agregado exitosamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
        }
    }

    public void cancelMedico(ActionEvent event) {
        txtIdentidadMedico.setText(null);
        txtNombreMedico.setText(null);
        txtApellidoMedico.setText(null);
        txtDireccionMedico.setText(null);
        txtEmailMedico.setText(null);
        txtTelefonoMedico.setText(null);
        cmbMedicoArea.setValue(null);


    }

    public void returnList(ActionEvent event) {
        Stage med = new Stage();
        med.setResizable(false);
        try {
            returnList.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../view/medicos.fxml"));
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
