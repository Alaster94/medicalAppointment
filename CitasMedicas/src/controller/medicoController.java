package controller;

import com.jfoenix.controls.JFXTextField;
import dba.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import model.Areas;
import model.Medico;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class medicoController implements Initializable {
    //TextField
    @FXML
    private JFXTextField txtSearchMedico;
    @FXML
    private JFXTextField detArea;
    @FXML
    private JFXTextField areMedico;
    @FXML
    private JFXTextField telMedico;
    @FXML
    private JFXTextField emaMedico;
    @FXML
    private JFXTextField nombMedico;
    @FXML
    private JFXTextField direcMedico;
    @FXML
    private JFXTextField detIdentidad;
    @FXML
    private JFXTextField detApellido;
    @FXML
    private JFXTextField detDireccion;
    @FXML
    private JFXTextField detNombre;
    @FXML
    private JFXTextField detTelefono;
    @FXML
    private JFXTextField detEmail;
    @FXML
    private JFXTextField apelliMedico;
    //Table
    @FXML
    private TableView<Medico> medicos;
    @FXML
    private TableColumn<Medico, String> nombreMedico;
    @FXML
    private TableColumn<Medico, String> emailMedico;
    @FXML
    private TableColumn<Medico, String> telefonoMedico;
    @FXML
    private TableColumn<Medico, String> apellidoMedico;
    @FXML
    private TableColumn<Medico, String> idMedico;
    @FXML
    private TableColumn<Medico, String> areaMedico;
    //TabPane
    @FXML
    private Tab actualizarMedico;
    @FXML
    private TabPane tabPaneMedicos;
    @FXML
    private Tab listMedicos;
    @FXML
    private Tab detalleMedico;
    //Button
    @FXML
    private Button view;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnNew;
    @FXML
    private Button buscar;
    @FXML
    private Button btnEliminar;
    //ComboBox
//    @FXML    private JFXComboBox<Areas> cmbAreMedico;
    @FXML
    private ComboBox<Areas> cmbAreMedico;
    //Label
    @FXML
    private Label lbIdentidadMedico;
    //Colecciones
    private ObservableList<Areas> listaAreas;
    private ObservableList<Medico> listaMedicos;

    private DBConnection conexion;

    private Medico dao;
    private Medico medicoSeleccionado;
    private Medico pacienteDetalle;

    private Connection connection;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    private Medico medico;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellTable();

    }

    private void setCellTable() {
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
        medicos.setItems(listaMedicos);
        cmbAreMedico.setItems(listaAreas);
        //Enlazar columnas con atributos
        idMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("idMedicos"));
        nombreMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("nombreDoctor"));
        apellidoMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("apellidoDoctor"));
        areaMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("areas"));

        emailMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("emailDoctor"));
        telefonoMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("telefonoDoctor"));

        gestionarEventos();
