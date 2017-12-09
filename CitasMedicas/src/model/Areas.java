package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Areas {
    private IntegerProperty idAreas;
    private StringProperty nombreArea;

    public Areas(Integer idAreas, String nombreArea) {
        this.idAreas = new SimpleIntegerProperty(idAreas);
        this.nombreArea = new SimpleStringProperty(nombreArea);
    }

    public int getIdAreas() {
        return idAreas.get();
    }

    public IntegerProperty idAreasProperty() {
        return idAreas;
    }

    public void setIdAreas(int idAreas) {
        this.idAreas.set(idAreas);
    }

    public String getNombreArea() {
        return nombreArea.get();
    }

    public StringProperty nombreAreaProperty() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea.set(nombreArea);
    }

    public static void llenarInformacion(Connection connection, ObservableList<Areas> lista) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "SELECT idAreas, nombreArea FROM areas"
            );
            while (resultado.next()) {
                lista.add(
                        new Areas(
                                resultado.getInt("idAreas"),
                                resultado.getString("nombreArea")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return nombreArea.get();
    }
}
