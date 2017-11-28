package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dba.DBConnection;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Pane PaneLogin1;

    @FXML
    private AnchorPane DesignPane;

    @FXML
    private ImageView imgProgress;

    @FXML
    private AnchorPane paneLogin;

    @FXML
    private Pane PaneLogin;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnCitas;

    private Connection con = null;
    private DBConnection handler;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public void loginAction(ActionEvent actionEvent) throws IOException {
        imgProgress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(event -> {
//            System.out.println("Inicio de secion correcto");
            //Retrive Data from Database
            try {
                con = handler.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String q1 = "SELECT * from usuarios where usuario=? and password=?";

            try {
                pst = con.prepareStatement(q1);
                pst.setString(1,txtUsername.getText());
                pst.setString(2,txtPassword.getText());
                ResultSet rs = pst.executeQuery();

                int count=0;

                while (rs.next()){
                    count = count+1;
                }

                if (count==1){
//                System.out.println("Login Successfull");
                    btnLogin.getScene().getWindow().hide();
                    Stage home = new Stage();
                    try {
                        home.setResizable(false);//Ocultar la opcion de maximizar
                        Parent root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
                        Scene scene = new Scene(root);
                        home.setScene(scene);
                        home.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
//                System.out.println("Usuario o Password no es Correcto");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Usuario o Password no es Correcto");
                    alert.show();
                    imgProgress.setVisible(false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        pt.play();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgProgress.setVisible(false);
        handler = new DBConnection();
//        imgProgress.setVisible(false);
//        txtUsername.setStyle("-fx-text-inner-color: #a0a2ab;");
//        txtPassword.setStyle("-fx-text-inner-color: #a0a2ab;");
    }
}
