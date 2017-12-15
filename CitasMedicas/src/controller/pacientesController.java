package controller;

<<<<<<< HEAD
=======
import com.jfoenix.controls.JFXComboBox;
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Medico;
import model.Pacientes;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

<<<<<<< HEAD
public class pacientesController implements Initializable{
    //TextField
    @FXML    private JFXTextField identidad;
    @FXML    private JFXTextField nombre;
    @FXML    private JFXTextField apellido;
    @FXML    private JFXTextField genero;
    @FXML    private JFXTextField telefono;
    @FXML    private JFXTextField email;
    @FXML    private JFXTextField txtSearch;
    @FXML    private JFXTextField direccion;
    @FXML    private JFXTextField nombrePaciente;
    @FXML    private JFXTextField telefonoPacient;
    @FXML    private JFXTextField apellidoPaciente;
    @FXML    private JFXTextField direccionPaciente;
    @FXML    private JFXTextField emailPacient;
    @FXML    private JFXTextField detMedico;
    @FXML    private JFXTextField detFecha;
    //DatePicker
    @FXML    private DatePicker fechaPacient;
    //TableView
    @FXML    private TableView<Pacientes> pacientes;
    @FXML    private TableColumn<Pacientes, String> idPacient;
    @FXML    private TableColumn<Pacientes, String> nombrePacient;
    @FXML    private TableColumn<Pacientes, String> apellidoPacient;
    @FXML    private TableColumn<Pacientes, Date> fechaPaciente;
    @FXML    private TableColumn<Pacientes, String> telefonoPaciente;
    @FXML    private TableColumn<Pacientes, String> emailPaciente;
    //TabPane
    @FXML    private TabPane tabPanePacientes;
    @FXML    private Tab listPacientes;
    @FXML    private Tab actualizarPaciente;
    @FXML    private Tab detallePaciente;
    //Button
    @FXML    private Button buscar;
    @FXML    private Button btnUpdate;
    @FXML    private Button btnNew;
    @FXML    private Button btnEliminar;
    @FXML    private Button btnPrint;
    //ComboBox
    @FXML    private ComboBox<Medico> cmbListMedico;
    //Label
    @FXML    private Label lbIdentidad;

    //RadioButton
    @FXML    private ToggleGroup GrupoGenero;
    @FXML    private JFXRadioButton rbtFemenino;
    @FXML    private JFXRadioButton rbtMasculino;
=======
public class pacientesController implements Initializable {
    //TextField
    @FXML
    private JFXTextField identidad;
    @FXML
    private JFXTextField nombre;
    @FXML
    private JFXTextField apellido;
    @FXML
    private JFXTextField genero;
    @FXML
    private JFXTextField telefono;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXTextField direccion;
    @FXML
    private JFXTextField nombrePaciente;
    @FXML
    private JFXTextField telefonoPacient;
    @FXML
    private JFXTextField apellidoPaciente;
    @FXML
    private JFXTextField direccionPaciente;
    @FXML
    private JFXTextField emailPacient;
    //DatePicker
    @FXML
    private DatePicker fecha;
    @FXML
    private DatePicker fechaPacient;
    //TableView
    @FXML
    private TableView<Pacientes> pacientes;
    @FXML
    private TableColumn<Pacientes, String> idPacient;
    @FXML
    private TableColumn<Pacientes, String> nombrePacient;
    @FXML
    private TableColumn<Pacientes, String> apellidoPacient;
    @FXML
    private TableColumn<Pacientes, Date> fechaPaciente;
    @FXML
    private TableColumn<Pacientes, String> telefonoPaciente;
    @FXML
    private TableColumn<Pacientes, String> emailPaciente;
    //TabPane
    @FXML
    private TabPane tabPanePacientes;
    @FXML
    private Tab listPacientes;
    @FXML
    private Tab actualizarPaciente;
    @FXML
    private Tab detallePaciente;
    //Button
    @FXML
    private Button buscar;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnEliminar;
    //ComboBox
    @FXML
    private ComboBox<Medico> cmbListMedico;
    //Label
    @FXML
    private Label lbIdentidad;

    //RadioButton
    @FXML
    private ToggleGroup GrupoGenero;
    @FXML
    private JFXRadioButton rbtFemenino;
    @FXML
    private JFXRadioButton rbtMasculino;
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92

    //Colecciones
    private ObservableList<Medico> listaMedicos;
    private ObservableList<Pacientes> listaPacientes;

