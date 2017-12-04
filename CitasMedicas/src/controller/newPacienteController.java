package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;

public class newPacienteController {
    //TextField
    @FXML private JFXTextField txtIdentidadPaciente;
    @FXML private JFXTextField txtNombrePaciente;
    @FXML private JFXTextField txtTelefonoPaciente;
    @FXML private JFXTextField txtApellidoPaciente;
    @FXML private JFXTextField txtDireccionPaciente;
    @FXML private JFXTextField txtEmailPaciente;
    //RadioButton
    @FXML private JFXRadioButton rbtFemenino;
    @FXML private JFXRadioButton rbtMasculino;
    @FXML private ToggleGroup GrupoGenero;
    //DatePicker
    @FXML private DatePicker dpFechaIngreso;
    //ComboBox
    @FXML private JFXComboBox<?> cmbMedico;

    public void registerPaciente(ActionEvent event) {
    }

    public void cancelPaciente(ActionEvent event) {
    }
}
