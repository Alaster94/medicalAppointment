package model;

import dba.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.sql.*;

public class Pacientes {
    private StringProperty idPacientes;
    private StringProperty nombrePaciente;
    private StringProperty apellidoPaciente;
    private StringProperty genero;
    private Date fechaNacimiento;
    private StringProperty direccion;
    private StringProperty telefono;
    private StringProperty email;
    private Medico medico;
    private Areas areas;


    public Pacientes(String idPacientes, String nombrePaciente, String apellidoPaciente, String genero, Date fechaNacimiento, String direccion, String email, String telefono, Medico medico) {
        this.idPacientes = new SimpleStringProperty(idPacientes);
        this.nombrePaciente = new SimpleStringProperty(nombrePaciente);
        this.apellidoPaciente = new SimpleStringProperty(apellidoPaciente);
        this.genero = new SimpleStringProperty(genero);
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = new SimpleStringProperty(direccion);
        this.telefono = new SimpleStringProperty(telefono);
        this.email = new SimpleStringProperty(email);
        this.medico = medico;
    }

    public String getIdPacientes() {
        return idPacientes.get();
    }

    public StringProperty idPacientesProperty() {
        return idPacientes;
    }

    public void setIdPacientes(String idPacientes) {
        this.idPacientes.set(idPacientes);
    }

    public String getNombrePaciente() {
        return nombrePaciente.get();
    }

    public StringProperty nombrePacienteProperty() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente.set(nombrePaciente);
    }

    public String getApellidoPaciente() {
        return apellidoPaciente.get();
    }

    public StringProperty apellidoPacienteProperty() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente.set(apellidoPaciente);
    }

    public String getGenero() {
        return genero.get();
    }

    public StringProperty generoProperty() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero.set(genero);
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Areas getAreas() {
        return areas;
    }

    public void setAreas(Areas areas) {
        this.areas = areas;
    }

    public int guardarPaciente(Connection connection) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO pacientes (idPacientes, nombrePaciente, apellidoPaciente, genero, fechaNacimiento, direccion, email, telefono, medicos_idMedicos) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, idPacientes.get());
            statement.setString(2, nombrePaciente.get());
            statement.setString(3, apellidoPaciente.get());
            statement.setString(4, genero.get());
            statement.setDate(5, fechaNacimiento);
            statement.setString(6, direccion.get());
            statement.setString(7, email.get());
            statement.setString(8, telefono.get());
            statement.setString(9, medico.getIdMedicos());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int actualizarPaciente(Connection connection) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "UPDATE pacientes SET nombrePaciente = ?, apellidoPaciente = ?, genero = ?, fechaNacimiento = ?, direccion = ?, email = ?, telefono = ?, medicos_idMedicos = ? WHERE idPacientes = ?"
                    );
            statement.setString(1, nombrePaciente.get());
            statement.setString(2, apellidoPaciente.get());
            statement.setString(3, genero.get());
            statement.setDate(4, fechaNacimiento);
            statement.setString(5, direccion.get());
            statement.setString(6, email.get());
            statement.setString(7, telefono.get());
            statement.setString(8, medico.getIdMedicos());
            statement.setString(9, idPacientes.get());
            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void llenarInformacionPacientes(Connection connection,
                                                  ObservableList<Pacientes> lista) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT P.idPacientes, P.nombrePaciente, P.apellidoPaciente, P.genero, P.fechaNacimiento, P.direccion, P.email, P.telefono," +
                            " M.idMedicos, M.nombreDoctor " +
                            "FROM pacientes P INNER JOIN medicos M ON (P.medicos_idMedicos = M.idMedicos)"
            );
            while (resultSet.next()) {
                lista.add(
                        new Pacientes(
                                resultSet.getString("idPacientes"),
                                resultSet.getString("nombrePaciente"),
                                resultSet.getString("apellidoPaciente"),
                                resultSet.getString("genero"),
                                resultSet.getDate("fechaNacimiento"),
                                resultSet.getString("direccion"),
                                resultSet.getString("email"),
                                resultSet.getString("telefono"),
                                new Medico(resultSet.getString("idMedicos"),
                                        resultSet.getString("nombreDoctor")
                                )

                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarPaciente(String idPaciente) throws SQLException, ClassNotFoundException {
        Connection connection = new DBConnection().getConnection();
        String sql = "DELETE FROM pacientes WHERE idPacientes = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idPaciente);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