    private DBConnection conexion;
    private Pacientes pacienteSeleccionado;
<<<<<<< HEAD
//    private Medico ;
=======
    private Medico medicoSeleccionado;
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellTable();
    }
<<<<<<< HEAD
    private void setCellTable(){
=======

    private void setCellTable() {
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
        conexion = new DBConnection();
//        conexion.establecerConexion();
        //Inicializar listas
        listaMedicos = FXCollections.observableArrayList();
        listaPacientes = FXCollections.observableArrayList();
        //Llenar listas
        try {
<<<<<<< HEAD
=======
//            Medico.llenarInformacion(conexion.getConnection(), listaMedicos);
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
            Medico.llenarInformacionMedicos(conexion.getConnection(), listaMedicos);
            Pacientes.llenarInformacionPacientes(conexion.getConnection(), listaPacientes);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Enlazar Listas con ComboBox y TableView
        pacientes.setItems(listaPacientes);
        cmbListMedico.setItems(listaMedicos);
        //Enlazar columnas con atributos
<<<<<<< HEAD
        idPacient.setCellValueFactory(new PropertyValueFactory<Pacientes,String>("idPacientes"));
        nombrePacient.setCellValueFactory(new PropertyValueFactory<Pacientes,String>("nombrePaciente"));
        apellidoPacient.setCellValueFactory(new PropertyValueFactory<Pacientes,String>("apellidoPaciente"));
        fechaPaciente.setCellValueFactory(new PropertyValueFactory<Pacientes,Date>("fechaNacimiento"));
        telefonoPaciente.setCellValueFactory(new PropertyValueFactory<Pacientes,String>("telefono"));
        emailPaciente.setCellValueFactory(new PropertyValueFactory<Pacientes,String>("email"));
    }

    public void registrosPacientes(MouseEvent mouseEvent) {
        if (listPacientes.isSelected()){
=======
        idPacient.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("idPacientes"));
        nombrePacient.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("nombrePaciente"));
        apellidoPacient.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("apellidoPaciente"));
        fechaPaciente.setCellValueFactory(new PropertyValueFactory<Pacientes, Date>("fechaNacimiento"));
        telefonoPaciente.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("telefono"));
        emailPaciente.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("email"));
    }

    public void registrosPacientes(MouseEvent mouseEvent) {
        if (listPacientes.isSelected()) {
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
            actualizarPaciente.setDisable(true);
            detallePaciente.setDisable(true);
        }
    }

    public void updatePaciente(ActionEvent event) {
        pacienteSeleccionado = pacientes.getSelectionModel().getSelectedItem();
<<<<<<< HEAD
        if (pacienteSeleccionado == null){
            exibirError("No ha seleccionado un registro");
        }else {
=======
        if (pacienteSeleccionado == null) {
            exibirError("No ha seleccionado un registro");
        } else {
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
            actualizarPaciente.setDisable(false);
            tabPanePacientes.getSelectionModel().select(actualizarPaciente);
            lbIdentidad.setText(pacienteSeleccionado.getIdPacientes());
            nombrePaciente.setText(pacienteSeleccionado.getNombrePaciente());
            apellidoPaciente.setText(pacienteSeleccionado.getApellidoPaciente());
<<<<<<< HEAD
            if (pacienteSeleccionado.getGenero().equals("Femenino")){
                rbtFemenino.setSelected(true);
                rbtMasculino.setSelected(false);
            }else if (pacienteSeleccionado.getGenero().equals("Masculino")){
=======
            if (pacienteSeleccionado.getGenero().equals("Femenino")) {
                rbtFemenino.setSelected(true);
                rbtMasculino.setSelected(false);
            } else if (pacienteSeleccionado.getGenero().equals("Masculino")) {
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
                rbtFemenino.setSelected(false);
                rbtMasculino.setSelected(true);
            }
            fechaPacient.setValue(pacienteSeleccionado.getFechaNacimiento().toLocalDate());
            direccionPaciente.setText(pacienteSeleccionado.getDireccion());
            emailPacient.setText(pacienteSeleccionado.getEmail());
            telefonoPacient.setText(pacienteSeleccionado.getTelefono());
            cmbListMedico.setValue(pacienteSeleccionado.getMedico());
            cmbListMedico.getSelectionModel().getSelectedItem();
<<<<<<< HEAD
        }
    }

    public void eliminarPaciente(ActionEvent event) {
        if(pacientes.getSelectionModel().getSelectedItem() == null){
            exibirError("No ha seleccionado un Paciente");
        }else {
            if (exibirDialogoConfirmacion("Confirma si quieres eliminar el Paciente seleccionado?")){
                try {
                    Pacientes.eliminarPaciente(pacientes.getSelectionModel().getSelectedItem().getIdPacientes());
                    exibirInformacion("Registro Eliminado");
                    tabPanePacientes.getSelectionModel().select(listPacientes);
                    setCellTable();
                }catch (Exception e){
                    exibirError("Fallo al eliminar el registro");
                    e.printStackTrace();
                }
            }
        }
    }

    public void newPaciente(ActionEvent event) {
        Stage paciente = new Stage();
        paciente.setResizable(false);
        try {
            btnNew.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../view/newPaciente.fxml"));
            paciente.initModality(Modality.APPLICATION_MODAL);
            paciente.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            paciente.setScene(scene);
            paciente.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void newCita(ActionEvent event) {
        Stage paciente = new Stage();
        paciente.setResizable(false);
        try {
//            btnNew.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../view/newCita.fxml"));
            paciente.initModality(Modality.APPLICATION_MODAL);
            paciente.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            paciente.setScene(scene);
            paciente.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void consultarPaciente(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String pacienteBuscar = txtSearch.getText().trim();
        listaPacientes.clear();
        if (txtSearch.getText().equals("")){
            setCellTable();
        }else {
            if (pacienteBuscar.length()>=1){//Realiza la busqueda solo si se ha escrito mas de 2 caracteres
                Connection con = DBConnection.getConnection();
                try {
                    Statement stnt = con.createStatement();
                    String sql = "SELECT P.idPacientes, P.nombrePaciente, P.apellidoPaciente, P.genero, P.fechaNacimiento, P.direccion, P.email, P.telefono, M.idMedicos, M.nombreDoctor, M.apellidoDoctor FROM pacientes P INNER JOIN medicos M ON (P.medicos_idMedicos = M.idMedicos) WHERE P.nombrePaciente LIKE '%" + pacienteBuscar + "%'";
                    ResultSet resultado = stnt.executeQuery(sql);
                    while (resultado.next()){
                        listaPacientes.add(
                                new Pacientes(
                                        resultado.getString("idPacientes"),
                                        resultado.getString("nombrePaciente"),
                                        resultado.getString("apellidoPaciente"),
                                        resultado.getString("genero"),
                                        resultado.getDate("fechaNacimiento"),
                                        resultado.getString("direccion"),
                                        resultado.getString("email"),
                                        resultado.getString("telefono"),
                                        new Medico(resultado.getString("idMedicos"),
                                                resultado.getString("nombreDoctor")
                                        )
                                )
                        );

                    }
                    resultado.close();
                } catch (SQLException e) {
//                lblError.setText(e.getMessage());
=======
        }
    }

    public void eliminarPaciente(ActionEvent event) {
        if (pacientes.getSelectionModel().getSelectedItem() == null) {
            exibirError("No ha seleccionado un Paciente");
        } else {
            if (exibirDialogoConfirmacion("Confirma si quieres eliminar el Paciente seleccionado?")) {
                try {
                    Pacientes.eliminarPaciente(pacientes.getSelectionModel().getSelectedItem().getIdPacientes());
                    exibirInformacion("Registro Eliminado");
                    tabPanePacientes.getSelectionModel().select(listPacientes);
                    setCellTable();
                } catch (Exception e) {
                    exibirError("Fallo al eliminar el registro");
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
                    e.printStackTrace();
                }
            }

        }
    }
<<<<<<< HEAD

    public void viewPaciente(ActionEvent event) {
        pacienteSeleccionado = pacientes.getSelectionModel().getSelectedItem();
        if ( pacienteSeleccionado == null){
            exibirError("No ha seleccionado un Registro");
        }else {
            detallePaciente.setDisable(false);
            tabPanePacientes.getSelectionModel().select(detallePaciente);
            identidad.setText(pacienteSeleccionado.getIdPacientes());
            nombre.setText(pacienteSeleccionado.getNombrePaciente());
            apellido.setText(pacienteSeleccionado.getApellidoPaciente());
            genero.setText(pacienteSeleccionado.getGenero());
            detFecha.setText(String.valueOf(pacienteSeleccionado.getFechaNacimiento()));
            direccion.setText(pacienteSeleccionado.getDireccion());
            email.setText(pacienteSeleccionado.getEmail());
            telefono.setText(pacienteSeleccionado.getTelefono());
            detMedico.setText(String.valueOf(pacienteSeleccionado.getMedico()));

        }
    }

    public void btnGuardarPacienteActualizado() throws SQLException, ClassNotFoundException {
        Pacientes p;
        p = new Pacientes(
                lbIdentidad.getText(),
                nombrePaciente.getText(),
                apellidoPaciente.getText(),
                rbtFemenino.isSelected()?"Femenino":"Masculino",
                Date.valueOf(fechaPacient.getValue()),
                direccionPaciente.getText(),
                emailPacient.getText(),
                telefonoPacient.getText(),
                cmbListMedico.getSelectionModel().getSelectedItem());
//        conexion.establecerConexion();
        int resultado = p.actualizarPaciente(conexion.getConnection());
//        conexion.cerrarConexion();

        if (resultado == 1){
            listaPacientes.set(pacientes.getSelectionModel().getSelectedIndex(),p);
            exibirDialogoConfirmacion("Medico  " + pacienteSeleccionado.getNombrePaciente() + "  Actualizado");
            tabPanePacientes.getSelectionModel().select(listPacientes);

        }
    }

    private void exibirInformacion(String informacion){
        Alert alarta = new Alert(Alert.AlertType.INFORMATION);
        alarta.setTitle("Informacion Paciente");
        alarta.setHeaderText(null);
        alarta.setContentText(informacion);
        alarta.showAndWait();
    }
    private void exibirError(String error){
=======

    public void newPaciente(ActionEvent event) {
        Stage paciente = new Stage();
        paciente.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/newPaciente.fxml"));
            paciente.initModality(Modality.APPLICATION_MODAL);
            paciente.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            paciente.setScene(scene);
            paciente.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void consultarPaciente(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String pacienteBuscar = txtSearch.getText().trim();
        listaPacientes.clear();
        if (txtSearch.getText().equals("")) {
            setCellTable();
        } else {
            if (pacienteBuscar.length() >= 1) {//Realiza la busqueda solo si se ha escrito mas de 2 caracteres
                Connection con = DBConnection.getConnection();
                try {
                    Statement stnt = con.createStatement();
                    String sql = "SELECT P.idPacientes, P.nombrePaciente, P.apellidoPaciente, P.genero, P.fechaNacimiento, P.direccion, P.email, P.telefono, M.idMedicos, M.nombreDoctor, M.apellidoDoctor FROM pacientes P INNER JOIN medicos M ON (P.medicos_idMedicos = M.idMedicos) WHERE P.nombrePaciente LIKE '%" + pacienteBuscar + "%'";
                    ResultSet resultado = stnt.executeQuery(sql);
                    while (resultado.next()) {
                        listaPacientes.add(
                                new Pacientes(
                                        resultado.getString("idPacientes"),
                                        resultado.getString("nombrePaciente"),
                                        resultado.getString("apellidoPaciente"),
                                        resultado.getString("genero"),
                                        resultado.getDate("fechaNacimiento"),
                                        resultado.getString("direccion"),
                                        resultado.getString("email"),
                                        resultado.getString("telefono"),
                                        new Medico(resultado.getString("idMedicos"),
                                                resultado.getString("nombreDoctor")
                                        )
                                )
                        );

                    }
                    resultado.close();
                } catch (SQLException e) {
//                lblError.setText(e.getMessage());
                    e.printStackTrace();
                }
            }

        }
    }

    public void viewPaciente(ActionEvent event) {
    }

    public void btnGuardarPacienteActualizado() throws SQLException, ClassNotFoundException {
        Pacientes p;
        p = new Pacientes(
                lbIdentidad.getText(),
                nombrePaciente.getText(),
                apellidoPaciente.getText(),
                rbtFemenino.isSelected() ? "Femenino" : "Masculino",
                Date.valueOf(fechaPacient.getValue()),
                direccionPaciente.getText(),
                emailPacient.getText(),
                telefonoPacient.getText(),
                cmbListMedico.getSelectionModel().getSelectedItem());
//        conexion.establecerConexion();
        int resultado = p.actualizarPaciente(conexion.getConnection());
//        conexion.cerrarConexion();

        if (resultado == 1) {
            listaPacientes.set(pacientes.getSelectionModel().getSelectedIndex(), p);
            exibirDialogoConfirmacion("Medico  " + pacienteSeleccionado.getNombrePaciente() + "  Actualizado");
            tabPanePacientes.getSelectionModel().select(listPacientes);

        }
    }

    private void exibirInformacion(String informacion) {
        Alert alarta = new Alert(Alert.AlertType.INFORMATION);
        alarta.setTitle("Informacion Paciente");
        alarta.setHeaderText(null);
        alarta.setContentText(informacion);
        alarta.showAndWait();
    }

    private void exibirError(String error) {
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Paciente");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }

    private boolean exibirDialogoConfirmacion(String confirmacion) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion Paciente");
        alert.setHeaderText(null);
        alert.setContentText(confirmacion);
        Optional<ButtonType> opcao = alert.showAndWait();
        if (opcao.get() == ButtonType.OK)
            return true;

        return false;

    }
<<<<<<< HEAD

=======
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
}
