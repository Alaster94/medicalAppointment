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

public class TipoUsuario {
    private IntegerProperty id;
    private StringProperty nombre;

    public TipoUsuario(Integer id, String nombre) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    public static void llenarInformacionTipo(Connection connection, ObservableList<TipoUsuario> lista){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "SELECT id, nombre FROM tipousuario"
            );
            while (resultado.next()){
                lista.add(
                        new TipoUsuario(
                                resultado.getInt("id"),
                                resultado.getString("nombre")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString(){
        return nombre.get();
    }
}
