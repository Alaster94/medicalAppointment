package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.listPacientes;
import model.pacientesModel;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class pacientesController implements Initializable{
    @FXML    private JFXTextField identidad;
    @FXML    private JFXTextField nombre;
    @FXML    private JFXTextField apellido;
    @FXML    private JFXTextField genero;
    @FXML    private JFXTextField telefono;
    @FXML    private JFXTextField email;
    @FXML    private DatePicker fecha;
    @FXML    private JFXTextField direccion;
    @FXML    private Label lbIdentidad;
    @FXML    private JFXTextField nombrePaciente;
    @FXML    private JFXTextField telefonoPacient;
    @FXML    private JFXTextField generoPaciente;
    @FXML    private JFXTextField apellidoPaciente;
    @FXML    private DatePicker fechaPacient;
    @FXML    private JFXTextField direccionPaciente;
    @FXML    private JFXTextField emailPacient;
    @FXML    private Button buscar;
    @FXML    private Button btnUpdate;
    @FXML    private Tab listPacientes;
    @FXML    private Tab actualizarPaciente;
    @FXML    private Button btnNew;
    @FXML    private TabPane tabPanePacientes;
    @FXML    private Tab detallePaciente;
    @FXML    private Button btnEliminar;
    @FXML    private JFXTextField txtSearch;
    @FXML    private TableView<listPacientes> pacientes;
    @FXML    private TableColumn<listPacientes, String> idPacient;
    @FXML    private TableColumn<listPacientes, String> nombrePacient;
    @FXML    private TableColumn<listPacientes, String> apellidoPacient;
    @FXML    private TableColumn<listPacientes, Date> fechaPaciente;
    @FXML    private TableColumn<listPacientes, String> telefonoPaciente;
    @FXML    private TableColumn<listPacientes, String> emailPaciente;
    private pacientesModel dao;
    private model.listPacientes pacienteSeleccionado;
    private model.listPacientes pacienteDetalle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setCellTable();
        loadDataFromDatabase();


    }
    public void registrosPacientes(MouseEvent mouseEvent) {
        if (listPacientes.isSelected()){
            actualizarPaciente.setDisable(true);
            detallePaciente.setDisable(true);
//            limpiarCadastroActualizacaoFuncionario();
        }
    }
    private void setCellTable(){
        try {
            dao = new pacientesModel();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        idPacient.setCellValueFactory(new PropertyValueFactory<>("identidad"));
        nombrePacient.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoPacient.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        fechaPaciente.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        telefonoPaciente.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        emailPaciente.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
    private void loadDataFromDatabase(){
        try {
            List<listPacientes> resultado = dao.consultar();
            pacientes.setItems(FXCollections.observableList(resultado));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void consultarPacientes() {
        try {
            List<listPacientes> resultado = dao.buscar(txtSearch.getText());

            if (resultado.isEmpty()){
                exibirDialogoInformacao("Paciente no Encontrado");
            }else {
                pacientes.setItems(FXCollections.observableList(resultado));
            }

        }catch (Exception e){
            exibirDialogoInformacao("Fallo al realizar la busqueda");
            e.printStackTrace();

        }

    }


    public void updatePaciente(ActionEvent event) {
        pacienteSeleccionado = pacientes.getSelectionModel().getSelectedItem();
        if (pacienteSeleccionado == null){
            exibirDialogoError("No ha seleccionado un registro");
        }else {
            actualizarPaciente.setDisable(false);
            tabPanePacientes.getSelectionModel().select(actualizarPaciente);
            lbIdentidad.setText(pacienteSeleccionado.getIdentidad());
            nombrePaciente.setText(pacienteSeleccionado.getNombre());
            apellidoPaciente.setText(pacienteSeleccionado.getApellido());
            generoPaciente.setText(pacienteSeleccionado.getGenero());
            fechaPacient.setValue(pacienteSeleccionado.getFecha().toLocalDate());
            direccionPaciente.setText(pacienteSeleccionado.getDireccion());
            emailPacient.setText(pacienteSeleccionado.getEmail());
            telefonoPacient.setText(pacienteSeleccionado.getTelefono());
        }
    }

    public void btnGuardarPacienteActualizado() {
        pacienteSeleccionado.setNombre(nombrePaciente.getText());
        pacienteSeleccionado.setApellido(apellidoPaciente.getText());
        pacienteSeleccionado.setGenero(generoPaciente.getText());
        pacienteSeleccionado.setFecha(Date.valueOf(fechaPacient.getValue()));
        pacienteSeleccionado.setDireccion(direccionPaciente.getText());
        pacienteSeleccionado.setEmail(emailPacient.getText());
        pacienteSeleccionado.setTelefono(telefonoPacient.getText());
        try {
            dao.actualizar(pacienteSeleccionado);
            exibirDialogoConfirmacion("Paciente  " + pacienteSeleccionado.getNombre() + "  Actualizado");
            tabPanePacientes.getSelectionModel().select(listPacientes);
            loadDataFromDatabase();
        } catch (SQLException e) {
            exibirDialogoError("Error al actualizar el registro");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void eliminarPaciente(ActionEvent event) {
        if(pacientes.getSelectionModel().getSelectedItem() == null){
            exibirDialogoError("No ha seleccionado un Registro");
        }else {
            if (exibirDialogoConfirmacion("Confirma si quieres eliminar el registro seleccionado?")){
                try {
                    dao.eliminar(pacientes.getSelectionModel().getSelectedItem().getIdentidad());
                    exibirDialogoInformacao("Registro Eliminado");
                    loadDataFromDatabase();
                }catch (Exception e){
                    exibirDialogoError("Fallo al eliminar el registro");
                    e.printStackTrace();
                }
            }
        }
    }
    public void viewPaciente(ActionEvent event) {
        pacienteDetalle = pacientes.getSelectionModel().getSelectedItem();
        if (pacienteDetalle == null){
            exibirDialogoError("No ha seleccionado un registro");
        }else {
            detallePaciente.setDisable(false);
            tabPanePacientes.getSelectionModel().select(detallePaciente);
            identidad.setText(pacienteDetalle.getIdentidad());
            nombre.setText(pacienteDetalle.getNombre());
            apellido.setText(pacienteDetalle.getApellido());
            genero.setText(pacienteDetalle.getGenero());
            fecha.setValue(pacienteDetalle.getFecha().toLocalDate());
            direccion.setText(pacienteDetalle.getDireccion());
            email.setText(pacienteDetalle.getEmail());
            telefono.setText(pacienteDetalle.getTelefono());
        }

    }

    public void newPaciente(ActionEvent event) {
        Stage pacientes = new Stage();
        pacientes.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/newPaciente.fxml"));
            pacientes.initModality(Modality.APPLICATION_MODAL);
            pacientes.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            pacientes.setScene(scene);
            pacientes.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exibirDialogoInformacao(String informacion){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);
        alert.setContentText(informacion);
        alert.showAndWait();
    }
    private void exibirDialogoError(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }
    private boolean exibirDialogoConfirmacion(String confirmacion){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion");
        alert.setHeaderText(null);
        alert.setContentText(confirmacion);
        Optional<ButtonType> opcao = alert.showAndWait();
        if (opcao.get() == ButtonType.OK)
            return true;

        return false;

    }


}
