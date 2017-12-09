package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.usersList;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class usersController implements Initializable {
    @FXML
    private CheckBox cbxEstado;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtDireccion;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private DatePicker dpBirthDate;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtApellido;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXTextField txtPassword;
    @FXML
    private TableColumn<?, ?> apellidos;
    @FXML
    private TableColumn<?, ?> estado;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableColumn<?, ?> direccion;
    @FXML
    private Label lblServerStatus;
    @FXML
    private TableColumn<?, ?> birthDate;
    @FXML
    private TableColumn<?, ?> nombres;
    @FXML
    private TableView<usersList> usersTable;
    @FXML
    private Button btnNew;
    @FXML
    private Pane pnlServerStatus;
    @FXML
    private Button btnRemove;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> telefono;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> usuario;
    @FXML
    private TableColumn<?, ?> password;

    @FXML
    private TableColumn<?, ?> idUsuarios;


    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<usersList> data;

    private void setCellTable() {
        nombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        idUsuarios.setCellValueFactory(new PropertyValueFactory<>("id"));

//        estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void setCellValueFromTableToTextField() {
//        usersTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                usersList ul = usersTable.getItems().get(usersTable.getSelectionModel().getSelectedIndex());
//                txtNombre.setText(ul.getNombres());
//                txtApellido.setText(ul.getApellidos());
//                dpBirthDate.setValue(LocalDate.parse(ul.getBirthDate()));
//                txtTelefono.setText(ul.getTelefono());
//                txtDireccion.setText(ul.getDireccion());
//                txtEmail.setText(ul.getEmail());
//                txtUsuario.setText(ul.getUsuario());
//                txtPassword.setText(ul.getPassword());
//                cbxEstado.setText(ul.getEstado());
//
//            }
//        });
    }

    private void loadDataFromDatabase() {
        data.clear();
        try {
            pst = con.prepareStatement("SELECT * FROM usuarios");
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new usersList("" + rs.getString(2), "" + rs.getString(3), "" + rs.getString(4),
                        "" + rs.getString(5), "" + rs.getString(6), "" + rs.getString(7), "" + rs.getString(8), "" + rs.getString(9), "" + rs.getString(10), rs.getString("idUsuarios")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        usersTable.setItems(data);
    }

    public void searchUser() {
        txtSearch.setOnKeyReleased(event -> {
            if (txtSearch.getText().equals("")) {
                loadDataFromDatabase();
            } else {
                data.clear();
                String sql = "SELECT * FROM usuarios WHERE apellidos LIKE '%" + txtSearch.getText() + "%'"
                        + "UNION SELECT * FROM usuarios WHERE apellidos LIKE '%" + txtSearch.getText() + "%'"
                        + "UNION SELECT * FROM usuarios WHERE usuario LIKE '%" + txtSearch.getText() + "%'";
                try {
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        data.add(new usersList("" + rs.getString(2), "" + rs.getString(3), "" + rs.getString(4),
                                "" + rs.getString(5), "" + rs.getString(6), "" + rs.getString(7), "" + rs.getString(8), "" + rs.getString(9), "" + rs.getString(10), rs.getString("idUsuarios")));
                    }
                    usersTable.setItems(data);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void updateAction(ActionEvent actionEvent) {
        String id = data.get(usersTable.getSelectionModel().getSelectedIndex()).getId();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/updateUser.fxml"));
        AnchorPane page = new AnchorPane();
        Stage stage = new Stage();
        stage.setTitle("Actualizar Usuario");
        stage.initModality(Modality.APPLICATION_MODAL);
        //  stage.initOwner((Stage) ((Button) actionEvent.getSource()).getScene().getWindow());
        stage.setScene(new Scene(page, 700, 700));
        UpdateUserController updateUserController = loader.getController();
        updateUserController.setCliente(id);
        updateUserController.setStage(stage);
        stage.show();
        stage.setResizable(true);


//        updateUserController.setStage(stage);


//        stage.setScene(scene);
//        stage.show();

    }

    public void removeAction(ActionEvent actionEvent) {
        String sql = "DELETE FROM usuarios WHERE nombres = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, txtNombre.getText());
            int i = pst.executeUpdate();
            if (i == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Dato eliminado corectamente");
                alert.show();
                loadDataFromDatabase();
//                clearTextField();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newAction(ActionEvent actionEvent) {
        Stage users = new Stage();
        users.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/nuevoUsuario.fxml"));
            Scene scene = new Scene(root);
            users.setScene(scene);
            users.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            con = dba.DBConnection.getConnection();
//            data = FXCollections.observableArrayList();
//            setCellTable();
//            loadDataFromDatabase();
//            setCellValueFromTableToTextField();
//            searchUser();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }


}
