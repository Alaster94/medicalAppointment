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
import model.*;
import validation.TextFieldValidation;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class citaController implements Initializable {
    //Table
    @FXML    private TableView<Citas> tblViewCitas;
    @FXML    private TableColumn<Citas, String> clmnEstadoPago;
    @FXML    private TableColumn<Citas, Pacientes> clmnCitasPaciente;
    @FXML    private TableColumn<Citas, String> clmnEstadoCita;
    @FXML    private TableColumn<Citas, Date> clmnFecha;
    @FXML    private TableColumn<Citas, String> clmnNota;
    @FXML    private TableColumn<Citas, Medico> clmnCitasMedico;
    @FXML    private JFXTextField txtSearchCita;
    //NUEVA CITA
    @FXML    private JFXTextField nombreCitaMedico;
    @FXML    private JFXRadioButton rbtCitaPendiente;
    @FXML    private JFXRadioButton rbtCitaNoPagado;
    @FXML    private JFXRadioButton rbtCitaTerminado;
    @FXML    private JFXRadioButton rbtCitaPagado;
    @FXML    private JFXTextArea asuntoCita;
    @FXML    private JFXTextArea medicamentoCita;
    @FXML    private JFXTextArea notaCita;
    @FXML    private JFXTextArea sintomasCita;
    @FXML    private JFXTextArea diagnosticoCita;
    @FXML    private DatePicker citaFecha;
    //ACTUALIZAR CITA
    @FXML    private JFXTextField nombreMedico;
    @FXML    private JFXRadioButton rbtTerminado;
    @FXML    private JFXRadioButton rbtPendiente;
    @FXML    private JFXRadioButton rbtNoPagado;
    @FXML    private JFXRadioButton rbtPagado;
    @FXML    private JFXTextArea asunto;
    @FXML    private JFXTextArea sintomas;
    @FXML    private JFXTextArea diagnostico;
    @FXML    private JFXTextArea medicamento;
    @FXML    private JFXTextArea nota;
    @FXML    private DatePicker fecha;
    //DETALLE CITA
    @FXML    private JFXTextField detEstadoPagoCita;
    @FXML    private JFXTextField detCitaEstado;
    @FXML    private JFXTextField detMedicoCita;
    @FXML    private JFXTextField detCitaFecha;
    @FXML    private JFXTextArea detAsuntoCita;
    @FXML    private JFXTextArea detMedicamentoCita;
    @FXML    private JFXTextArea detNotaCita;
    @FXML    private JFXTextArea detSintomasCita;
    @FXML    private JFXTextArea detDiagnosticoCita;
    //TAB PANE
    @FXML    private TabPane tabPaneCitass;
    @FXML    private Tab actualizarCita;
    @FXML    private Tab detalleCita;
    @FXML    private Tab nuevaCita;
    @FXML    private Tab listCitas;
    //ToggleGroup
    @FXML    private ToggleGroup GrupoGenero;
    @FXML    private ToggleGroup GrupoGenero11;
    @FXML    private ToggleGroup GrupoGenero2;
    @FXML    private ToggleGroup GrupoGenero1;
    //BUTTON
    @FXML    private Button view;
    @FXML    private Button btnUpdate;
    @FXML    private Button btnNew;
    @FXML    private Button btnEliminar;
    //LABEL
    @FXML    private Label detIdCita;
    @FXML    private Label lbIdCitaPaciente;
    @FXML    private Label lbIdCitaPaciente1;
    @FXML    private ComboBox<Pacientes> cmbCita;
    @FXML    private ComboBox<Pacientes> cmbCitaPaciente;
    //LABEL ERROR
    @FXML    private Label error_FechaCita;
    @FXML    private Label error_Sintomas;
    @FXML    private Label error_TelMedico;
    @FXML    private Label error_Apellido;
    @FXML    private Label error_Email;

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
        tblViewCitas.setItems(listaCita);
        //Enlazar columnas con atributos
        clmnCitasPaciente.setCellValueFactory(new PropertyValueFactory<Citas,Pacientes>("pacientes"));
        clmnCitasMedico.setCellValueFactory(new PropertyValueFactory<Citas,Medico>("medico"));
        clmnFecha.setCellValueFactory(new PropertyValueFactory<Citas,Date>("fechaa"));
        clmnNota.setCellValueFactory(new PropertyValueFactory<Citas,String>("nota"));
        clmnEstadoPago.setCellValueFactory(new PropertyValueFactory<Citas,String>("estadoPago"));
        clmnEstadoCita.setCellValueFactory(new PropertyValueFactory<Citas,String>("estadoCita"));
    }

    public void registroCita(MouseEvent mouseEvent) {
        if (listCitas.isSelected()){
            nuevaCita.setDisable(true);
            actualizarCita.setDisable(true);
            detalleCita.setDisable(true);
        }
    }

    public void consultarCitas(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String pacienteBuscar = txtSearchCita.getText().trim();
        listaCita.clear();
        if (txtSearchCita.getText().equals("")){
            setCellTable();
        }else {
            if (pacienteBuscar.length()>=1){//Realiza la busqueda solo si se ha escrito mas de 2 caracteres
                Connection con = DBConnection.getConnection();
                try {
                    Statement stnt = con.createStatement();
                    String sql = "SELECT C.idCita, C.asunto, C.fecha, C.nota, C.diagnostico, C.sintomas, C.medicamentos, C.estadoCita, C.estadoPago, " +
                                  "P.idPacientes, P.nombrePaciente, P.apellidoPaciente, P.telefono, M.idMedicos, M.nombreDoctor " +
                                  "FROM citas C INNER JOIN pacientes P ON (C.pacientes_idPacientes = P.idPacientes) INNER JOIN medicos M ON (P.medicos_idMedicos = M.idMedicos) WHERE P.nombrePaciente LIKE '%" + pacienteBuscar + "%'";
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

    public void updateCita(ActionEvent event) {
        citaSeleccionada = tblViewCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada == null){
            Error("No ha seleccionado un registro");
        }else {
            actualizarCita.setDisable(false);
            tabPaneCitass.getSelectionModel().select(actualizarCita);
            lbIdCitaPaciente.setText(String.valueOf(citaSeleccionada.getIdCita()));
            cmbCitaPaciente.setValue(citaSeleccionada.getPacientes());
            cmbCitaPaciente.getSelectionModel().getSelectedItem();
            asunto.setText(citaSeleccionada.getAsunto());
            fecha.setValue(citaSeleccionada.getFechaa().toLocalDate());
            nota.setText(citaSeleccionada.getNota());
            diagnostico.setText(citaSeleccionada.getDiagnostico());
            sintomas.setText(citaSeleccionada.getSintomas());
            medicamento.setText(citaSeleccionada.getMedicamentos());
            nombreMedico.setText(String.valueOf(citaSeleccionada.getMedico()));
            if (citaSeleccionada.getEstadoCita().equals("Pendiente")){
                rbtPendiente.setSelected(true);
                rbtTerminado.setSelected(false);
            }else if (citaSeleccionada.getEstadoCita().equals("Terminado")){
                rbtPendiente.setSelected(false);
                rbtTerminado.setSelected(true);
            }
            if (citaSeleccionada.getEstadoPago().equals("Pagado")){
                rbtPagado.setSelected(true);
                rbtNoPagado.setSelected(false);
            }else if (citaSeleccionada.getEstadoPago().equals("Pendiente")){
                rbtPagado.setSelected(false);
                rbtNoPagado.setSelected(true);
            }

        }
    }

    public void eliminarCita(ActionEvent event) {
        if(tblViewCitas.getSelectionModel().getSelectedItem() == null){
            Error("No ha seleccionado un Paciente");
        }else {
            if (Confirmacion("Confirma si quieres eliminar el Paciente seleccionado?")){
                try {
                    Citas.eliminarCita(tblViewCitas.getSelectionModel().getSelectedItem().getIdCita());
                    Informacion("Registro Eliminado");
                    tabPaneCitass.getSelectionModel().select(listCitas);
                    setCellTable();
                }catch (Exception e){
                    Error("Fallo al eliminar el registro");
                    e.printStackTrace();
                }
            }
        }
    }

    public void newCita(ActionEvent event) {
        citaSeleccionada = tblViewCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada == null){
            Error("No ha seleccionado registro");
        }else {
            nuevaCita.setDisable(false);
            tabPaneCitass.getSelectionModel().select(nuevaCita);
            cmbCita.setValue(citaSeleccionada.getPacientes());
            cmbCita.getSelectionModel().getSelectedItem();
            nombreCitaMedico.setText(String.valueOf(citaSeleccionada.getMedico()));

        }

    }

    public void viewCita(ActionEvent event) {
        citaSeleccionada = tblViewCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada == null){
            Error("No ha seleccionado un Registro");
        }else {
            detalleCita.setDisable(false);
            tabPaneCitass.getSelectionModel().select(detalleCita);
            detIdCita.setText(String.valueOf(citaSeleccionada.getPacientes()));
            detMedicoCita.setText(String.valueOf(citaSeleccionada.getMedico()));
            detAsuntoCita.setText(citaSeleccionada.getAsunto());
            detCitaFecha.setText(String.valueOf(citaSeleccionada.getFechaa()));
//            detCitaFecha.setValue(citaSeleccionada.getFechaa().toLocalDate());
            detNotaCita.setText(citaSeleccionada.getNota());
            detDiagnosticoCita.setText(citaSeleccionada.getDiagnostico());
            detSintomasCita.setText(citaSeleccionada.getSintomas());
            detMedicamentoCita.setText(citaSeleccionada.getMedicamentos());
            detEstadoPagoCita.setText(citaSeleccionada.getEstadoPago());
            detCitaEstado.setText(citaSeleccionada.getEstadoCita());

        }
    }


    public void btnGuardarCita() throws SQLException, ClassNotFoundException {
        boolean isIdentidadEmpty = TextFieldValidation.isTextAreaNotEmpty(sintomasCita, error_Sintomas,"Campo Requerido.");
        //boolean isNombreEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(citaFecha, error_FechaCita,"Nombre del Paciente Requerido.");
        //boolean isApellidoEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtApellidoPaciente, error_Apellido,"Apellido del Paciente Requerido.");
//        boolean isTelefonoEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtTelefonoMedico, error_TelMedico,"Telefono del Medico Requerido.");
//        boolean isDireccEmpty = validation.TextFieldValidation.isTextFieldNotEmpty(txtDireccionMedico, error_DirecMedico,"Direccion del Medico Requerida.");
        if (isIdentidadEmpty) {
            //Crear una nueva instancia del tipo Alumno
            Citas citas = new Citas(0,
                    asuntoCita.getText(),
                    Date.valueOf(citaFecha.getValue()),
                    notaCita.getText(),
                    diagnosticoCita.getText(),
                    sintomasCita.getText(),
                    medicamentoCita.getText(),
                    rbtCitaTerminado.isSelected() ? "Terminado" : "Pendiente",
                    rbtCitaPagado.isSelected() ? "Pagado" : "Pendiente",
                    cmbCita.getSelectionModel().getSelectedItem()
            );
            //Llamar al metodo guardarRegistro de la clase Alumno
            int resultado = citas.guardarCita(conexion.getConnection());

            if (resultado == 1) {
                listaCita.add(citas);
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Cita Agregada");
                mensaje.setContentText("El registro ha sido agregado exitosamente");
                mensaje.setHeaderText("Resultado:");
                mensaje.show();

            }
            tabPaneCitass.getSelectionModel().select(listCitas);
        }
    }

    public void cancelAction(ActionEvent event) {
        asuntoCita.setText(null);
        citaFecha.setValue(null);
        notaCita.setText(null);
        diagnosticoCita.setText(null);
        sintomasCita.setText(null);
        medicamentoCita.setText(null);
        rbtCitaTerminado.setSelected(false);
        rbtCitaPendiente.setSelected(false);
        rbtCitaPagado.setSelected(false);
        rbtCitaNoPagado.setSelected(false);
        cmbCita.setValue(null);
    }

    public void btnGuardarCitaActualizada() throws SQLException, ClassNotFoundException {
        Citas citas = new Citas(

                Integer.valueOf(lbIdCitaPaciente.getText()),
                asunto.getText(),
                Date.valueOf(fecha.getValue()),
                nota.getText(),
                diagnostico.getText(),
                sintomas.getText(),
                medicamento.getText(),
                rbtTerminado.isSelected()?"Terminado":"Pendiente",
                rbtPagado.isSelected()?"Pagado":"Pendiente",
                cmbCitaPaciente.getSelectionModel().getSelectedItem()
        );

        int resultado = citas.actualizarCita(conexion.getConnection());
        if (resultado == 1){
            listaCita.set(tblViewCitas.getSelectionModel().getSelectedIndex(),citas);
            Confirmacion("Cita  " + citaSeleccionada.getPacientes() + "  Actualizado");
            tabPaneCitass.getSelectionModel().select(listCitas);

        }
    }

    public void btnImprimir(ActionEvent event) {
    }

    private void Informacion(String informacion){
        Alert alarta = new Alert(Alert.AlertType.INFORMATION);
        alarta.setTitle("Informacion Cita");
        alarta.setHeaderText(null);
        alarta.setContentText(informacion);
        alarta.showAndWait();
    }
    private void Error(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Cita");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }
    private boolean Confirmacion(String confirmacion){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion Cita");
        alert.setHeaderText(null);
        alert.setContentText(confirmacion);
        Optional<ButtonType> opcao = alert.showAndWait();
        if (opcao.get() == ButtonType.OK)
            return true;
        return false;

    }

//    public void btnGuardar(ActionEvent event) throws SQLException, ClassNotFoundException {
//        Citas citas = new Citas(
//                lbIdCitaPaciente.getText(),
//                asunto.getText(),
//                Date.valueOf(fecha.getValue()),
//                nota.getText(),
//                diagnostico.getText(),
//                sintomas.getText(),
//                medicamentoCita.getText(),
//                rbtTerminado.isSelected()?"Terminado":"Pediente",
//                rbtPagado.isSelected()?"Pagado":"Pendiente",
//                cmbCitaPaciente.getSelectionModel().getSelectedItem()
//        );
//
//        int resultado = citas.actualizarCita(conexion.getConnection());
//        if (resultado == 1){
//            listaCita.set(tblViewCitas.getSelectionModel().getSelectedIndex(),citas);
//            Confirmacion("Cita  " + citaSeleccionada.getPacientes() + "  Actualizado");
//            tabPaneCitass.getSelectionModel().select(listCitas);
//
//        }
//    }
}
