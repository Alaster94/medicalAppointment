package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import dba.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Citas;
import model.Medico;
import model.Pacientes;
import validation.TextFieldValidation;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class newCitaController implements Initializable {
    @FXML    private JFXComboBox<Pacientes> cmbPaciente;
    @FXML    private JFXRadioButton rbtPendiente;
    @FXML    private ToggleGroup GrupoGenero1;
    @FXML    private JFXTextArea txtDiagnostico;
    @FXML    private JFXRadioButton rbtNoPagado;
    @FXML    private JFXRadioButton rbtTerminado;
    @FXML    private JFXTextArea txtMedicamentos;
    @FXML    private JFXTextArea txtAsunto;
    @FXML    private JFXTextArea txtSintomas;
    @FXML    private JFXRadioButton rbtPagado;
    @FXML    private ToggleGroup GrupoGenero;
    @FXML    private DatePicker dpFechaCita;
    @FXML    private Button returnList;
    @FXML    private JFXTextArea txtNota;
    //LABEL
    @FXML    private Label error_Asunto;
    @FXML    private Label error_Nota;
    @FXML    private Label error_Diagnostico;
    @FXML    private Label error_Sintomas;
    @FXML    private Label error_Medicamentos;
    //Colecciones
    private ObservableList<Pacientes> listaPacientes;
    private ObservableList<Citas> listaCitas;

    private DBConnection conexion;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexion = new DBConnection();
        //Inicializar listas
        listaCitas = FXCollections.observableArrayList();
        listaPacientes = FXCollections.observableArrayList();
        //Llenar listas
        try {
            Citas.llenarInformacionCitas(conexion.getConnection(), listaCitas);
            Pacientes.llenarInformacionPacientes(conexion.getConnection(), listaPacientes);
        } catch (ClassNotFoundException a) {
            a.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Enlazar Listas con ComboBox y TableView
        cmbPaciente.setItems(listaPacientes);

    }
    public void returnList(ActionEvent event) {
    }

    public void registerCita(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isIdentidadEmpty = TextFieldValidation.isTextAreaNotEmpty(txtSintomas, error_Sintomas,"Campo Requerido.");
        //boolean isNombreEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(citaFecha, error_FechaCita,"Nombre del Paciente Requerido.");
        boolean isApellidoEmpty = TextFieldValidation.isTextAreaNotEmpty(txtMedicamentos, error_Medicamentos,"Apellido del Paciente Requerido.");
//        boolean isTelefonoEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtTelefonoMedico, error_TelMedico,"Telefono del Medico Requerido.");
//        boolean isDireccEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtDireccionMedico, error_DirecMedico,"Direccion del Medico Requerida.");
        if (isIdentidadEmpty && isApellidoEmpty) {
            //Crear una nueva instancia del tipo Alumno
            Citas citas = new Citas(0,
                    txtAsunto.getText(),
                    Date.valueOf(dpFechaCita.getValue()),
                    txtNota.getText(),
                    txtDiagnostico.getText(),
                    txtSintomas.getText(),
                    txtMedicamentos.getText(),
                    rbtTerminado.isSelected() ? "Terminado" : "Pendiente",
                    rbtPagado.isSelected() ? "Pagado" : "Pendiente",
                    cmbPaciente.getSelectionModel().getSelectedItem()
            );
            //Llamar al metodo guardarRegistro de la clase Alumno
            int resultado = citas.guardarCita(conexion.getConnection());

            if (resultado == 1) {
                listaCitas.add(citas);
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Cita");
                mensaje.setContentText("La Cita ha sido agregada exitosamente");
                mensaje.setHeaderText("Resultado:");
                mensaje.show();
            }
        }
    }

    public void cancelCita(ActionEvent event) {
        txtAsunto.setText(null);
        dpFechaCita.setValue(null);
        txtNota.setText(null);
        txtDiagnostico.setText(null);
        txtSintomas.setText(null);
        txtMedicamentos.setText(null);
        rbtPendiente.setSelected(false);
        rbtTerminado.setSelected(false);
        rbtPagado.setSelected(false);
        rbtNoPagado.setSelected(false);
        cmbPaciente.setValue(null);
    }
}
