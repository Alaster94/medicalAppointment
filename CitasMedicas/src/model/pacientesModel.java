package model;

import dba.DBConnection;
import model.listPacientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class pacientesModel {
    private Connection con;

    public pacientesModel() throws SQLException, ClassNotFoundException {
        Connection connection = new dba.DBConnection().getConnection();
    }

    public void listPacientes(listPacientes pacientes) throws SQLException, ClassNotFoundException {
        Connection connection = new dba.DBConnection().getConnection();
        String sql = "";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<listPacientes> buscar(String nombrePaciente) throws SQLException, ClassNotFoundException {
        Connection connection = new DBConnection().getConnection();
        List<listPacientes> pacientes = new ArrayList<>();
        String sqll = "SELECT idPacientes, nombrePaciente,apellidoPaciente, fechaNacimiento, telefono, email FROM pacientes where idPacientes LIKE '%" + nombrePaciente + "%'"
                + "UNION SELECT idPacientes, nombrePaciente,apellidoPaciente, fechaNacimiento, telefono, email FROM pacientes where nombrePaciente LIKE '%" + nombrePaciente + "%'"
                + "UNION SELECT idPacientes, nombrePaciente,apellidoPaciente, fechaNacimiento, telefono, email FROM pacientes where apellidoPaciente LIKE '%" + nombrePaciente + "%'";
        try {
            PreparedStatement stat = connection.prepareStatement(sqll);
            ResultSet result = stat.executeQuery();
            while (result.next()) {
                listPacientes paciente = new listPacientes();
                paciente.setIdentidad(result.getString("idPacientes"));
                paciente.setNombre(result.getString("nombrePaciente"));
                paciente.setApellido(result.getString("apellidoPaciente"));
                paciente.setTelefono(result.getString("telefono"));
                paciente.setFecha(result.getDate("fechaNacimiento"));
                paciente.setEmail(result.getString("email"));

                pacientes.add(paciente);

            }
            result.close();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return pacientes;
    }

    public List<listPacientes> consultar() throws SQLException, ClassNotFoundException {

        Connection connection1 = new DBConnection().getConnection();
        List<listPacientes> consultarPacientes = new ArrayList<>();
        String sql = "SELECT idPacientes, nombrePaciente, genero, apellidoPaciente, fechaNacimiento, direccion, telefono, email FROM pacientes";
        try {
            PreparedStatement statement = connection1.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listPacientes consPaciente = new listPacientes();
                consPaciente.setIdentidad(resultSet.getString("idPacientes"));
                consPaciente.setNombre(resultSet.getString("nombrePaciente"));
                consPaciente.setApellido(resultSet.getString("apellidoPaciente"));
                consPaciente.setGenero(resultSet.getString("genero"));
                consPaciente.setFecha(resultSet.getDate("fechaNacimiento"));
                consPaciente.setDireccion(resultSet.getString("direccion"));
                consPaciente.setTelefono(resultSet.getString("telefono"));
                consPaciente.setEmail(resultSet.getString("email"));

                consultarPacientes.add(consPaciente);


            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return consultarPacientes;
    }

    public void actualizar(listPacientes Paciente) throws SQLException, ClassNotFoundException {
        Connection connection = new DBConnection().getConnection();
        String sql = "UPDATE pacientes SET nombrePaciente=?, apellidoPaciente=?, genero=?, fechaNacimiento=?, direccion=?, email=?, telefono=? WHERE idPacientes=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Paciente.getNombre());
            statement.setString(2, Paciente.getApellido());
            statement.setString(3, Paciente.getGenero());
            statement.setDate(4, Paciente.getFecha());
            statement.setString(5, Paciente.getDireccion());
            statement.setString(6, Paciente.getEmail());
            statement.setString(7, Paciente.getTelefono());
            statement.setString(8, Paciente.getIdentidad());

//            statement.setInt(5, funcionario.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void eliminar(String idPaciente) throws SQLException, ClassNotFoundException {
        Connection connection = new DBConnection().getConnection();
        String sql = "DELETE FROM pacientes WHERE idPacientes = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idPaciente);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void view(listPacientes verPaciente) throws SQLException, ClassNotFoundException {
        Connection connection = new DBConnection().getConnection();
        String sql = "UPDATE pacientes SET nombrePaciente=?, apellidoPaciente=?, genero=?, fecha=?, direccion=?, email=?, telefono=? WHERE idPacientes=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, verPaciente.getNombre());
            statement.setString(2, verPaciente.getApellido());
            statement.setString(3, verPaciente.getGenero());
            statement.setDate(4, verPaciente.getFecha());
            statement.setString(5, verPaciente.getDireccion());
            statement.setString(6, verPaciente.getEmail());
            statement.setString(7, verPaciente.getTelefono());
            statement.setString(8, verPaciente.getIdentidad());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


}
