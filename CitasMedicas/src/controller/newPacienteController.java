package controller;

import com.jfoenix.controls.JFXComboBox;
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
import model.Areas;
import model.Medico;
import model.Pacientes;

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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Enlazar Listas con ComboBox y TableView
        cmbMedico.setItems(listaMedicos);
    }

    public void registerPaciente(ActionEvent event) throws SQLException, ClassNotFoundException {
        //Crear una nueva instancia del tipo Paciente
        Pacientes p = new Pacientes(txtIdentidadPaciente.getText(),
                txtNombrePaciente.getText(),
                txtApellidoPaciente.getText(),
                rbtFemenino.isSelected()?"Femenino":"Masculino",
                Date.valueOf(dpFechaIngreso.getValue()),
                txtDireccionPaciente.getText(),
                txtEmailPaciente.getText(),
                txtTelefonoPaciente.getText(),
                cmbMedico.getSelectionModel().getSelectedItem());
        //Llamar al metodo guardarRegistro de la clase Alumno
        int resultado = p.guardarPaciente(conexion.getConnection());
        if (resultado == 1){
            listaPacientes.add(p);
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Registro agregado");
            mensaje.setContentText("El registro ha sido agregado exitosamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        }
    }

    public void cancelPaciente(ActionEvent event) {
    }


}
