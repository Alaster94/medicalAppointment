package controller;

import com.jfoenix.controls.JFXTextField;
import dba.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Citas;
import model.Medico;
import model.Pacientes;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class estadoCitaController implements Initializable{
    //Table
    @FXML
    private TableView<Citas> estadoCitaTable;
    @FXML    private TableColumn<Citas, Pacientes> clmnPaciente;
    @FXML    private TableColumn<Citas, String> clmnPagoEstado;
    @FXML    private TableColumn<Citas, Date> clmnFechaCita;
    @FXML    private TableColumn<Citas, String> clmnCitaEstado;
    @FXML    private TableColumn<Citas, Medico> clmnMedico;
    @FXML    private JFXTextField txtSearch;

    //Colecciones
    private ObservableList<Pacientes> listaPaciente;
    private ObservableList<Citas> listaCita;
    private DBConnection conexion;
    private Citas citaSeleccionada;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellTable();

    }

    private void setCellTable(){
        conexion = new DBConnection();
        //Inicializar listas
        listaPaciente = FXCollections.observableArrayList();
        listaCita = FXCollections.observableArrayList();
        //Llenar listas
        try {
            Pacientes.llenarInformacionPacientes(conexion.getConnection(), listaPaciente);
            Citas.llenarInformacionCitas(conexion.getConnection(), listaCita);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Enlazar Listas con ComboBox y TableView
        estadoCitaTable.setItems(listaCita);
        //Enlazar columnas con atributos
        clmnPaciente.setCellValueFactory(new PropertyValueFactory<Citas,Pacientes>("pacientes"));
        clmnMedico.setCellValueFactory(new PropertyValueFactory<Citas,Medico>("medico"));
        clmnFechaCita.setCellValueFactory(new PropertyValueFactory<Citas,Date>("fechaa"));
        clmnPagoEstado.setCellValueFactory(new PropertyValueFactory<Citas,String>("estadoPago"));
        clmnCitaEstado.setCellValueFactory(new PropertyValueFactory<Citas,String>("estadoCita"));
    }

    public void consultar(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String Buscar = txtSearch.getText().trim();
        listaCita.clear();
        if (txtSearch.getText().equals("")){
            setCellTable();
        }else {
            if (Buscar.length()>=1){//Realiza la busqueda solo si se ha escrito mas de 2 caracteres
                Connection con = DBConnection.getConnection();
                try {
                    Statement stnt = con.createStatement();
                    String sql = "SELECT C.idCita, C.asunto, C.fecha, C.nota, C.diagnostico, C.sintomas, C.medicamentos, C.estadoCita, C.estadoPago, " +
                            "P.idPacientes, P.nombrePaciente, P.apellidoPaciente, P.telefono, M.idMedicos, M.nombreDoctor " +
                            "FROM citas C INNER JOIN pacientes P ON (C.pacientes_idPacientes = P.idPacientes) INNER JOIN medicos M ON (P.medicos_idMedicos = M.idMedicos) WHERE P.nombrePaciente LIKE '%" + Buscar + "%'" +
                    "UNION SELECT C.idCita, C.asunto, C.fecha, C.nota, C.diagnostico, C.sintomas, C.medicamentos, C.estadoCita, C.estadoPago, " +
                            "P.idPacientes, P.nombrePaciente, P.apellidoPaciente, P.telefono, M.idMedicos, M.nombreDoctor " +
                            "FROM citas C INNER JOIN pacientes P ON (C.pacientes_idPacientes = P.idPacientes) INNER JOIN medicos M ON (P.medicos_idMedicos = M.idMedicos) WHERE P.apellidoPaciente LIKE '%" + Buscar + "%'" +
                            "UNION SELECT C.idCita, C.asunto, C.fecha, C.nota, C.diagnostico, C.sintomas, C.medicamentos, C.estadoCita, C.estadoPago, " +
                            "P.idPacientes, P.nombrePaciente, P.apellidoPaciente, P.telefono, M.idMedicos, M.nombreDoctor " +
                            "FROM citas C INNER JOIN pacientes P ON (C.pacientes_idPacientes = P.idPacientes) INNER JOIN medicos M ON (P.medicos_idMedicos = M.idMedicos) WHERE P.idPacientes LIKE '%" + Buscar + "%'";
                    ResultSet resultado = stnt.executeQuery(sql);
                    while (resultado.next()){
                        listaCita.add(
                                new Citas(
                                        resultado.getInt("idCita"),
                                        resultado.getString("asunto"),
                                        resultado.getDate("fecha"),
                                        resultado.getString("nota"),
                                        resultado.getString("diagnostico"),
                                        resultado.getString("sintomas"),
                                        resultado.getString("medicamentos"),
                                        resultado.getString("estadoCita"),
                                        resultado.getString("estadoPago"),
                                        new Pacientes(resultado.getString("idPacientes"),
                                                resultado.getString("nombrePaciente"),
                                                resultado.getString("apellidoPaciente")
                                        ),
                                        new Medico(resultado.getString("idMedicos"),
                                                resultado.getString("nombreDoctor")
                                        )
                                )
                        );

                    }
                    resultado.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
