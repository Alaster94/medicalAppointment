package model;

import dba.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.sql.*;

public class Medico {
    private StringProperty idMedicos;
    private StringProperty nombreDoctor;
    private StringProperty apellidoDoctor;
    private StringProperty direccionDoctor;
    private StringProperty emailDoctor;
    private StringProperty telefonoDoctor;
    private Areas areas;

    public Medico(String idMedicos, String nombreDoctor, String apellidoDoctor, String direccionDoctor, String emailDoctor, String telefonoDoctor, Areas areas) {
        this.idMedicos = new SimpleStringProperty(idMedicos);
        this.nombreDoctor = new SimpleStringProperty(nombreDoctor);
        this.apellidoDoctor = new SimpleStringProperty(apellidoDoctor);
        this.direccionDoctor = new SimpleStringProperty(direccionDoctor);
        this.telefonoDoctor = new SimpleStringProperty(telefonoDoctor);
        this.emailDoctor = new SimpleStringProperty(emailDoctor);
        this.areas = areas;
    }

    public Medico(String idMedicos, String nombreDoctor) {
        this.idMedicos = new SimpleStringProperty(idMedicos);
        this.nombreDoctor = new SimpleStringProperty(nombreDoctor);

    }

    public String getIdMedicos() {
        return idMedicos.get();
    }

    public StringProperty idMedicosProperty() {
        return idMedicos;
    }

    public void setIdMedicos(String idMedicos) {
        this.idMedicos.set(idMedicos);
    }

    public String getNombreDoctor() {
        return nombreDoctor.get();
    }

    public StringProperty nombreDoctorProperty() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor.set(nombreDoctor);
    }

    public String getApellidoDoctor() {
        return apellidoDoctor.get();
    }

    public StringProperty apellidoDoctorProperty() {
        return apellidoDoctor;
    }

    public void setApellidoDoctor(String apellidoDoctor) {
        this.apellidoDoctor.set(apellidoDoctor);
    }

    public String getDireccionDoctor() {
        return direccionDoctor.get();
    }

    public StringProperty direccionDoctorProperty() {
        return direccionDoctor;
    }

    public void setDireccionDoctor(String direccionDoctor) {
        this.direccionDoctor.set(direccionDoctor);
    }

    public String getEmailDoctor() {
        return emailDoctor.get();
    }

    public StringProperty emailDoctorProperty() {
        return emailDoctor;
    }

    public void setEmailDoctor(String emailDoctor) {
        this.emailDoctor.set(emailDoctor);
    }

    public String getTelefonoDoctor() {
        return telefonoDoctor.get();
    }

    public StringProperty telefonoDoctorProperty() {
        return telefonoDoctor;
    }

    public void setTelefonoDoctor(String telefonoDoctor) {
        this.telefonoDoctor.set(telefonoDoctor);
    }

    public Areas getAreas() {
        return areas;
    }

    public void setAreas(Areas areas) {
        this.areas = areas;
    }

    public int guardarRegistro(Connection connection) {
        try {
            PreparedStatement instruccion =
                    connection.prepareStatement("INSERT INTO medicos (idMedicos, nombreDoctor, apellidoDoctor, direccion, email, telefono, areas_idAreas) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            instruccion.setString(1, idMedicos.get());
            instruccion.setString(2, nombreDoctor.get());
            instruccion.setString(3, apellidoDoctor.get());
            instruccion.setString(4, direccionDoctor.get());
            instruccion.setString(5, emailDoctor.get());
            instruccion.setString(6, telefonoDoctor.get());
            instruccion.setInt(7, areas.getIdAreas());

            return instruccion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int actualizarRegistro(Connection connection) {
        try {
            PreparedStatement instruccion =
                    connection.prepareStatement(
                            "UPDATE medicos SET nombreDoctor = ?, apellidoDoctor = ?, direccion = ?, email = ?, telefono = ?, areas_idAreas = ? WHERE idMedicos = ?"
                    );
            instruccion.setString(1, nombreDoctor.get());
            instruccion.setString(2, apellidoDoctor.get());
            instruccion.setString(3, direccionDoctor.get());
            instruccion.setString(4, emailDoctor.get());
            instruccion.setString(5, telefonoDoctor.get());
            instruccion.setInt(6, areas.getIdAreas());
            instruccion.setString(7, idMedicos.get());
            return instruccion.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void llenarInformacionMedicos(Connection connection,
                                                ObservableList<Medico> lista) {
        try {
            Statement instruccion = connection.createStatement();
            ResultSet resultado = instruccion.executeQuery(
                    "SELECT M.idMedicos, M.nombreDoctor,M.apellidoDoctor,M.direccion, M.telefono, M.email, A.idAreas, A.nombreArea FROM medicos M INNER JOIN areas A ON (M.areas_idAreas = A.idAreas)"
            );
            while (resultado.next()) {
                lista.add(
                        new Medico(
                                resultado.getString("idMedicos"),
                                resultado.getString("nombreDoctor"),
                                resultado.getString("apellidoDoctor"),
                                resultado.getString("direccion"),
                                resultado.getString("email"),
                                resultado.getString("telefono"),
                                new Areas(resultado.getInt("idAreas"),
                                        resultado.getString("nombreArea")
                                )
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void eliminarRegistro(String idMedico) throws SQLException, ClassNotFoundException {
        Connection connection = new DBConnection().getConnection();
        String sql = "DELETE FROM medicos WHERE idMedicos = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idMedico);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException(e);
        }

<<<<<<< HEAD
    }
    @Override
    public String toString(){
        return nombreDoctor.get()+ " ("+idMedicos.get()+")";
    }
    
=======
    //    public int eliminarRegistro(Connection connection){
//        try {
//            PreparedStatement instruccion = connection.prepareStatement(
//                    "DELETE FROM medicos WHERE idMedicos = ?"
//            );
//            instruccion.setString(1, idMedicos.get());
//            return instruccion.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
    public static void eliminarRegistro(String idMedico) throws SQLException, ClassNotFoundException {
        Connection connection = new DBConnection().getConnection();
        String sql = "DELETE FROM medicos WHERE idMedicos = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idMedico);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

>>>>>>> 8e8c425d249a008ccb8255f8913edb8383b32d92
}