//        conexion.cerrarConexion();
    }

    public void gestionarEventos() {
        medicos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Medico>() {
            @Override
            public void changed(ObservableValue<? extends Medico> arg0,
                                Medico valorAnterior, Medico valorSeleccionado) {
                if (valorSeleccionado != null) {

                }

            }
        });
    }

    public void registrosPacientes(MouseEvent mouseEvent) {
        if (listMedicos.isSelected()) {
            actualizarMedico.setDisable(true);
            detalleMedico.setDisable(true);
        }
    }

    public void updateMedico(ActionEvent event) {
        medicoSeleccionado = medicos.getSelectionModel().getSelectedItem();
        if (medicoSeleccionado == null) {
            exibirDialogoError("No ha seleccionado un registro");
        } else {
            actualizarMedico.setDisable(false);
            tabPaneMedicos.getSelectionModel().select(actualizarMedico);
            lbIdentidadMedico.setText(medicoSeleccionado.getIdMedicos());
            nombMedico.setText(medicoSeleccionado.getNombreDoctor());
            apelliMedico.setText(medicoSeleccionado.getApellidoDoctor());
            direcMedico.setText(medicoSeleccionado.getDireccionDoctor());
            emaMedico.setText(medicoSeleccionado.getEmailDoctor());
            telMedico.setText(medicoSeleccionado.getTelefonoDoctor());
            cmbAreMedico.setValue(medicoSeleccionado.getAreas());
            cmbAreMedico.getSelectionModel().getSelectedItem();
        }

    }

    public void eliminarMedico() {
        if (medicos.getSelectionModel().getSelectedItem() == null) {
            exibirDialogoError("No ha seleccionado un Registro");
        } else {
            if (exibirDialogoConfirmacion("Confirma si quieres eliminar el registro seleccionado?")) {
                try {
                    Medico.eliminarRegistro(medicos.getSelectionModel().getSelectedItem().getIdMedicos());
                    exibirDialogoInformacao("Registro Eliminado");
                    tabPaneMedicos.getSelectionModel().select(listMedicos);
                    setCellTable();
                } catch (Exception e) {
                    exibirDialogoError("Fallo al eliminar el registro");
                    e.printStackTrace();
                }
            }
        }
    }

    public void newMedico(ActionEvent event) {
        Stage medicos = new Stage();
        medicos.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/newMedico.fxml"));
            medicos.initModality(Modality.APPLICATION_MODAL);
            medicos.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            medicos.setScene(scene);
            medicos.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void consultar(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        String nombreBusqueda = txtSearchMedico.getText().trim();
        listaMedicos.clear();
        if (txtSearchMedico.getText().equals("")) {
            setCellTable();
        } else {
            if (nombreBusqueda.length() >= 1) {//Realiza la busqueda solo si se ha escrito mas de 2 caracteres

                Connection con = DBConnection.getConnection();
                try {
                    Statement stnt = con.createStatement();
                    String sql = "SELECT M.idMedicos, M.nombreDoctor,M.apellidoDoctor,M.direccion, M.telefono, M.email, A.idAreas, A.nombreArea FROM medicos M INNER JOIN areas A ON (M.areas_idAreas = A.idAreas) WHERE nombreDoctor LIKE '%" + nombreBusqueda + "%'";
                    ResultSet resultado = stnt.executeQuery(sql);
                    while (resultado.next()) {
                        listaMedicos.add(
                                new Medico(
                                        resultado.getString("idMedicos"),
                                        resultado.getString("nombreDoctor"),
                                        resultado.getString("apellidoDoctor"),
                                        resultado.getString("direccion"),
                                        resultado.getString("telefono"),
                                        resultado.getString("email"),
                                        new Areas(resultado.getInt("idAreas"),
                                                resultado.getString("nombreArea")
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
        medicoSeleccionado = medicos.getSelectionModel().getSelectedItem();
        if (medicoSeleccionado == null) {
            exibirDialogoError("No ha seleccionado registro");
        } else {
            detalleMedico.setDisable(false);
            tabPaneMedicos.getSelectionModel().select(detalleMedico);
            detIdentidad.setText(medicoSeleccionado.getIdMedicos());
            detNombre.setText(medicoSeleccionado.getNombreDoctor());
            detApellido.setText(medicoSeleccionado.getApellidoDoctor());
            detDireccion.setText(medicoSeleccionado.getDireccionDoctor());
            detEmail.setText(medicoSeleccionado.getEmailDoctor());
            detTelefono.setText(medicoSeleccionado.getTelefonoDoctor());
            detArea.setText(String.valueOf(medicoSeleccionado.getAreas()));

        }
    }

    @FXML
    public void btnGuardarPacienteActualizado() throws SQLException, ClassNotFoundException {
        Medico a;
        a = new Medico(
                lbIdentidadMedico.getText(),
                nombMedico.getText(),
                apelliMedico.getText(),
                direcMedico.getText(),
                emaMedico.getText(),
                telMedico.getText(),
                cmbAreMedico.getSelectionModel().getSelectedItem());
//        conexion.establecerConexion();
        int resultado = a.actualizarRegistro(conexion.getConnection());
//        conexion.cerrarConexion();

        if (resultado == 1) {
            listaMedicos.set(medicos.getSelectionModel().getSelectedIndex(), a);
            exibirDialogoConfirmacion("Medico  " + medicoSeleccionado.getNombreDoctor() + "  Actualizado");
            tabPaneMedicos.getSelectionModel().select(listMedicos);

        }
    }

    private void exibirDialogoInformacao(String informacion) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);
        alert.setContentText(informacion);
        alert.showAndWait();
    }

    private void exibirDialogoError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }

    private boolean exibirDialogoConfirmacion(String confirmacion) {
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
