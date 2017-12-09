package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dba.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Areas;
import model.Medico;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
//        conexion.establecerConexion();
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
//        medicos.setItems(listaMedicos);
        cmbMedicoArea.setItems(listaAreas);

    }

    public void registerMedico(ActionEvent event) throws SQLException, ClassNotFoundException {
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
        }


    }

    public void cancelMedico(ActionEvent event) {
    }
}
