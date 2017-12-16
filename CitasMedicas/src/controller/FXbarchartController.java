package controller;

import dba.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FXbarchartController implements Initializable{
//    @FXML
//    private BarChart<String, Double> barChart;

    @FXML
    private BarChart<String, String> barChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private Button btnLoad;

    private DBConnection conexion;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadChart();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void loadChart() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        Statement stnt = null;
        stnt = con.createStatement();
        String sql = "SELECT fecha, count(idCita) AS idCita FROM citas WHERE estadoCita = 'Pendiente' GROUP BY fecha ASC";
        XYChart.Series series = new XYChart.Series();
        ResultSet resultado = stnt.executeQuery(sql);
        series.setName("Cantidad de Pacientes por Fecha");
//        series.getData().add(new XYChart.Data<>("Alaster", 5000));
//        series.getData().add(new XYChart.Data<>("Nehemias", 1000));
//        series.getData().add(new XYChart.Data<>("Erazo", 3000));
//        series.getData().add(new XYChart.Data<>("Aragon", 2000));
        while (resultado.next()){
            series.getData().add(new XYChart.Data<>(resultado.getString(1), resultado.getDouble(2)));
        }

        barChart.getData().addAll(series);

//        Connection con = DBConnection.getConnection();
//        Statement stnt = null;
//        try {
//            stnt = con.createStatement();
//            String sql = "SELECT fecha, estadoCita FROM citas GROUP BY fecha ASC";
//            XYChart.Series<String, Double> series = new XYChart.Series<>();
//            ResultSet resultado = stnt.executeQuery(sql);
//
//            while (resultado.next()){
//                series.getData().add(new XYChart.Data<>(resultado.getString(1), resultado.getDouble(2)));
//            }
//            barChart.getData().add(series);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }


}
