package controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dba.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Citas;
import model.Medico;
import model.Pacientes;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class citasController implements Initializable {
    //TextField y TextArea Nuevo
    @FXML    private JFXTextArea sintomasCita;
    @FXML    private JFXTextArea diagnosticoCita;
    @FXML    private JFXTextArea notaCita;
    @FXML    private JFXTextArea medicamentoCita;
    @FXML    private JFXTextArea asuntoCita;
    @FXML    private JFXTextField apellidoCitaPaciente;
    @FXML    private JFXTextField txtSearchCita;
    @FXML    private JFXTextField nombreCitaPaciente;
    //TextField y TextArea Actualizar
    @FXML    private JFXTextArea sintomas;
    @FXML    private JFXTextArea diagnostico;
    @FXML    private JFXTextArea nota;
    @FXML    private JFXTextArea medicamento;
    @FXML    private JFXTextArea asunto;
    @FXML    private JFXTextField apellidoPaciente;
    @FXML    private JFXTextField nombrePaciente;
    //TextField y TextArea Detalle
    @FXML    private JFXTextArea detSintomasCita;
    @FXML    private JFXTextArea detAsuntoCita;
    @FXML    private JFXTextArea detMedicamentoCita;
    @FXML    private JFXTextArea detDiagnosticoCita;
    @FXML    private JFXTextField detapellidoCita;
    @FXML    private JFXTextArea detNotaCita;
    @FXML    private JFXTextField detCitaEstado;
    @FXML    private JFXTextField detnombreCita;
    @FXML    private JFXTextField detEstadoPagoCita;
    //Table
    @FXML    private TableView<Citas> citasPacientes;
    @FXML    private TableColumn<Citas, Pacientes> nombrePacienteCita;
    @FXML    private TableColumn<Citas, Date> fechaCita;
    @FXML    private TableColumn<Citas, String> estadoCita;
    @FXML    private TableColumn<Citas, Pacientes> idPacienteCita;
    @FXML    private TableColumn<Citas, Pacientes> apellidoPacienteCita;
    @FXML    private TableColumn<Citas, Pacientes> telefonoPacienteCita;
    //TabPane
    @FXML    private TabPane tabPaneCitas;
    @FXML    private Tab nuevaCita;
    @FXML    private Tab actualizarCita;
    @FXML    private Tab detalleCita;
    @FXML    private Tab listCitas;
    //Button
    @FXML    private Button viewCita;
    @FXML    private Button btnUpdateCita;
    @FXML    private Button btnNewCita;
    @FXML    private Button btnEliminarCita;
    //Label
    @FXML    private Label lbIdCitaPaciente;
    @FXML    private Label detIdCita;
    //DatePicker
    @FXML    private DatePicker detCitaFecha;
    @FXML    private DatePicker citaFecha;
    //RadioButton
    @FXML    private JFXRadioButton rbtNoPagado;
    @FXML    private JFXRadioButton rbtTerminado;
    @FXML    private JFXRadioButton rbtPagado;
    @FXML    private JFXRadioButton rbtCitaNoPagado;
    @FXML    private JFXRadioButton rbtCitaTerminado;
    @FXML    private JFXRadioButton rbtCitaPagado;
    @FXML    private JFXRadioButton rbtCitaPendiente;
    @FXML    private JFXRadioButton rbtPendiente;
    //Colecciones
    private ObservableList<Citas> listaCitas;
    private ObservableList<Pacientes> listaPacientes;
    private DBConnection conexion;
    private Pacientes pacienteSeleccionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellTable();

    }
    private void setCellTable(){
        conexion = new DBConnection();
        //Inicializa Listas
        listaCitas = FXCollections.observableArrayList();

//        listaMedicos = FXCollections.observableArrayList();
        //Llenar listas
        try {
            Citas.llenarInformacionCitas(conexion.getConnection(), listaCitas);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Enlazar Listas con ComboBox y TableView
        citasPacientes.setItems(listaCitas);
        //Enlazar columnas con atributos
        idPacienteCita.setCellValueFactory(new PropertyValueFactory<Citas,Pacientes>("idPacientes"));
        nombrePacienteCita.setCellValueFactory(new PropertyValueFactory<Citas,Pacientes>("nombrePaciente"));
        apellidoPacienteCita.setCellValueFactory(new PropertyValueFactory<Citas,Pacientes>("apellidoPaciente"));
        fechaCita.setCellValueFactory(new PropertyValueFactory<Citas,Date>("fecha"));
        telefonoPacienteCita.setCellValueFactory(new PropertyValueFactory<Citas,Pacientes>("telefono"));
        estadoCita.setCellValueFactory(new PropertyValueFactory<Citas,String>("estadoCita"));
    }

    public void registroCitas(MouseEvent mouseEvent) {
        if (listCitas.isSelected()){
            nuevaCita.setDisable(true);
            actualizarCita.setDisable(true);
            detalleCita.setDisable(true);
        }
    }

    public void consultarCita(KeyEvent keyEvent) {
    }

    public void updateCita(ActionEvent event) {
    }

    public void eliminarCita(ActionEvent event) {
    }

    public void newCita(ActionEvent event) {
    }

    public void viewPacienteCita(ActionEvent event) {
    }

    public void btnGuardarPacienteActualizado(ActionEvent event) {
    }

    public void btnImprimir(ActionEvent event) {
    }

    public void btnGuardarPaciente(ActionEvent event) {
    }

    public void registrosUsuarios(MouseEvent mouseEvent) {
    }

    public void consultarUsuarios(KeyEvent keyEvent) {
    }

    public void updateUsuario(ActionEvent event) {
    }

    public void eliminarUsuario(ActionEvent event) {
    }

    public void newUsuario(ActionEvent event) {
    }

    public void viewUsuario(ActionEvent event) {
    }

    public void btnGuardarUsuarioActualizado(ActionEvent event) {
    }
}
