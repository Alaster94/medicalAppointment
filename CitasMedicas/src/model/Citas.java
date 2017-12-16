package model;

import dba.DBConnection;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.sql.*;

public class Citas {
    private IntegerProperty idCita;
    private StringProperty asunto;
    private Date fechaa;
    private StringProperty nota;
    private StringProperty diagnostico;
    private StringProperty sintomas;
    private StringProperty medicamentos;
    private StringProperty estadoCita;
    private StringProperty estadoPago;
    private Pacientes pacientes;
    private Medico medico;

    public Citas(Integer idCita, String asunto, Date fechaa, String nota, String diagnostico, String sintomas, String medicamentos, String estadoCita, String estadoPago, Pacientes pacientes, Medico medico) {
        this.idCita = new SimpleIntegerProperty(idCita);
        this.asunto = new SimpleStringProperty(asunto);
        this.fechaa = fechaa;
        this.nota = new SimpleStringProperty(nota);
        this.diagnostico = new SimpleStringProperty(diagnostico);
        this.sintomas = new SimpleStringProperty(sintomas);
        this.medicamentos = new SimpleStringProperty(medicamentos);
        this.estadoCita = new SimpleStringProperty(estadoCita);
        this.estadoPago = new SimpleStringProperty(estadoPago);
        this.pacientes = pacientes;
        this.medico = medico;

    }

    public Citas(Integer idCita,String asunto, Date fechaa, String nota, String diagnostico, String sintomas, String medicamentos, String estadoCita, String estadoPago, Pacientes pacientes) {
        this.idCita = new SimpleIntegerProperty(idCita);
        this.asunto = new SimpleStringProperty(asunto);
        this.fechaa = fechaa;
        this.nota = new SimpleStringProperty(nota);
        this.diagnostico = new SimpleStringProperty(diagnostico);
        this.sintomas = new SimpleStringProperty(sintomas);
        this.medicamentos = new SimpleStringProperty(medicamentos);
        this.estadoCita = new SimpleStringProperty(estadoCita);
        this.estadoPago = new SimpleStringProperty(estadoPago);
        this.pacientes = pacientes;

    }


    public int getIdCita() {
        return idCita.get();
    }

    public IntegerProperty idCitaProperty() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita.set(idCita);
    }

    public String getAsunto() {
        return asunto.get();
    }

    public StringProperty asuntoProperty() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto.set(asunto);
    }

    public Date getFechaa() {
        return fechaa;
    }

    public void setFechaa(Date fecha) {
        this.fechaa = fechaa;
    }

    public String getNota() {
        return nota.get();
    }

    public StringProperty notaProperty() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota.set(nota);
    }

    public String getDiagnostico() {
        return diagnostico.get();
    }

    public StringProperty diagnosticoProperty() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico.set(diagnostico);
    }

    public String getSintomas() {
        return sintomas.get();
    }

    public StringProperty sintomasProperty() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas.set(sintomas);
    }

    public String getMedicamentos() {
        return medicamentos.get();
    }

    public StringProperty medicamentosProperty() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos.set(medicamentos);
    }

    public String getEstadoCita() {
        return estadoCita.get();
    }

    public StringProperty estadoCitaProperty() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita.set(estadoCita);
    }

    public String getEstadoPago() {
        return estadoPago.get();
    }

    public StringProperty estadoPagoProperty() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago.set(estadoPago);
    }

    public Pacientes getPacientes() {
        return pacientes;
    }

    public void setPacientes(Pacientes pacientes) {
        this.pacientes = pacientes;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public static void llenarInformacionCitas(Connection connection,
                                              ObservableList<Citas> lista){
        try {
            Statement statement1 = connection.createStatement();
            ResultSet resultado = statement1.executeQuery(
                    "SELECT C.idCita, C.asunto, C.fecha, C.nota, C.diagnostico, C.sintomas, C.medicamentos, C.estadoCita, C.estadoPago, " +
                            "P.idPacientes, P.nombrePaciente, P.apellidoPaciente, P.telefono, M.idMedicos, M.nombreDoctor " +
                            "FROM citas C INNER JOIN pacientes P ON (C.pacientes_idPacientes = P.idPacientes) INNER JOIN medicos M ON (P.medicos_idMedicos = M.idMedicos)" +
                            "ORDER BY C.fecha DESC "
            );
            while(resultado.next()){
                lista.add(
                        new Citas(
                                resultado.getInt("idCita"),
                                resultado.getString("asunto"),
                                resultado.getDate("fecha"),
                                resultado.getString("nota"),
                                resultado.getString("diagnostico"),
                                resultado.getString("sintomas"),
                                resultado.getString("medicamentos"),
                                resultado.getString("estadoCita"),
                                resultado.getString("estadoPago"),
                                new Pacientes(resultado.getString("idPacientes"),
                                        resultado.getString("nombrePaciente"),
                                        resultado.getString("apellidoPaciente")
                                ),
                                new Medico(resultado.getString("idMedicos"),
                                        resultado.getString("nombreDoctor")
                                )

                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int guardarCita(Connection connection){
        try {
            //Evitar inyeccion SQL.
            PreparedStatement instruccion =
                    connection.prepareStatement("INSERT INTO citas (asunto, fecha, nota, diagnostico, sintomas, medicamentos, estadoCita, estadoPago, pacientes_idPacientes) "+
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            instruccion.setString(1, asunto.get());
            instruccion.setDate(2, fechaa);
            instruccion.setString(3, nota.get());
            instruccion.setString(4, diagnostico.get());
            instruccion.setString(5, sintomas.get());
            instruccion.setString(6, medicamentos.get());
            instruccion.setString(7, estadoCita.get());
            instruccion.setString(8, estadoPago.get());
            instruccion.setString(9, pacientes.getIdPacientes());
            return instruccion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int actualizarCita(Connection connection){
        try {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "UPDATE citas SET asunto = ?, fecha = ?, nota = ?, diagnostico = ?, sintomas = ?, medicamentos = ?, estadoCita = ?, estadoPago = ?, pacientes_idPacientes = ? WHERE idCita = ?"
                    );
            statement.setString(1, asunto.get());
            statement.setDate(2, fechaa);
            statement.setString(3, nota.get());
            statement.setString(4, diagnostico.get());
            statement.setString(5, sintomas.get());
            statement.setString(6, medicamentos.get());
            statement.setString(7, estadoCita.get());
            statement.setString(8, estadoPago.get());
            statement.setString(9, pacientes.getIdPacientes());
            statement.setInt(10, idCita.get());
            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void eliminarCita(int idCita) throws SQLException, ClassNotFoundException {
        Connection connection = new DBConnection().getConnection();
        String sql = "DELETE FROM citas WHERE idCita = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCita);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new  RuntimeException(e);
        }

    }
}
