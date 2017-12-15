package controller;

import com.jfoenix.controls.JFXPasswordField;
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
import model.TipoUsuario;
import model.Usuarios;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class usuarioController implements Initializable {
    //TextField
    @FXML    private JFXTextField detPrivilegio;
    @FXML    private JFXTextField txtApellidoUsuario;
    @FXML    private JFXTextField detApellido;
    @FXML    private JFXTextField txtNombreUsuario;
    @FXML    private JFXTextField detFecha;
    @FXML    private JFXTextField detDireccion;
    @FXML    private JFXTextField detIdUsuario;
    @FXML    private JFXTextField txtDireccionUsuario;
    @FXML    private JFXTextField txtUsuario;
    @FXML    private JFXTextField detNombre;
    @FXML    private JFXTextField detTelefono;
    @FXML    private JFXTextField txtTelefonoUsuario;
    @FXML    private JFXTextField detEmail;
    @FXML    private JFXTextField txtEmailUsuario;
    @FXML    private JFXTextField detEstado;
    @FXML    private JFXPasswordField psPassword;
    @FXML    private JFXTextField txtIdUsuario;
    @FXML    private JFXTextField detNombreUsuario;
    @FXML    private JFXTextField txtSearchUsuario;
<<<<<<< HEAD
=======

>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
    //Table
    @FXML    private TableView<Usuarios> usuarios;
    @FXML    private TableColumn<Usuarios, String> idUsuario;
    @FXML    private TableColumn<Usuarios, String> nombreUsuario;
    @FXML    private TableColumn<Usuarios, String> apellidoUsuario;
    @FXML    private TableColumn<Usuarios, String> telefonoUsuario;
    @FXML    private TableColumn<Usuarios, String> emailUsuario;
    @FXML    private TableColumn<Usuarios, String> tipoUsuario;
    //TabPane
    @FXML    private TabPane tabPaneUsuarios;
    @FXML    private Tab listUsuarios;
    @FXML    private Tab actualizarUsuario;
    @FXML    private Tab detalleUsuario;
    //Button
    @FXML    private Button btnUpdate;
    @FXML    private Button view;
    @FXML    private Button btnPrint;
    @FXML    private Button btnNew;
    @FXML    private Button btnEliminar;
    //ComboBox
    @FXML    private ComboBox<TipoUsuario> cmbPrivilegioUsuario;
    //RadioButton
    @FXML    private JFXRadioButton rbtActivo;
    @FXML    private JFXRadioButton rbtInactivo;
    @FXML    private ToggleGroup GrupoGenero;
    //Label
    @FXML    private Label lbIdentidadMedico1;
    @FXML    private Label lbIdentidadMedico;
    //DatePicker
    @FXML    private DatePicker dtpFecha;
    //Colecciones
    private ObservableList<TipoUsuario> listaTipoUsuario;
    private ObservableList<Usuarios> listaUsuario;
    private DBConnection conexion;
    private Usuarios usuarioSeleccionado;
<<<<<<< HEAD
=======
    private Medico pacienteDetalle;
    private Connection connection;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellTable();
    }
    private void setCellTable(){
        conexion = new DBConnection();
        //Inicializar listas
        listaTipoUsuario = FXCollections.observableArrayList();
        listaUsuario = FXCollections.observableArrayList();
        //Llenar listas
        try {
            TipoUsuario.llenarInformacionTipo(conexion.getConnection(), listaTipoUsuario);
            Usuarios.llenarInformacionUsuarios(conexion.getConnection(), listaUsuario);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Enlazar Listas con ComboBox y TableView
        usuarios.setItems(listaUsuario);
        cmbPrivilegioUsuario.setItems(listaTipoUsuario);
        //Enlazar columnas con atributos
        idUsuario.setCellValueFactory(new PropertyValueFactory<Usuarios,String>("idUsuarios"));
        nombreUsuario.setCellValueFactory(new PropertyValueFactory<Usuarios,String>("nombres"));
        apellidoUsuario.setCellValueFactory(new PropertyValueFactory<Usuarios,String>("apellidos"));
        tipoUsuario.setCellValueFactory(new PropertyValueFactory<Usuarios,String>("tipoUsuario"));
        telefonoUsuario.setCellValueFactory(new PropertyValueFactory<Usuarios,String>("telefonoo"));
        emailUsuario.setCellValueFactory(new PropertyValueFactory<Usuarios,String>("emaill"));
    }

    public void registrosUsuarios(MouseEvent mouseEvent) {
        if (listUsuarios.isSelected()){
            actualizarUsuario.setDisable(true);
            detalleUsuario.setDisable(true);
        }
    }

    public void consultarUsuarios(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String usuarioBuscar = txtSearchUsuario.getText().trim();
        listaUsuario.clear();
        if (txtSearchUsuario.getText().equals("")){
            setCellTable();
        }else {
            if (usuarioBuscar.length()>=1){//Realiza la busqueda solo si se ha escrito mas de 2 caracteres

                Connection con = DBConnection.getConnection();
                try {
                    Statement stnt = con.createStatement();
                    String sql = "SELECT U.idUsuarios, U.nombres, U.apellidos, U.birthDate, U.telefono, U.direccion, U.email, U.usuario, U.password, U.estado, T.id, T.nombre FROM usuarios U INNER JOIN tipousuario T ON (U.tipoUsuario_id = T.id) WHERE U.nombres LIKE '%" + usuarioBuscar + "%'";
                    ResultSet resultado = stnt.executeQuery(sql);
                    while (resultado.next()){
                        listaUsuario.add(
                                new Usuarios(
                                        resultado.getString("idUsuarios"),
                                        resultado.getString("nombres"),
                                        resultado.getString("apellidos"),
                                        resultado.getDate("birthDate"),
                                        resultado.getString("telefono"),
                                        resultado.getString("direccion"),
                                        resultado.getString("email"),
                                        resultado.getString("usuario"),
                                        resultado.getString("password"),
                                        resultado.getString("estado"),
                                        new TipoUsuario(resultado.getInt("id"),
                                                resultado.getString("nombre")
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
    public void updateUsuario(ActionEvent event) {
        usuarioSeleccionado = usuarios.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado == null){
            exibirError("No ha seleccionado un registro");
        }else {
            actualizarUsuario.setDisable(false);
            tabPaneUsuarios.getSelectionModel().select(actualizarUsuario);
            txtIdUsuario.setText(usuarioSeleccionado.getIdUsuarios());
            txtNombreUsuario.setText(usuarioSeleccionado.getNombres());
            txtApellidoUsuario.setText(usuarioSeleccionado.getApellidos());
            dtpFecha.setValue(usuarioSeleccionado.getBirthDate().toLocalDate());
            txtTelefonoUsuario.setText(usuarioSeleccionado.getTelefonoo());
            txtDireccionUsuario.setText(usuarioSeleccionado.getDireccionn());
            txtEmailUsuario.setText(usuarioSeleccionado.getEmaill());
            txtUsuario.setText(usuarioSeleccionado.getUsuarioo());
            psPassword.setText(usuarioSeleccionado.getPassword());
            if (usuarioSeleccionado.getEstado().equals("Activo")){
                rbtActivo.setSelected(true);
                rbtInactivo.setSelected(false);
            }else if (usuarioSeleccionado.getEstado().equals("Inactivo")){
                rbtActivo.setSelected(false);
                rbtInactivo.setSelected(true);
            }
            cmbPrivilegioUsuario.setValue(usuarioSeleccionado.getTipoUsuario());
            cmbPrivilegioUsuario.getSelectionModel().getSelectedItem();
        }
    }

    public void eliminarUsuario(ActionEvent event) {
        if(usuarios.getSelectionModel().getSelectedItem() == null){
            exibirError("No ha seleccionado un Registro");
        }else {
            if (exibirDialogoConfirmacion("Confirma si quieres eliminar el registro seleccionado?")){
                try {
                    Usuarios.eliminarRegistro(usuarios.getSelectionModel().getSelectedItem().getIdUsuarios());
                    exibirDialogoInformacao("Registro Eliminado");
                    tabPaneUsuarios.getSelectionModel().select(listUsuarios);
                    setCellTable();
                }catch (Exception e){
                    exibirError("Fallo al eliminar el registro");
                    e.printStackTrace();
                }
            }
        }
    }

    public void newUsuario(ActionEvent event) {
        Stage paciente = new Stage();
        paciente.setResizable(false);
        try {
<<<<<<< HEAD
            btnNew.getScene().getWindow().hide();
=======
>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
            Parent root = FXMLLoader.load(getClass().getResource("../view/nuevoUsuario.fxml"));
            paciente.initModality(Modality.APPLICATION_MODAL);
            paciente.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            paciente.setScene(scene);
            paciente.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewUsuario(ActionEvent event) {
        usuarioSeleccionado = usuarios.getSelectionModel().getSelectedItem();
        if ( usuarioSeleccionado == null){
            exibirError("No ha seleccionado Registro");
        }else {
            detalleUsuario.setDisable(false);
            tabPaneUsuarios.getSelectionModel().select(detalleUsuario);
            detIdUsuario.setText(usuarioSeleccionado.getIdUsuarios());
            detNombre.setText(usuarioSeleccionado.getNombres());
            detApellido.setText(usuarioSeleccionado.getApellidos());
            detFecha.setText(String.valueOf(usuarioSeleccionado.getBirthDate()));
            detTelefono.setText(usuarioSeleccionado.getTelefonoo());
            detDireccion.setText(usuarioSeleccionado.getDireccionn());
            detEmail.setText(usuarioSeleccionado.getEmaill());
            detNombreUsuario.setText(usuarioSeleccionado.getUsuarioo());
            detEstado.setText(usuarioSeleccionado.getEstado());
            detPrivilegio.setText(String.valueOf(usuarioSeleccionado.getTipoUsuario()));

        }
    }

    public void btnGuardarUsuarioActualizado(ActionEvent event) throws SQLException, ClassNotFoundException {
        Usuarios u;
        u = new Usuarios(
                txtIdUsuario.getText(),
                txtNombreUsuario.getText(),
                txtApellidoUsuario.getText(),
                Date.valueOf(dtpFecha.getValue()),
                txtTelefonoUsuario.getText(),
                txtDireccionUsuario.getText(),
                txtEmailUsuario.getText(),
                txtUsuario.getText(),
                psPassword.getText(),
                rbtActivo.isSelected()?"Activo":"Inactivo",
                cmbPrivilegioUsuario.getSelectionModel().getSelectedItem());
        int resultado = u.actualizarRegistro(conexion.getConnection());
        if (resultado == 1){
            listaUsuario.set(usuarios.getSelectionModel().getSelectedIndex(),u);
            exibirDialogoConfirmacion("Usuario  " + usuarioSeleccionado.getNombres() + "  Actualizado");
            tabPaneUsuarios.getSelectionModel().select(listUsuarios);

        }
    }

    private void exibirDialogoInformacao(String informacion){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacionn");
        alert.setHeaderText(null);
        alert.setContentText(informacion);
        alert.showAndWait();
    }
    private void exibirError(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errorr");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }
    private boolean exibirDialogoConfirmacion(String confirmacion){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacionn");
        alert.setHeaderText(null);
        alert.setContentText(confirmacion);
        Optional<ButtonType> opcao = alert.showAndWait();
        if (opcao.get() == ButtonType.OK)
            return true;

        return false;

    }
}
